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

public class MapsFormSearch {

	private String searchText;
	private String searchLatitude;
	private String searchLongitude;
	private String searchBuilding;
	private String searchCampus;
	private String venueId;
	private String locationName;

	public String getSearchCampus() {
		return searchCampus;
	}

	public void setSearchCampus(String searchCampus) {
		this.searchCampus = searchCampus;
	}

	private MapsGroup mapsGroup;
	
	public MapsGroup getMapsGroup() {
		return mapsGroup;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getVenueId() {
		return venueId;
	}

	public void setVenueId(String venueId) {
		this.venueId = venueId;
	}

	public void setMapsGroup(MapsGroup mapsGroup) {
		this.mapsGroup = mapsGroup;
	}

	public String getSearchLatitude() {
		return searchLatitude;
	}

	public void setSearchLatitude(String searchLatitude) {
		this.searchLatitude = searchLatitude;
	}

	public String getSearchLongitude() {
		return searchLongitude;
	}

	public void setSearchLongitude(String searchLongitude) {
		this.searchLongitude = searchLongitude;
	}

	public String getSearchBuilding() {
		return searchBuilding;
	}

	public void setSearchBuilding(String searchBuilding) {
		this.searchBuilding = searchBuilding;
	}

	public String getSearchText() {
		return searchText;
	}

	public void setSearchText(String searchText) {
		this.searchText = searchText;
	}
	
}
