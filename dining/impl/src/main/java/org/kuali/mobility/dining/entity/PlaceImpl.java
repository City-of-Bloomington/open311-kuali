package org.kuali.mobility.dining.entity;

import java.io.Serializable;

public class PlaceImpl implements Place, Serializable {

	private static final long serialVersionUID = 7822282214636842701L;
	private String name;
	private String campus;
	private String type;
	private String location;
	//private List<String> menu;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}

	public Place compact () {
		Place p = new PlaceImpl();
		p.setName(getName());
		p.setLocation(getLocation());
		return p;
	}
}
