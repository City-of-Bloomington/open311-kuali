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
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a full news feed.  Each NewsFeed object corresponds to one NewsSource object and contains a list of NewsArticle objects.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
public class NewsFeedImpl implements Serializable, Comparable<NewsFeed>, NewsFeed {

	private static final long serialVersionUID = 475441282491797666L;
	
	private String title;
	private String author;
	private String description;
	private long sourceId;
	private List<NewsArticle> articles;
	private int order;
	
	@Override
	public NewsFeed copy() {
		NewsFeed copy = new NewsFeedImpl();
		copy.setSourceId(sourceId);
		copy.setOrder(order);
		if (title != null) {
			copy.setTitle(new String(title));
		}
		if (author != null) {
			copy.setAuthor(new String(author));
		}
		if (description != null) {
			copy.setDescription(new String(description));
		}
		if (!articles.isEmpty()) {
			List<NewsArticle> articlesCopy = new ArrayList<NewsArticle>();
			for (NewsArticle article : articles) {
				articlesCopy.add(article.copy());
			}
			copy.setArticles(articlesCopy);
		}
		return copy;
	}
    
	public NewsFeedImpl() {
		articles = new ArrayList<NewsArticle>();
	}
	
	@Override
	public int compareTo(NewsFeed o) {
		return order - o.getOrder();
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#getTitle()
	 */
	@Override
	public String getTitle() {
		return title;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#setTitle(java.lang.String)
	 */
	@Override
	public void setTitle(String title) {
		this.title = title;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#getAuthor()
	 */
	@Override
	public String getAuthor() {
		return author;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#setAuthor(java.lang.String)
	 */
	@Override
	public void setAuthor(String author) {
		this.author = author;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#getDescription()
	 */
	@Override
	public String getDescription() {
		return description;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#setDescription(java.lang.String)
	 */
	@Override
	public void setDescription(String description) {
		this.description = description;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#getSourceId()
	 */
	@Override
	public long getSourceId() {
		return sourceId;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#setSourceId(long)
	 */
	@Override
	public void setSourceId(long sourceId) {
		this.sourceId = sourceId;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#getArticles()
	 */
	@Override
	public List<NewsArticle> getArticles() {
		return articles;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#setArticles(java.util.List)
	 */
	@Override
	public void setArticles(List<NewsArticle> articles) {
		this.articles = articles;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#getOrder()
	 */
	@Override
	public int getOrder() {
		return order;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsFeed#setOrder(int)
	 */
	@Override
	public void setOrder(int order) {
		this.order = order;
	}
}
