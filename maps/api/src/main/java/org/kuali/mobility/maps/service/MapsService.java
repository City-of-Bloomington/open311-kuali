package org.kuali.mobility.maps.service;

import org.kuali.mobility.maps.entity.Location;
import org.kuali.mobility.maps.entity.MapsGroup;

public interface MapsService {
	public void loadKml();
	public MapsGroup getMapsGroupById(String id);
	public Location getLocationById(String id);
	public String getArcGisUrl();
}
