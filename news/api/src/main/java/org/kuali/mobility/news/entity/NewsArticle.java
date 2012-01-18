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

import java.sql.Timestamp;

public interface NewsArticle {

	public NewsArticle copy();

	/**
	 * @return the publish date formatted for display (EEEE, MMMM dd, yyyy h:mm a)
	 */
	public String getPublishDateDisplay();

	public int compareTo(NewsArticle arg0);

	/**
	 * @return the title
	 */
	public String getTitle();

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title);

	/**
	 * @return the URL to the full article
	 */
	public String getLink();

	/**
	 * @param link the URL to the full article
	 */
	public void setLink(String link);

	/**
	 * @return the body text of the article
	 */
	public String getDescription();

	/**
	 * @param description the body text to set
	 */
	public void setDescription(String description);

	/**
	 * @return the publish date
	 */
	public Timestamp getPublishDate();

	/**
	 * @param publishDate the publish date to set
	 */
	public void setPublishDate(Timestamp publishDate);

	/**
	 * @return the article id
	 */
	public String getArticleId();

	/**
	 * @param articleId the article id to set
	 */
	public void setArticleId(String articleId);

	/**
	 * @return the id of the NewsSource to which this article belongs
	 */
	public long getSourceId();

	/**
	 * @param sourceId the id of the NewsSource to which this article belongs
	 */
	public void setSourceId(long sourceId);

}