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

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Represents a single article present in a news feed.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 *
 */
public class NewsArticle implements Serializable, Comparable<NewsArticle> {

	private static final long serialVersionUID = -133725965130444787L;
	
	private String title;
	private String link;
	private String description;
	private Timestamp publishDate;
	private String articleId;
	private long sourceId;
	
	private final SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM dd, yyyy h:mm a");
	
	public NewsArticle copy() {
		NewsArticle copy = new NewsArticle();
		if (title != null) {
			copy.setTitle(new String(title));
		}
		if (link != null) {
			copy.setLink(new String(link));
		}
		if (description != null) {
			copy.setDescription(new String(description));
		}
		if (articleId != null) {
			copy.setArticleId(new String(articleId));
		}
		copy.setSourceId(sourceId);
		copy.setPublishDate(new Timestamp(publishDate.getTime()));
		
		return copy;
	}
	
	/**
	 * @return the publish date formatted for display (EEEE, MMMM dd, yyyy h:mm a)
	 */
	public String getPublishDateDisplay() {
		return format.format(publishDate);
	}
	
	@Override
	public int compareTo(NewsArticle arg0) {
		return publishDate.compareTo(arg0.publishDate);
	}
	
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the URL to the full article
	 */
	public String getLink() {
		return link;
	}

	/**
	 * @param link the URL to the full article
	 */
	public void setLink(String link) {
		this.link = link;
	}

	/**
	 * @return the body text of the article
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the body text to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the publish date
	 */
	public Timestamp getPublishDate() {
		return publishDate;
	}

	/**
	 * @param publishDate the publish date to set
	 */
	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}

	/**
	 * @return the article id
	 */
	public String getArticleId() {
		return articleId;
	}

	/**
	 * @param articleId the article id to set
	 */
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	/**
	 * @return the id of the NewsSource to which this article belongs
	 */
	public long getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the id of the NewsSource to which this article belongs
	 */
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}
}
