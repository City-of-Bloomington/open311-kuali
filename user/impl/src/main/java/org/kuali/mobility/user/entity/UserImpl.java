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

package org.kuali.mobility.user.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity(name="User")
@Table(name = "USR_T")
public class UserImpl implements User, Serializable {

	private static final long serialVersionUID = -2720266083487368287L;

	@Id
	@SequenceGenerator(name = "user_sequence", sequenceName = "SEQ_USR_T", initialValue = 1000, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_sequence")
	@Column(name = "PERSON_ID")
	private Long guid;

	@Column(name = "EMPL_ID")
	private String emplId;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "FIRST_LOGIN_DATE")
	private Timestamp firstLogin;

	@Column(name = "LAST_LOGIN_DATE")
	private Timestamp lastLogin;

	@Column(name = "CAMPUS")
	private String campus;

	@Column(name = "DEVICE_ID")
	private String deviceId;
	
	@Column(name = "MAIL")
	private String email;

	@Column(name = "CAS_PAIR_DATE")
	private Timestamp pairDate;

	@Version
	@Column(name = "VER_NBR")
	protected Long versionNumber;
	
	 @Transient
	    private Map<String, String> userAttributes;
	    
	    @Transient
	    private String primaryCampus;
	    
	    @Transient
	    private List<String> groups;
	    
	    @Transient
	    private List<String> affiliations;
	    

	private String viewCampus;

	private boolean publicUser;

	public UserImpl() {
		this.publicUser = false;
    	userAttributes = new HashMap<String, String>();
    	groups = new ArrayList<String>();
    	affiliations = new ArrayList<String>();
	}

	public UserImpl(boolean publicUser) {
    	userAttributes = new HashMap<String, String>();
    	groups = new ArrayList<String>();
    	affiliations = new ArrayList<String>();
		this.publicUser = publicUser;
	}

	public boolean isPublicUser() {
		return publicUser;
	}

	public void setPublicUser(boolean publicUser) {
		this.publicUser = publicUser;
	}
    
	public String getViewCampus() {
		return viewCampus;
	}

	public void setViewCampus(String viewCampus) {
		this.viewCampus = viewCampus;
	}

	public Long getGuid() {
		return guid;
	}

	public void setGuid(Long guid) {
		this.guid = guid;
	}

	public String getEmplId() {
		return emplId;
	}

	public void setEmplId(String emplId) {
		this.emplId = emplId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Timestamp getFirstLogin() {
		return firstLogin;
	}

	public void setFirstLogin(Timestamp firstLogin) {
		this.firstLogin = firstLogin;
	}

	public Timestamp getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Timestamp lastLogin) {
		this.lastLogin = lastLogin;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Timestamp getPairDate() {
		return pairDate;
	}

	public void setPairDate(Timestamp pairDate) {
		this.pairDate = pairDate;
	}
	@Override
	public Map<String, String> getUserAttributes() {		
		return userAttributes;
	}

	@Override
	public void setUserAttributes(Map<String, String> userAttributes) {
		this.userAttributes = userAttributes;
	}

	@Override
	public String getUserAttribute(String key) {		
		return userAttributes.get(key);
	}

	@Override
	public void setUserAttribute(String key, String value) {
		userAttributes.put(key, value);
	}
	
	@Override
    public void removeUserAttribute(String key) {
		userAttributes.remove(key);
	}

	@Override
	public String getPrimaryCampus() {
		return primaryCampus;
	}

	@Override
	public void setPrimaryCampus(String primaryCampus) {
		this.primaryCampus = primaryCampus;
	}

	@Override
	public List<String> getGroups() {
		return Collections.unmodifiableList(groups);
	}

	@Override
	public List<String> getAffiliations() {
		return Collections.unmodifiableList(affiliations);
	}

	@Override
	public boolean isMember(String groupName) {
		return groups.contains(groupName);
	}

	@Override
	public boolean isStudent() {
		return affiliations.contains("Enrolled");
	}

	@Override
	public boolean isFaculty() {
		return affiliations.contains("Faculty");
	}

	@Override
	public boolean isStaff() {
		return affiliations.contains("Staff");
	}

	@Override
	public boolean isAlumnus() {
		return affiliations.contains("Alumni");
	}

	@Override
	public void setGroups(List<String> groups) {
		this.groups = groups;
	}
	@Override
	public void setAffiliations(List<String> affiliations) {
		this.affiliations = affiliations;
	}

	@Override
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
