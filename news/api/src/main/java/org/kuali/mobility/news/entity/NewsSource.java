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
public class NewsSource {

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
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	/**
	 * @return the URL of the feed
	 */
	public String getUrl() {
		return url;
	}
	
	/**
	 * @param url the URL of the feed
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	
	public Long getVersionNumber() {
		return versionNumber;
	}
	
	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	/**
	 * @return whether the feed is active or not
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * @param active set this feed active or inactive
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * @return the name assigned to this feed
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set for this feed.  It is not displayed to end users.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the display order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * @param order the display order
	 */
	public void setOrder(int order) {
		this.order = order;
	}
}
