package com.leaguerotational.model;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.leaguerotational.dto.Article;

@ManagedBean
public class SearchBean {

	private String type;			//set by get param
	private String content;			//set by get param
	private String fmtdContent;		//capitalization or "results found for" shenanigans
	private int page = 1;
	private boolean prevPage;
	private boolean nextPage;
	private List<Article> results;	//set by a method which is chosen based off the type

	/**
	 * Returns the content of the search query after formatting it for the appropriate search type.<br/>
	 * e.g. a search of type "tag" capitalizes each tag's word, a search of "broad" says "Results found for: ..."
	 * @return
	 */
	public String getContent() {
		return content;
	}
	
	public String getFmtdContent() {
		if (null != type) {
			if (type.equals("tag") || type.equals("all")) {
				fmtdContent = capitalizeWords(content);
			}
			else if (type.equals("broad")) {
				fmtdContent = "Results found for: \"" + content + "\":";
			}
		}
		return fmtdContent;
	}
	private String capitalizeWords(String str) {
		StringBuilder capitalized = new StringBuilder();
		String[] words = str.split("\\s+");
		for (String word: words) {
			capitalized.append(word.substring(0,1).toUpperCase() + word.substring(1) + " ");
		}
		return capitalized.toString().trim();
	}
	
	public List<Article> getResults() {

		switch (type) {
		
		case "tag":
			if (results == null)
				try {
					results = getThisPageResults(DataProvider.getArticlesByTag(content));
				} catch (SQLException e) { e.printStackTrace(); }
			
			break;
			
		case "all":
			if (results == null)
				try {
					results = getThisPageResults(DataProvider.getAllArticles());
				} catch (SQLException e) { e.printStackTrace(); }
			
			break;
			
		case "broad":
			if (results == null)
				try {
					results = getThisPageResults(DataProvider.getArticlesByBroadSearch(content));
				} catch (SQLException e) { e.printStackTrace(); }
			
			break;
			
		default:
			break;
		}
		
		return results;
	}
	
	private List<Article> getThisPageResults(List<Article> results) {

		
		int lowerIndex = page * 10 - 10;
		int upperIndex = page * 10;
		
		if (upperIndex >= results.size()) {
			upperIndex = results.size();
			nextPage = false;
		}
		else {
			nextPage = true;
		}
		
		
		try {
			results = results.subList(lowerIndex, upperIndex);
		} catch (Exception e) {
			results = null;
		}
		return results;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public boolean isPrevPage() {
		if (page > 1 && results != null) {
			prevPage = true;
		} else {
			prevPage = false;
		}
		return prevPage;
	}

	public boolean isNextPage() {
		return nextPage;
	}
	
	
}
