/**
 * Copyright 2011-2012 The Kuali Foundation Licensed under the
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

package org.kuali.mobility.open311.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="KME_SUBMISSION_T")
public class Submission implements Serializable {

	private static final long serialVersionUID = 3936544145647062912L;

    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false, updatable=false)
    private Long id;
  
    @Column(name="PRNT_ID")
    private Long parentId;
    
    @Column(name="REV_NBR")
    private Long revisionNumber;

    @Column(name="REV_USR_ID")
    private String revisionUserId;
    
    @Column(name="NM")
    private String name;
    
    @Column(name="TYP")
    private String type;
    
    @Column(name="GRP")
    private String group;
    
    @Column(name="ACTV")
    private int active;
    
    @Column(name="PST_DT")
    private Timestamp postDate;

    @Column(name="ARCHVD_DT")
    private Timestamp archivedDate;

    @Column(name="IP_ADDR")
    private String ipAddress;

    @Column(name="USR_AGNT")
    private String userAgent;
    
    @Column(name="USR_ID")
    private String userId;
    
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="submission")
    private List<SubmissionAttribute> attributes;

	@Version
    @Column(name="VER_NBR")
    protected Long versionNumber;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getRevisionNumber() {
		return revisionNumber;
	}

	public void setRevisionNumber(Long revisionNumber) {
		this.revisionNumber = revisionNumber;
	}

	public String getRevisionUserId() {
		return revisionUserId;
	}

	public void setRevisionUserId(String revisionUserId) {
		this.revisionUserId = revisionUserId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getGroup() {
		return group;
	}

	public void setGroup(String group) {
		this.group = group;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Timestamp getPostDate() {
		return postDate;
	}

	public void setPostDate(Timestamp postDate) {
		this.postDate = postDate;
	}

	public Timestamp getArchivedDate() {
		return archivedDate;
	}

	public void setArchivedDate(Timestamp archivedDate) {
		this.archivedDate = archivedDate;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}    
	
    public List<SubmissionAttribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<SubmissionAttribute> attributes) {
		this.attributes = attributes;
	}

}
