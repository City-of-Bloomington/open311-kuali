package org.kuali.mobility.dining.entity;

import java.io.Serializable;
import java.util.List;

public class PlaceByType implements Serializable{
	
	private static final long serialVersionUID = -2610898798132410119L;

	private String type;
	private List<Place> places;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<Place> getPlaces() {
		return places;
	}
	public void setPlaces(List<Place> places) {
		this.places = places;
	}
	
}
