package com.leaguerotational.dto;

/**
 * Represents an article that is stored in the article table of the_league_rotational database
 * @author Steven
 */
public class Article {
	
	private String title;
	private String url;
	private String shortTitle;
	private String date;
	private String description;
	private String content;
	private String[] tags;
	private String image;
	private String thumbnail;
	private boolean published;
	private boolean featured;

	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Creates a short title using this article's title, suitable for sharing on Twitter.
	 * @return the shortened title with "..." at the end, or the regular title if not long enough.
	 */
	public String getShortTitle() {
		if (title.length() > 71) {
			shortTitle = title.substring(0, 71) + "...";
		} else {
			shortTitle = title;
		}
		return shortTitle;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getContent() {
		return content;
	}
	
	public void setContent(String content) {
		this.content = content;
	}
	
	public String[] getTags() {
		return tags;
	}
	
	public void setTags(String[] tags) {
		this.tags = tags;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	/**
	 * Determines the path to the thumbnail (not stored in database) by using the article's image path.
	 * @return	the path to the thumbnail, starting with the img directory
	 */
	public String getThumbnail() {
		if (image == null) {
			return "img/thumbnail.png";
		}
		StringBuilder builder = new StringBuilder();
		String[] tokens = image.split("\\/");
		// "img/05-2015/clg.jpg" becomes {"img", "05-2015", "clg.jpg"}
		
		//piece it together with "thumbnail" before the end
		builder.append(tokens[0] + "/");
		builder.append(tokens[1] + "/");
		builder.append("thumbnail/");
		builder.append(tokens[2]);
		
		//becomes img/05-2015/thumbnail/clg.jpg in this example
		thumbnail = builder.toString();
		return thumbnail;
	}
	
	public boolean isPublished() {
		return published;
	}

	public void setPublished(boolean published) {
		this.published = published;
	}

	public boolean isFeatured() {
		return featured;
	}

	public void setFeatured(boolean featured) {
		this.featured = featured;
	}
}
