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

package org.kuali.mobility.tours.controllers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JSONSerializer;
import net.sf.json.JsonConfig;

import org.kuali.mobility.tours.entity.POI;
import org.kuali.mobility.tours.entity.Tour;
import org.kuali.mobility.tours.service.ToursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import de.micromata.opengis.kml.v_2_2_0.Kml;

@Controller 
@RequestMapping("/tours")
public class ToursController {
	
	@Autowired
    private ToursService toursService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel) {
    	uiModel.addAttribute("tours", toursService.findAllTours());
    	return "tours/home";
    }
    
    @RequestMapping(value = "publish", method = RequestMethod.GET)
    public String publish(Model uiModel) {
    	uiModel.addAttribute("tours", toursService.findAllTours());
    	uiModel.addAttribute("pois", toursService.findAllCommonPOI());
    	return "tours/index";
    }
    
	@RequestMapping(value = "/view/{tourId}", method = RequestMethod.GET)
    public String viewTour(Model uiModel, @PathVariable("tourId") long tourId) {
    	Tour tour = toursService.findTourById(tourId);
    	uiModel.addAttribute("tour", tour);	
    	return "tours/tour";
    }
	
	@RequestMapping(value = "/details/{poiId}", method = RequestMethod.GET)
    public String viewPoiDetails(Model uiModel, @PathVariable("poiId") long poiId) {
    	POI poi = toursService.findPoiById(poiId);
    	uiModel.addAttribute("poi", poi);	
    	return "tours/details";
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/map/{tourId}", method = RequestMethod.GET)
    public String viewTourMap(Model uiModel, @PathVariable("tourId") long tourId) {
    	Tour tour = toursService.findTourById(tourId);
    	uiModel.addAttribute("tour", tour);
    	
    	JsonConfig config = new JsonConfig();
    	config.registerPropertyExclusion(POI.class, "tour");
    	config.registerPropertyExclusion(POI.class, "versionNumber");
    	config.registerPropertyExclusion(POI.class, "tourId");
    	JSONObject json =  (JSONObject) JSONSerializer.toJSON(tour, config);
    	JSONArray pointsOfInterest = json.getJSONArray("pointsOfInterest");
    	for (Iterator<JSONObject> iter = pointsOfInterest.iterator(); iter.hasNext();) {
    		try {
    			JSONObject poi = iter.next();
    			String mediaJson = poi.getString("media");
    			if (!mediaJson.isEmpty()) {
	    			JSONArray media = (JSONArray) JSONSerializer.toJSON(mediaJson);
					poi.element("media", media);
    			} else {
    				poi.element("media", new JSONArray());
    			}
			} catch (Exception e) {}
    	}
    	uiModel.addAttribute("tourJson", json.toString());
    	
    	return "tours/map";
    }
    
    @RequestMapping(value = "new", method = RequestMethod.GET)
    public String edit(Model uiModel) {
    	return "tours/edit";
    }
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/edit/{tourId}", method = RequestMethod.GET)
    public String editTour(Model uiModel, @PathVariable("tourId") long tourId) {
    	Tour tour = toursService.findTourById(tourId);
    	JsonConfig config = new JsonConfig();
    	config.registerPropertyExclusion(POI.class, "tour");
    	config.registerPropertyExclusion(POI.class, "versionNumber");
    	config.registerPropertyExclusion(POI.class, "tourId");
    	JSONObject json =  (JSONObject) JSONSerializer.toJSON(tour, config);
    	JSONArray pointsOfInterest = json.getJSONArray("pointsOfInterest");
    	for (Iterator<JSONObject> iter = pointsOfInterest.iterator(); iter.hasNext();) {
    		try {
    			JSONObject poi = iter.next();
    			String mediaJson = poi.getString("media");
    			if (!mediaJson.isEmpty()) {
	    			JSONArray media = (JSONArray) JSONSerializer.toJSON(mediaJson);
					poi.element("media", media);
    			} else {
    				poi.element("media", new JSONArray());
    			}
			} catch (Exception e) {}
    	}
    	uiModel.addAttribute("tourJson", json.toString());
    	
    	List<POI> definedPois = toursService.findAllCommonPOI();
    	pointsOfInterest =  (JSONArray) JSONSerializer.toJSON(definedPois, config);
    	for (Iterator<JSONObject> iter = pointsOfInterest.iterator(); iter.hasNext();) {
    		try {
    			JSONObject poi = iter.next();
    			String mediaJson = poi.getString("media");
    			if (!mediaJson.isEmpty()) {
	    			JSONArray media = (JSONArray) JSONSerializer.toJSON(mediaJson);
					poi.element("media", media);
    			} else {
    				poi.element("media", new JSONArray());
    			}
			} catch (Exception e) {}
    	}
    	uiModel.addAttribute("pois", pointsOfInterest.toString());
    	return "tours/edit";
    }
    
    @RequestMapping(value = "edit", method = RequestMethod.POST)
    public String save(@RequestParam("data") String postData,  Model uiModel) {
    	Tour tour = convertTourFromJson(postData);
    	toursService.saveTour(tour);
    	return publish(uiModel);
    }
    
    @RequestMapping(value = "delete/{tourId}", method = RequestMethod.GET)
    public String delete(@PathVariable("tourId") Long tourId, Model uiModel) {
    	toursService.deleteTourById(tourId);
    	return index(uiModel);
    }
    
    
    @RequestMapping(value = "poi/new", method = RequestMethod.GET)
    public String editPoi(Model uiModel) {
    	return "tours/editPoi";
    }
    
	@RequestMapping(value = "poi/edit/{poiId}", method = RequestMethod.GET)
    public String editPoi(Model uiModel, @PathVariable("poiId") long poiId) {
    	POI poi = toursService.findPoiById(poiId);
    	JsonConfig config = new JsonConfig();
    	config.registerPropertyExclusion(POI.class, "tour");
    	config.registerPropertyExclusion(POI.class, "tourId");
    	JSONObject json =  (JSONObject) JSONSerializer.toJSON(poi, config);
		String mediaJson = json.getString("media");
		if (!mediaJson.isEmpty()) {
			JSONArray media = (JSONArray) JSONSerializer.toJSON(mediaJson);
			json.element("media", media);
		} else {
			json.element("media", new JSONArray());
		}
    	uiModel.addAttribute("poiJson", json.toString());
    	return "tours/editPoi";
    }
    
    @RequestMapping(value = "poi/edit", method = RequestMethod.POST)
    public String savePoi(@RequestParam("data") String postData,  Model uiModel) {
    	POI poi = convertPoiFromJson(postData);
    	toursService.savePoi(poi);
    	return index(uiModel);
    }
    
    @RequestMapping(value = "poi/delete/{poiId}", method = RequestMethod.GET)
    public String deletePoi(@PathVariable("poiId") Long poiId, Model uiModel) {
    	toursService.deletePoiById(poiId);
    	return index(uiModel);
    }
    
    @RequestMapping(value = "kml/{tourId}", method = RequestMethod.GET)
    public String downloadKml(@PathVariable("tourId") Long tourId, HttpServletResponse response) {
    	Tour tour = toursService.findTourById(tourId);
    	Kml kml = toursService.createTourKml(tour);
    	OutputStream os = new ByteArrayOutputStream();
    	try {
			kml.marshal(os);
		} catch (Exception e) {
		}
    	byte [] data = os.toString().getBytes();
    	response.setContentType("application/vnd.google-earth.kml+xml");
		response.setContentLength(data.length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + tour.getName() + ".kml\"" );
		try {
			response.getOutputStream().write(data, 0, data.length);
		} catch (IOException e) {
		}
    	return null;
    }
    
    @RequestMapping(value = "kml", method = RequestMethod.POST)
    public String downloadKml(@RequestParam("data") String postData, @RequestParam("name") String name, HttpServletResponse response) {
    	String newline = System.getProperty("line.separator");
    	byte [] data = postData.replace("<EOL>", newline).getBytes();
    	response.setContentType("application/vnd.google-earth.kml+xml");
		response.setContentLength(data.length);
		response.setHeader("Content-Disposition", "attachment; filename=\"" + name + ".kml\"" );
		try {
			response.getOutputStream().write(data, 0, data.length);
		} catch (IOException e) {
		}
    	return null;
    }
    
    
    @SuppressWarnings("unchecked")
	private Tour convertTourFromJson(String json) {
    	Tour tour = new Tour();
    	JSONObject tourJson = (JSONObject) JSONSerializer.toJSON(json);
    	
    	try {
    		tour.setTourId(tourJson.getLong("id"));
    	} catch (Exception e) {
    		tour.setTourId(null);
    	}
    	tour.setName(tourJson.getString("name"));
    	tour.setDescription(tourJson.getString("description"));
    	tour.setPath(tourJson.getString("path"));
    	try {
    		tour.setVersionNumber(tourJson.getLong("version"));
    	} catch (Exception e) {
    		tour.setVersionNumber(null);
    	}
    	
    	JSONArray pointsOfInterest = tourJson.getJSONArray("POIs");
		List<POI> POIs = new ArrayList<POI>();
		for (Iterator<JSONObject> iter = pointsOfInterest.iterator(); iter.hasNext();) {
			JSONObject pointOfInterest = iter.next();
			POI poi = new POI();
			
			try {
				poi.setPoiId(pointOfInterest.getLong("poiId"));
	    	} catch (Exception e) {
	    		poi.setPoiId(null);
	    	}
			
			poi.setName(pointOfInterest.getString("name"));
			try {
				poi.setDescription(pointOfInterest.getString("description"));
	    	} catch (Exception e) {
	    		poi.setDescription(null);
	    	}
			try {
				poi.setLocationId(pointOfInterest.getString("id"));
	    	} catch (Exception e) {
	    		poi.setLocationId(null);
	    	}
			poi.setType(pointOfInterest.getString("type"));
			
			JSONObject location = pointOfInterest.getJSONObject("location");
			poi.setLatitude(location.getDouble("lat"));
			poi.setLongitude(location.getDouble("lng"));
			try {
				poi.setMedia(pointOfInterest.getString("media"));
			} catch (Exception e) {}
			try {
				poi.setUrl(pointOfInterest.getString("url"));
			} catch (Exception e) {}
			poi.setTourId(tour.getTourId());
			poi.setTour(tour);
			POIs.add(poi);
		}
    	tour.setPointsOfInterest(POIs);
    	
    	return tour;
    }
    
	private POI convertPoiFromJson(String json) {
    	POI poi = new POI();
    	JSONObject pointOfInterest = (JSONObject) JSONSerializer.toJSON(json);
    	
		try {
			poi.setPoiId(pointOfInterest.getLong("id"));
    	} catch (Exception e) {
    		poi.setPoiId(null);
    	}
		try {
			poi.setVersionNumber(pointOfInterest.getLong("version"));
    	} catch (Exception e) {
    		poi.setVersionNumber(null);
    	}
		
		poi.setName(pointOfInterest.getString("name"));
		try {
			poi.setDescription(pointOfInterest.getString("description"));
    	} catch (Exception e) {
    		poi.setDescription(null);
    	}
		try {
			poi.setLocationId(pointOfInterest.getString("locationId"));
    	} catch (Exception e) {
    		poi.setLocationId(null);
    	}
		try {
			poi.setUrl(pointOfInterest.getString("url"));
    	} catch (Exception e) {
    		poi.setDescription(null);
    	}
		poi.setType(pointOfInterest.getString("type"));
		poi.setTour(null);
		poi.setTourId(null);
		
		JSONObject location = pointOfInterest.getJSONObject("location");
		poi.setLatitude(location.getDouble("latitude"));
		poi.setLongitude(location.getDouble("longitude"));
		try {
			poi.setMedia(pointOfInterest.getString("media"));
		} catch (Exception e) {}

    	return poi;
    }
}
