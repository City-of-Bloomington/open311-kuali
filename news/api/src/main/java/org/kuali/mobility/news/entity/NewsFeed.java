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