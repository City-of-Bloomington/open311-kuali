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

public interface Search {

	public abstract boolean isExactLastName();

	public abstract String getFirstName();

	public abstract void setFirstName(String firstName);

	public abstract String getLastName();

	public abstract void setLastName(String lastName);

	public abstract String getUserName();

	public abstract void setUserName(String userName);

	public abstract String getExactness();

	public abstract void setExactness(String exactness);

	public abstract String getStatus();

	public abstract void setStatus(String status);

	public abstract String getLocation();

	public abstract void setLocation(String location);

}