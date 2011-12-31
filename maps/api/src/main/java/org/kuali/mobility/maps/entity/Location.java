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
 
package org.kuali.mobility.maps.entity;

import java.io.Serializable;

import flexjson.JSONSerializer;

/*
 * Modifying the Location object? Remember to update the copy method.
 */
public class Location implements Serializable {

	private static final long serialVersionUID = -2588912315204722978L;

	private String id;
	
	private String name;
	
	private String description;
	
	private String street;
	
	private String city;
	
	private String state;
	
	private String zip;
	
	private Double latitude;
	
	private Double longitude;
	
	private boolean active;

	public Location() {
		
	}
	
	public Location(String id, String name, String description, String street, String city, String state) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.street = street;
		this.city = city;
		this.state = state;
	}
	
    public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Location copy() {
		Location location = new Location();
		location.setActive(this.active);
		if (city != null) {
			location.setCity(new String(city));	
		}
		if (id != null) {
			location.setId(new String(this.id));	
		}
		if (latitude != null) {
			location.setLatitude(new Double(this.latitude));	
		}
		if (longitude != null) {
			location.setLongitude(new Double(this.longitude));	
		}
		if (name != null) {
			location.setName(new String(this.name));	
		}
		if (description != null) {
			location.setDescription(new String(this.description));	
		}
		if (state != null) {
			location.setState(new String(this.state));	
		}
		if (street != null) {
			location.setStreet(new String(this.street));	
		}
		if (zip != null) {
			location.setZip(new String(this.zip));	
		}
		return location;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
}
