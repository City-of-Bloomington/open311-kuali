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

public class SearchImpl implements Search {
	private String firstName;
	private String lastName;
	private String userName;
	
	private String exactness = "starts";
	private String status;
	private String location;
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#isExactLastName()
	 */
	@Override
	public boolean isExactLastName() {
		return "exact".equals(exactness);
	}
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#getUserName()
	 */
	@Override
	public String getUserName() {
		return userName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#getExactness()
	 */
	@Override
	public String getExactness() {
		return exactness;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#setExactness(java.lang.String)
	 */
	@Override
	public void setExactness(String exactness) {
		this.exactness = exactness;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#getStatus()
	 */
	@Override
	public String getStatus() {
		return status;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#setStatus(java.lang.String)
	 */
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#getLocation()
	 */
	@Override
	public String getLocation() {
		return location;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.Search#setLocation(java.lang.String)
	 */
	@Override
	public void setLocation(String location) {
		this.location = location;
	}
}
