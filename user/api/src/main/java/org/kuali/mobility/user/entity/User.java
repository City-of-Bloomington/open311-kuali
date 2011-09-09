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

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

public interface User {

	String getViewCampus();
	void setViewCampus(String campus);

	boolean isPublicUser();

	Long getPrincipalId();
	String getPrincipalName();
	String getEmail();
	public String getIpAddress();
	public void setIpAddress(String ipAddress);
	void setFirstLogin(Timestamp firstLogin);
	void setLastLogin(Timestamp firstLogin);

	void setPrincipalName(String userId);
	void setEmail(String email);

    List<String> getGroups();
    List<String> getAffiliations();
    String getPrimaryCampus();
    
    void setGroups(List<String> groups);
    void setAffiliations(List<String> affiliations);
    void setPrimaryCampus(String primaryCampus);
    Map<String, String> getUserAttributes();
    void setUserAttributes(Map<String, String> userAttributes);
    String getUserAttribute(String key);
    void setUserAttribute(String key, String value);
    void removeUserAttribute(String key);
    
    boolean isMember(String groupName);
    boolean isStudent();
    boolean isFaculty();
    boolean isStaff();
    boolean isAlumnus();
    
    UserCacheObject getFromCache(String key);
    void removeFromCache(String key);
    void putInCache(String key, Object item);
}
