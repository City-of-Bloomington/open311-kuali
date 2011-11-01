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

package org.kuali.mobility.coursedescriptions.entity;

public class Course {

	private String fullTitle;
	private String title;
	private String departmentCode;
	private String number;
	private String credits;
	private String maxCredits;
	private String prerequisite;
	private String corequisite;
	private String description;
	
	public Course(String title, String departmentCode, String number, String credits, String maxCredits, String prerequisite, String corequisite, String description) {
		this.title = title;
		this.departmentCode = departmentCode;
		this.number = number;
		this.credits = credits;
		this.maxCredits = maxCredits;
		this.prerequisite = prerequisite;
		this.corequisite = corequisite;
		if (description != "") {
			this.description = description;
		} else {
			this.description = "No description at this time.";
		}
		String temp = "";
		if (!maxCredits.isEmpty()){
			temp = "-" + maxCredits;
		} 
		this.fullTitle = departmentCode + " " + number + " " + title + " ("+ credits + temp + " cr.)";
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFullTitle() {
		return fullTitle;
	}

	public void setFullTitle(String fullTitle) {
		this.fullTitle = fullTitle;
	}

	public String getDepartmentCode() {
		return departmentCode;
	}

	public void setDepartmentCode(String departmentCode) {
		this.departmentCode = departmentCode;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getCredits() {
		return credits;
	}

	public void setCredits(String credits) {
		this.credits = credits;
	}

	public String getMaxCredits() {
		return maxCredits;
	}

	public void setMaxCredits(String maxCredits) {
		this.maxCredits = maxCredits;
	}

	public String getPrerequisite() {
		return prerequisite;
	}

	public void setPrerequisite(String prerequisite) {
		this.prerequisite = prerequisite;
	}

	public String getCorequisite() {
		return corequisite;
	}

	public void setCorequisite(String corequisite) {
		this.corequisite = corequisite;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
