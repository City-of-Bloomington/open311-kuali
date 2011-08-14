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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity(name="Tool")
@Table(name="TOOL_T")
public class Tool implements Serializable, Comparable<Tool> {

	private static final long serialVersionUID = 4709451428489759275L;

	@Id
    @SequenceGenerator(name="tool_sequence", sequenceName="SEQ_TOOL_T", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="tool_sequence")
    @Column(name="TOOL_ID")
	private Long toolId;
	
	@Column(name="ALIAS")
	private String alias;

	@Column(name="TITLE")
	private String title;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Transient
	private String badgeCount;
	
	@Column(name="ICON_URL")
	private String iconUrl;
	
	@Version
    @Column(name="VER_NBR")
    private Long versionNumber;	

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

	public String getBadgeCount() {
		return badgeCount;
	}

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
	public int compareTo(Tool arg0) {
		return title.compareTo(arg0.title);
	}
	
	@Override
	public boolean equals(Object arg0) {
		if (arg0 instanceof Tool) {
			return toolId.equals(((Tool)arg0).toolId);
		}
		return false;
	}

}
