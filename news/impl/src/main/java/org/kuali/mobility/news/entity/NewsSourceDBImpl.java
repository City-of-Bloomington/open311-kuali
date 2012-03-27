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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Represents a source for a news feed.  Each NewsSource has an associated NewsFeed.  
 * A NewsSource contains the URL and display order for a feed, whereas the NewsFeed contains the actual feed data.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 *
 */
@Entity
@Table(name="NEWS_SRC_T")
public class NewsSourceDBImpl implements NewsSource {

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="ID")
	private Long id;
	
	@Column(name="NAME")
	private String name;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="ACTIVE")
	private boolean active;
	
	@Column(name="ORDR")
	private int order;
	
	@Version
    @Column(name="VER_NBR")
    private Long versionNumber;
	
	// TODO: Change this so it can be persisted.
	@Transient
	private Long parentId;
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#setId(java.lang.Long)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#getUrl()
	 */
	@Override
	public String getUrl() {
		return url;
	}
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#setUrl(java.lang.String)
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#getVersionNumber()
	 */
	@Override
	public Long getVersionNumber() {
		return versionNumber;
	}
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#setVersionNumber(java.lang.Long)
	 */
	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#isActive()
	 */
	@Override
	public boolean isActive() {
		return active;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#setActive(boolean)
	 */
	@Override
	public void setActive(boolean active) {
		this.active = active;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#getName()
	 */
	@Override
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#setName(java.lang.String)
	 */
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#getOrder()
	 */
	@Override
	public int getOrder() {
		return order;
	}

	/* (non-Javadoc)
	 * @see org.kuali.mobility.news.entity.NewsSource#setOrder(int)
	 */
	@Override
	public void setOrder(int order) {
		this.order = order;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}
}
