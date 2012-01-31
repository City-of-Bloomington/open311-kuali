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

public interface Person {

	public abstract String getHashedUserName();

	public abstract String getFirstName();

	public abstract void setFirstName(String firstName);

	public abstract String getLastName();

	public abstract void setLastName(String lastName);

	public abstract String getUserName();

	public abstract void setUserName(String userName);

	public abstract String getDisplayName();

	public abstract void setDisplayName(String displayName);

	public abstract String getEmail();

	public abstract void setEmail(String email);

	public abstract String getPhone();

	public abstract void setPhone(String phone);

	public abstract String getAddress();

	public abstract void setAddress(String address);

	public abstract List<String> getLocations();

	public abstract void setLocations(List<String> locations);

	public abstract List<String> getAffiliations();

	public abstract void setAffiliations(List<String> affiliations);

	public abstract List<String> getDepartments();

	public abstract void setDepartments(List<String> departments);

}