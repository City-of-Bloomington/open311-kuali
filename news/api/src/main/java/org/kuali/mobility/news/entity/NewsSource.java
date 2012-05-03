/**
 * Copyright 2011 The Kuali Foundation Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

package org.kuali.mobility.news.entity;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="newsSource")
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
	 * @return the articles
	 */
	@XmlTransient
	public List<NewsArticle> getArticles();

	/**
	 * @param articles the articles to set
	 */
	@XmlTransient
	public void setArticles(List<NewsArticle> articles);

	/**
	 * @return the display order
	 */
	public int getOrder();

	/**
	 * @param order the display order
	 */
	public void setOrder(int order);
	
	public void setParentId( Long parentId );
	public Long getParentId();

}