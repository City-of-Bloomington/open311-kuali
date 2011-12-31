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

package org.kuali.mobility.computerlabs.entity;

import java.io.Serializable;

/**
 * An object representing a computer lab.  This could be a room in a building on campus.
 * 
 * @author Kuali Mobility Team
 */
public class Lab implements Serializable, Comparable<Lab> {

	private static final long serialVersionUID = -372599891985133670L;

	private String lab;
	private String floor;
	private String building;
	private String buildingCode;
	private String campus;
	private String availability;
	private String windowsAvailability;
	private String macAvailability;
	private String softwareAvailability;
	private String floorplan;

	public Lab() {
	}

	@Override
	public int compareTo(Lab that) {
		if (this.getCampus() == null || that == null || that.getCampus() == null) {
			return -1;
		}
		return this.getCampus().compareTo(that.getCampus());
	}

	@Override
	public boolean equals(Object object) {
		if (object != null) {
			Lab lab = (Lab) object;
			if ((((this.getCampus() == null || this.getCampus().equals("")) && (lab.getCampus() == null || lab.getCampus().equals(""))) || (this.getCampus() != null && this.getCampus().equals(lab.getCampus()))) && 
				(((this.getLab() == null || this.getLab().equals("")) && (lab.getLab() == null || lab.getLab().equals(""))) || ( this.getLab() != null && this.getLab().equals(lab.getLab()))) && 
				(((this.getBuilding() == null || this.getBuilding().equals("")) && (lab.getBuilding() == null || lab.getBuilding().equals(""))) || (this.getBuilding() != null && this.getBuilding().equals(lab.getBuilding()))) && 
				(((this.getBuildingCode() == null || this.getBuildingCode().equals("")) && (lab.getBuildingCode() == null || lab.getBuildingCode().equals(""))) || (this.getBuildingCode() != null && this.getBuildingCode().equals(lab.getBuildingCode()))) && 
				(((this.getAvailability() == null || this.getAvailability().equals("")) && (lab.getAvailability() == null || lab.getAvailability().equals(""))) || (this.getAvailability() != null && this.getAvailability().equals(lab.getAvailability()))) && 
				(((this.getMacAvailability() == null || this.getMacAvailability().equals("")) && (lab.getMacAvailability() == null || lab.getMacAvailability().equals(""))) || (this.getMacAvailability() != null && this.getMacAvailability().equals(lab.getMacAvailability()))) &&
				(((this.getSoftwareAvailability() == null || this.getSoftwareAvailability().equals("")) && (lab.getSoftwareAvailability() == null || lab.getSoftwareAvailability().equals(""))) || (this.getSoftwareAvailability() != null && this.getSoftwareAvailability().equals(lab.getSoftwareAvailability()))) &&
				(((this.getFloorplan() == null || this.getFloorplan().equals("")) && (lab.getFloorplan() == null || lab.getFloorplan().equals(""))) || (this.getFloorplan() != null && this.getFloorplan().equals(lab.getFloorplan()))) &&
				(((this.getWindowsAvailability() == null || this.getWindowsAvailability().equals("")) && (lab.getWindowsAvailability() == null || lab.getWindowsAvailability().equals(""))) || (this.getWindowsAvailability() != null && this.getWindowsAvailability().equals(lab.getWindowsAvailability())))) {
				return true;
			}
		}
		return false;
	}
	
	public String getLab() {
		return lab;
	}

	public void setLab(String lab) {
		this.lab = lab;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getAvailability() {
		return availability;
	}

	public void setAvailability(String availability) {
		this.availability = availability;
	}

	public String getWindowsAvailability() {
		return windowsAvailability;
	}

	public void setWindowsAvailability(String windowsAvailability) {
		this.windowsAvailability = windowsAvailability;
	}

	public String getMacAvailability() {
		return macAvailability;
	}

	public void setMacAvailability(String macAvailability) {
		this.macAvailability = macAvailability;
	}

	public String getSoftwareAvailability() {
		return softwareAvailability;
	}

	public void setSoftwareAvailability(String softwareAvailability) {
		this.softwareAvailability = softwareAvailability;
	}

	public String getFloorplan() {
		return floorplan;
	}

	public void setFloorplan(String floorplan) {
		this.floorplan = floorplan;
	}

}
