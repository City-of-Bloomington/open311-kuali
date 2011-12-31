package org.kuali.mobility.util.mapper.domain;

public class Seat {
	private String lab;
	private String floor;
	private String buildingCode;
	private String availability;
	private String windowsAvailability;
	private String macAvailability;

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

	public String getBuildingCode() {
		return buildingCode;
	}

	public void setBuildingCode(String buildingCode) {
		this.buildingCode = buildingCode;
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

	public void setWindowsAvailability(String windowsAvailablity) {
		this.windowsAvailability = windowsAvailablity;
	}

	public String getMacAvailability() {
		return macAvailability;
	}

	public void setMacAvailability(String macAvailability) {
		this.macAvailability = macAvailability;
	}

}
