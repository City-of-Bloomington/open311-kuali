package org.kuali.mobility.news.entity;

public interface NewsSource {

	public Long getId();

	public void setId(Long id);

	/**
	 * @return the URL of the feed
	 */
	public String getUrl();

	/**
	 * @param url the URL of the feed
	 */
	public void setUrl(String url);

	public Long getVersionNumber();

	public void setVersionNumber(Long versionNumber);

	/**
	 * @return whether the feed is active or not
	 */
	public boolean isActive();

	/**
	 * @param active set this feed active or inactive
	 */
	public void setActive(boolean active);

	/**
	 * @return the name assigned to this feed
	 */
	public String getName();

	/**
	 * @param name the name to set for this feed.  It is not displayed to end users.
	 */
	public void setName(String name);

	/**
	 * @return the display order
	 */
	public int getOrder();

	/**
	 * @param order the display order
	 */
	public void setOrder(int order);

}