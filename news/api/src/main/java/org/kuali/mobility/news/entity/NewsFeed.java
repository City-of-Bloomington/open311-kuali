package org.kuali.mobility.news.entity;

import java.util.List;

public interface NewsFeed {

	public NewsFeed copy();

	public int compareTo(NewsFeed o);

	/**
	 * @return the title
	 */
	public String getTitle();

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title);

	/**
	 * @return the author
	 */
	public String getAuthor();

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author);

	/**
	 * @return the description of the feed
	 */
	public String getDescription();

	/**
	 * @param description the description of the feed
	 */
	public void setDescription(String description);

	/**
	 * @return the id of the associated NewsSource
	 */
	public long getSourceId();

	/**
	 * @param sourceId id of the associated NewsSource
	 */
	public void setSourceId(long sourceId);

	/**
	 * @return the articles
	 */
	public List<NewsArticle> getArticles();

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<NewsArticle> articles);

	/**
	 * @return the display order
	 */
	public int getOrder();

	/**
	 * @param order the display order to set
	 */
	public void setOrder(int order);

}