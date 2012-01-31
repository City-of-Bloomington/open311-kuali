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
package org.kuali.mobility.people.entity;

import java.util.List;

public interface Person extends DirectoryEntry {

	public String getHashedUserName();

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getUserName();

	public void setUserName(String userName);

	public String getDisplayName();

	public void setDisplayName(String displayName);

	public String getEmail();

	public void setEmail(String email);

	public String getPhone();

	public void setPhone(String phone);

	public String getAddress();

	public void setAddress(String address);

	public List<String> getLocations();

	public void setLocations(List<String> locations);

	public List<String> getAffiliations();

	public void setAffiliations(List<String> affiliations);

	public List<String> getDepartments();

	public void setDepartments(List<String> departments);

}