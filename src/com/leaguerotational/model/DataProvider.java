package com.leaguerotational.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.leaguerotational.dto.Article;

public class DataProvider {

	public static List<Article> getArticlesByBroadSearch(String searchContent) throws SQLException {
		String query = "SELECT * FROM article WHERE title LIKE ? OR tags LIKE ? OR content LIKE ? ORDER BY date DESC";
		PreparedStatement preparedStatement = createConnection().prepareStatement(query);
		preparedStatement.setString(1, "%" + searchContent + "%");
		preparedStatement.setString(2, "%" + searchContent + "%");
		preparedStatement.setString(3, "%" + searchContent + "%");
		ResultSet rs = preparedStatement.executeQuery();
		
		return getArticles(rs);
	}
	
	public static List<Article> getAllArticles() throws SQLException {
		String query = "SELECT * FROM article ORDER BY date DESC";
		PreparedStatement preparedStatement = createConnection().prepareStatement(query);
		ResultSet rs = preparedStatement.executeQuery();
		
		return getArticles(rs);
	}
	
	public static List<Article> getArticlesByTag(String tag) throws SQLException {
		String query = "SELECT * FROM article WHERE tags LIKE ? ORDER BY date DESC";
		PreparedStatement preparedStatement = createConnection().prepareStatement(query);
		preparedStatement.setString(1, "%" + tag + "%");
		System.out.println(preparedStatement);
		ResultSet rs = preparedStatement.executeQuery();
		
		return getArticles(rs);
	}

	public static List<Article> getRecentArticles(int numberOfArticles) throws SQLException {
		String query = "SELECT * FROM article ORDER BY date DESC LIMIT ?";
		PreparedStatement preparedStatement = createConnection().prepareStatement(query);
		preparedStatement.setInt(1, numberOfArticles);
		ResultSet rs = preparedStatement.executeQuery();
		
		return getArticles(rs);
	}
	
	public static List<Article> getArticle(String url) throws SQLException {
		String query = "SELECT * FROM article WHERE url=? ORDER BY date DESC";
		PreparedStatement preparedStatement = createConnection().prepareStatement(query);
		preparedStatement.setString(1, url);
		ResultSet rs = preparedStatement.executeQuery();
		
		return getArticles(rs);
	}
	
	private static String[] separateTagsIntoArray(String tagsTogether) {
		String[] separateTags = tagsTogether.split("\\|");
		return separateTags;
	}
	
	private static String formatDate(Date date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MMMM d, yyyy");
		return dateFormat.format(date);
	}

	private static List<Article> getArticles(ResultSet rs) throws SQLException {
		List<Article> articles = new ArrayList<>();
		
		while (rs.next()) {
			if (!rs.getBoolean("published")) {
				continue;
			}
			Article article = new Article();
			article.setTitle(rs.getString("title"));
			article.setUrl(rs.getString("url"));
			article.setDate(formatDate(rs.getDate("date")));
			if (rs.getString("description") != null) {
				article.setDescription(rs.getString("description"));
			}
			if (rs.getString("content") != null) {
				article.setContent(rs.getString("content"));
			}
			if (rs.getString("tags") != null) {
				article.setTags(separateTagsIntoArray(rs.getString("tags")));
			}
			if (rs.getString("image") != null) {
				article.setImage(rs.getString("image"));
			}
			article.setPublished(rs.getBoolean("published"));
			article.setFeatured(rs.getBoolean("featured"));
			articles.add(article);
		}

		return articles;
	}
	
	public static Connection createConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		}

		Connection conn = null;

		try {
			//Ouch. I actually put this information in the source code when I wrote this. Bad, past Steve!
			conn = DriverManager.getConnection("jdbc:mysql://localhost/the_league_rotational", "<redacted>", "<redacted>");
		} catch (SQLException ex) {
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		}

		return conn;
	}
	
}
