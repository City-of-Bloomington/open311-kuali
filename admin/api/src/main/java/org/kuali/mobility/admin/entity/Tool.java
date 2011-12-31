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

package org.kuali.mobility.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

/**
 * Defines a "tool" to show on a home screen. It can be assigned to HomeScreens through HomeTool objects.
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 *
 */
@Entity
@Table(name="TL_T")
public class Tool implements Serializable, Comparable<Tool> {

	private static final long serialVersionUID = 4709451428489759275L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name="ID")
	private Long toolId;
	
	@Column(name="ALIAS")
	private String alias;

	@Column(name="TTL")
	private String title;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="DESC_TXT")
	private String description;
		
	@Column(name="ICN_URL")
	private String iconUrl;
	
	@Column(name="CONTACTS")
	private String contacts;
	
	@Column(name="PROG_LANG")
	private String programmingLanguage;
	
	@Column(name="KEYWORDS")
	private String keywords;
	
	@Column(name="LICENCE")
	private String licence;
	
	@Version
    @Column(name="VER_NBR")
    private Long versionNumber;	
	
	@Transient
	private String badgeCount;

	public Long getToolId() {
		return toolId;
	}

	public void setToolId(Long id) {
		this.toolId = id;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return a value to show on the home screen.  Example would be for an "unread count" in an email tool.
	 */
	public String getBadgeCount() {
		return badgeCount;
	}

	/**
	 * set the value to show on the home screen
	 * @param badgeCount
	 */
	public void setBadgeCount(String badgeCount) {
		this.badgeCount = badgeCount;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	@Override
	public int compareTo(Tool that) {
		if (that == null) {
			return -1;
		}
		return this.title.compareTo(that.title);
	}
	
	@Override
	public boolean equals(Object that) {
		if (that == null) {
			return false;
		}
		if (that instanceof Tool) {
			return this.toolId.equals(((Tool)that).toolId);
		}
		return false;
	}

	public String getContacts() {
		return contacts;
	}

	public void setContacts(String contacts) {
		this.contacts = contacts;
	}

	public String getProgrammingLanguage() {
		return programmingLanguage;
	}

	public void setProgrammingLanguage(String programmingLanguage) {
		this.programmingLanguage = programmingLanguage;
	}

	public String getKeywords() {
		return keywords;
	}

	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}

	public String getLicence() {
		return licence;
	}

	public void setLicence(String licence) {
		this.licence = licence;
	}

}
