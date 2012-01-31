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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PersonImpl implements Serializable, Person {

	private static final long serialVersionUID = -2125754188712894101L;
	
	private String firstName;
	private String lastName;
	private String displayName;
	private String userName;
	
	private List<String> locations;
	private List<String> affiliations;
	private List<String> departments;
	
	private String email;
	private String phone;
	private String address;
	
	public PersonImpl() {
		locations = new ArrayList<String>();
		affiliations = new ArrayList<String>();
		departments = new ArrayList<String>();
	}
	
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getHashedUserName()
	 */
	@Override
	public String getHashedUserName() {
		return Integer.toString(Math.abs(userName.hashCode()));
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setFirstName(java.lang.String)
	 */
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setLastName(java.lang.String)
	 */
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getUserName()
	 */
	@Override
	public String getUserName() {
		return userName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setUserName(java.lang.String)
	 */
	@Override
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getDisplayName()
	 */
	@Override
	public String getDisplayName() {
		return displayName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setDisplayName(java.lang.String)
	 */
	@Override
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getEmail()
	 */
	@Override
	public String getEmail() {
		return email;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setEmail(java.lang.String)
	 */
	@Override
	public void setEmail(String email) {
		this.email = email;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getPhone()
	 */
	@Override
	public String getPhone() {
		return phone;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setPhone(java.lang.String)
	 */
	@Override
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getAddress()
	 */
	@Override
	public String getAddress() {
		return address;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setAddress(java.lang.String)
	 */
	@Override
	public void setAddress(String address) {
		this.address = address;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getLocations()
	 */
	@Override
	public List<String> getLocations() {
		return locations;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setLocations(java.util.List)
	 */
	@Override
	public void setLocations(List<String> locations) {
		this.locations = locations;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getAffiliations()
	 */
	@Override
	public List<String> getAffiliations() {
		return affiliations;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setAffiliations(java.util.List)
	 */
	@Override
	public void setAffiliations(List<String> affiliations) {
		this.affiliations = affiliations;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#getDepartments()
	 */
	@Override
	public List<String> getDepartments() {
		return departments;
	}
	/* (non-Javadoc)
	 * @see org.kuali.mobility.people.entity.People#setDepartments(java.util.List)
	 */
	@Override
	public void setDepartments(List<String> departments) {
		this.departments = departments;
	}
}
