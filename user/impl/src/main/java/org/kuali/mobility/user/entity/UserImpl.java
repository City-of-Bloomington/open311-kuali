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
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity(name = "User")
@Table(name = "KME_USR_T")
public class UserImpl implements User, Serializable {

	private static final long serialVersionUID = -2720266083487368287L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
	@Column(name = "ID")
	private Long principalId;

	@Column(name = "PRNCPL_NM")
	private String principalName;

	@Column(name = "FRST_LGN_DT")
	private Timestamp firstLogin;

	@Column(name = "LST_LGN_DT")
	private Timestamp lastLogin;

	@Column(name = "DEV_ID")
	private String deviceId;

	@Version
	@Column(name = "VER_NBR")
	protected Long versionNumber;

	@Transient
	private Map<String, String> userAttributes;

	@Transient
	private String primaryCampus;

	@Transient
	private String email;

	@Transient
	private List<String> groups;

	@Transient
	private List<String> affiliations;

	@Transient
	private String viewCampus;

	@Transient
	private boolean publicUser;

	@Transient
	private final UserCache cache = new UserCache();

	@Transient
	private String ipAddress;

	public UserImpl() {
		publicUser = false;
		userAttributes = new HashMap<String, String>();
		groups = new ArrayList<String>();
		affiliations = new ArrayList<String>();
	}

	public UserImpl(boolean publicUser) {
		if (publicUser) {
			principalId = -1L;
		}
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

	public Long getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(Long principalId) {
		this.principalId = principalId;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
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

	@Override
	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getIpAddress() {
		return ipAddress;
	}
	
	@Override
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@Override
	public UserCacheObject getFromCache(String key) {
		return cache.get(key);
	}

	@Override
	public void putInCache(String key, Object item) {
		cache.put(key, item);
	}

	@Override
	public void removeFromCache(String key) {
		cache.remove(key);
	}

	@Override
	public void invalidateUser() {
		// TODO: Implement this method to invalidate the active user object in the session, purging all data.
	}

	@Override
	public void setRequestURL(String url) {
		this.setUserAttribute( "service", url );
	}

	@Override
	public String getRequestURL() {
		return this.getUserAttribute( "service" );
	}
}
