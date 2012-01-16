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
public class NewsArticleImpl implements Serializable, Comparable<NewsArticle>, NewsArticle {

	private static final long serialVersionUID = -133725965130444787L;
	
	private String title;
	private String link;
	private String description;
	private Timestamp publishDate;
	private String articleId;
	private long sourceId;
	
	private final SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM dd, yyyy h:mm a");
	
	@Override
	public NewsArticle copy() {
		NewsArticle copy = new NewsArticleImpl();
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
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#getPublishDateDisplay()
	 */
	@Override
	public String getPublishDateDisplay() {
		return format.format(publishDate);
	}
	
	@Override
	public int compareTo(NewsArticle arg0) {
		return publishDate.compareTo(arg0.getPublishDate());
	}
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#getLink()
	 */
	@Override
	public String getLink() {
		return link;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#setLink(java.lang.String)
	 */
	@Override
	public void setLink(String link) {
		this.link = link;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#getPublishDate()
	 */
	@Override
	public Timestamp getPublishDate() {
		return publishDate;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#setPublishDate(java.sql.Timestamp)
	 */
	@Override
	public void setPublishDate(Timestamp publishDate) {
		this.publishDate = publishDate;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#getArticleId()
	 */
	@Override
	public String getArticleId() {
		return articleId;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#setArticleId(java.lang.String)
	 */
	@Override
	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#getSourceId()
	 */
	@Override
	public long getSourceId() {
		return sourceId;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsArticle#setSourceId(long)
	 */
	@Override
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}
}
