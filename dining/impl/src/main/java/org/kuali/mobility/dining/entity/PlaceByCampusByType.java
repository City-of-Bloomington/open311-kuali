package org.kuali.mobility.dining.entity;

import java.io.Serializable;
import java.util.List;

public class PlaceByCampusByType implements Serializable {
	private static final long serialVersionUID = 7760010053507943067L;

	private String campus;
	private List<PlaceByType> placeByTypes;
	
	public String getCampus() {
		return campus;
	}
	public void setCampus(String campus) {
		this.campus = campus;
	}
	public List<PlaceByType> getPlaceByTypes() {
		return placeByTypes;
	}
	public void setPlaceByTypes(List<PlaceByType> placeByTypes) {
		this.placeByTypes = placeByTypes;
	}
	
	
}
