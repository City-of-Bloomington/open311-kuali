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

package org.kuali.mobility.conference.entity;

import java.io.Serializable;

public class Attendee implements Serializable, Comparable<Attendee> {

	private static final long serialVersionUID = -2826816981140315473L;

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String workPhone;
	private String cellPhone;
	private String institution;
	private String campus;
	private String title;
	private String workAddress1;
	private String workAddress2;
	private String workCity;
	private String workState;
	private String workZip;
	private String country;

	public String getId() {
    	return id;
    }

	public void setId(String id) {
    	this.id = id;
    }

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getWorkPhone() {
		return workPhone;
	}

	public void setWorkPhone(String workPhone) {
		this.workPhone = workPhone;
	}

	public String getCellPhone() {
		return cellPhone;
	}

	public void setCellPhone(String cellPhone) {
		this.cellPhone = cellPhone;
	}

	public String getInstitution() {
		return institution;
	}

	public void setInstitution(String institution) {
		this.institution = institution;
	}
	
	public String getCampus() {
    	return campus;
    }
	
	public String getTitle() {
    	return title;
    }

	public void setTitle(String title) {
    	this.title = title;
    }

	public void setCampus(String campus) {
    	this.campus = campus;
    }

	public String getWorkAddress1() {
		return workAddress1;
	}

	public void setWorkAddress1(String workAddress1) {
		this.workAddress1 = workAddress1;
	}

	public String getWorkAddress2() {
		return workAddress2;
	}

	public void setWorkAddress2(String workAddress2) {
		this.workAddress2 = workAddress2;
	}

	public String getWorkCity() {
		return workCity;
	}

	public void setWorkCity(String workCity) {
		this.workCity = workCity;
	}

	public String getWorkState() {
		return workState;
	}

	public void setWorkState(String workState) {
		this.workState = workState;
	}

	public String getWorkZip() {
		return workZip;
	}

	public void setWorkZip(String workZip) {
		this.workZip = workZip;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public int compareTo(Attendee that) {
		if (that == null) {
			return -1;
		}
		
		if (this.getLastName() == null && that.getLastName() == null) {
			return -1;
		}
		
		if (this.getLastName() != null && that.getLastName() == null) {
			return -1;
		}
		
		if (this.getLastName() == null && that.getLastName() != null) {
			return 1;
		}
		
		int lastNameCompare = this.getLastName().compareTo(that.getLastName());
		if (lastNameCompare == 0) {
			
			if (this.getFirstName() == null && that.getFirstName() == null) {
				return -1;
			}
			
			if (this.getFirstName() != null && that.getFirstName() == null) {
				return -1;
			}
			
			if (this.getFirstName() == null && that.getFirstName() != null) {
				return 1;
			}
			
			return this.getFirstName().compareTo(that.getFirstName());	
		}
		
		return lastNameCompare;
	}

}
