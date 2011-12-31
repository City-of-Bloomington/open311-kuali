package org.kuali.mobility.maps.service;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.mobility.maps.entity.Location;
import org.kuali.mobility.maps.entity.MapsGroup;

import de.micromata.opengis.kml.v_2_2_0.Coordinate;
import de.micromata.opengis.kml.v_2_2_0.Document;
import de.micromata.opengis.kml.v_2_2_0.Feature;
import de.micromata.opengis.kml.v_2_2_0.Folder;
import de.micromata.opengis.kml.v_2_2_0.Kml;
import de.micromata.opengis.kml.v_2_2_0.Placemark;
import de.micromata.opengis.kml.v_2_2_0.Point;
import de.micromata.opengis.kml.v_2_2_0.xal.AddressLine;
import de.micromata.opengis.kml.v_2_2_0.xal.AddressLines;

public class MapsServiceImpl implements MapsService {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MapsServiceImpl.class);
	
	private String kmlUrl;
	private String arcGisUrl;
	
	private Map<String, MapsGroup> mapsGroups;
	private Map<String, Location> locations;
	
	private boolean dataLoaded = false;
	
	public MapsServiceImpl() {
		mapsGroups = new HashMap<String, MapsGroup>();
		locations = new HashMap<String, Location>();
	}

	@Override
	public void loadKml() {
		try {
			URL url = new URL(kmlUrl);
			final Kml kml = Kml.unmarshal(url.openStream());
			final Document document = (Document) kml.getFeature();
			List<Feature> features = document.getFeature();
			for (Feature feature : features) {
				if (feature instanceof Folder) {
					parseFolder((Folder)feature);
				}
			}
			dataLoaded = true;
		} catch (Exception e) {
			LOG.error("Error reading maps kml data", e);
		}
	}
	
	@Override
	public MapsGroup getMapsGroupById(String id) {
		if (!dataLoaded) loadKml();
		return mapsGroups.get(id);
	}
	
	@Override
	public Location getLocationById(String id) {
		if (!dataLoaded) loadKml();
		return locations.get(id);
	}
	
	private MapsGroup parseFolder(Folder folder) {
		if (folder.getId() != null) {
			MapsGroup mapsGroup = new MapsGroup();
			mapsGroup.setId(folder.getId());
			mapsGroup.setActive(true);
			mapsGroup.setName(folder.getName());
			List<Feature> features = folder.getFeature();
			for (Feature feature : features) {
				if (feature instanceof Folder) {
					mapsGroup.getMapsGroupChildren().add(parseFolder((Folder)feature));
				} else if (feature instanceof Placemark) {
					mapsGroup.getMapsLocations().add(parseLocation((Placemark)feature));
				}
			}
			mapsGroups.put(mapsGroup.getId(), mapsGroup);
			return mapsGroup;
		}
		return null;
	}
	
	private Location parseLocation(Placemark placemark) {
		if (placemark.getId() != null) {
			Location location = new Location();
			location.setActive(true);
			location.setId(placemark.getId());
			location.setName(placemark.getName());
			location.setDescription(placemark.getDescription());
			if (placemark.getGeometry() instanceof Point) {
				Point point = (Point)placemark.getGeometry();
				Coordinate coordinates = point.getCoordinates().get(0);
				location.setLatitude(coordinates.getLatitude());
				location.setLongitude(coordinates.getLongitude());
			}
			if (placemark.getXalAddressDetails() != null) {
				AddressLines addressLines = placemark.getXalAddressDetails().getAddressLines();
				for (AddressLine line : addressLines.getAddressLine()) {
					if (line.getUnderscore().equals("Street")) {
						location.setStreet(line.getContent());
					} else if (line.getUnderscore().equals("City")) {
						location.setCity(line.getContent());
					} else if (line.getUnderscore().equals("State")) {
						location.setState(line.getContent());
					} else if (line.getUnderscore().equals("Post Code")) {
						location.setZip(line.getContent());
					}
				}
			}
			locations.put(location.getId(), location);
			return location;
		}
		return null;
	}
	
	public String getKmlUrl() {
		return kmlUrl;
	}

	public void setKmlUrl(String kmlUrl) {
		this.kmlUrl = kmlUrl;
	}

	public String getArcGisUrl() {
		return arcGisUrl;
	}

	public void setArcGisUrl(String arcGisUrl) {
		this.arcGisUrl = arcGisUrl;
	}
}
