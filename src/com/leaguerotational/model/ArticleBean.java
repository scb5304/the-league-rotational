package com.leaguerotational.model;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.leaguerotational.dto.Article;

@ManagedBean
public class ArticleBean {
	
	private String title;
	private String url;
	private Article article;
	private Article[] navigateArticles;
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getUrl() {
		return url;
	}

	public Article getArticle() {
		if (article == null) {
			try {
				article = DataProvider.getArticle(url).get(0);
			} catch (Exception e) {
				article = new Article();
				article.setTitle("404 Error:");
				article.setContent("<p>No article found by that title.</p>"
						+ "<p>To make up for it, here's a picture of Phreak.</p>"
						+ "<p>"
						+ "<img class=\"article-featured-image img-responsive\" src=\"../img/phreak.jpg\" />"
						+ "</p>");
				article.setTags(new String[] {"Tons", "of", "Damage"});
			}
		}
		return article;
	}
	
	public Article[] getNavigateArticles() {
		List<Article> allArticleData = null;
		try {
			allArticleData = DataProvider.getAllArticles();
		} catch (SQLException e1) {e1.printStackTrace();}
		
		Article previousArticle, nextArticle; 
		int thisArticleIndex = getThisArticleIndex(allArticleData);
		
		try {
			previousArticle = allArticleData.get(thisArticleIndex + 1);
		} catch (IndexOutOfBoundsException e) {
			previousArticle = null;
		}
		
		try {
			nextArticle = allArticleData.get(thisArticleIndex - 1);
		} catch (IndexOutOfBoundsException e) {
			nextArticle = null;
		}
		
		navigateArticles = new Article[2];
		navigateArticles[0] = previousArticle;
		navigateArticles[1] = nextArticle;
		
		return navigateArticles;
	}
	
	private int getThisArticleIndex(List<Article> allArticleData) {
		int thisArticleIndex = 0;
		
		for (int i = 0; i < allArticleData.size(); i++) {
			String dbArticleTitle = allArticleData.get(i).getTitle();
			String thisArticleTitle = article.getTitle();
			
			if (dbArticleTitle.equals(thisArticleTitle)) {
				thisArticleIndex = i;
			}
		}
		return thisArticleIndex;
	}
	
}
