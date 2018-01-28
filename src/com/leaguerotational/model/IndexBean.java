package com.leaguerotational.model;

import java.sql.SQLException;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.leaguerotational.dto.Article;

@ManagedBean
public class IndexBean {

	private List<Article> recentArticles;
	
	public List<Article> getRecentArticles() {
		//First time in a view that its called
		if (recentArticles == null) {
			try {
				recentArticles = DataProvider.getRecentArticles(7);
			} catch (SQLException e) { e.printStackTrace(); }
			for (int i = 0; i < recentArticles.size(); i++) {
				if (recentArticles.get(i).isFeatured()) {
					Article article = recentArticles.get(i);
					recentArticles.remove(i);
					recentArticles.add(0, article);
					break;
				}
			}
		}
		
		
		
		return recentArticles;
	}

}
