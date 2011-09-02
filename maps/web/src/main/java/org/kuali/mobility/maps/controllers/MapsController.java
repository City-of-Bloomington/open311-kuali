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

package org.kuali.mobility.maps.controllers;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.DefaultHttpMethodRetryHandler;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.params.HttpClientParams;
import org.apache.commons.httpclient.params.HttpConnectionParams;
import org.apache.commons.httpclient.params.HttpMethodParams;
import org.kuali.mobility.configparams.service.ConfigParamService;
import org.kuali.mobility.maps.entity.Location;
import org.kuali.mobility.maps.entity.LocationSort;
import org.kuali.mobility.maps.entity.MapsFormSearch;
import org.kuali.mobility.maps.entity.MapsFormSearchResult;
import org.kuali.mobility.maps.entity.MapsFormSearchResultContainer;
import org.kuali.mobility.maps.entity.MapsGroup;
import org.kuali.mobility.maps.service.LocationService;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;

@Controller 
@RequestMapping("/maps")
public class MapsController {
    
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(MapsController.class);
	
	private static final String FOURSQUARE_CLIENT_ID = "Maps.Foursquare.Client.Id";
	private static final String FOURSQUARE_CLIENT_SECRET = "Maps.Foursquare.Client.Secret";
	
    @Autowired
    private LocationService locationService;
    public void setLocationService(LocationService locationService) {
        this.locationService = locationService;
    }
    
    @Autowired
	private ConfigParamService configParamService;
	public void setConfigParamService(ConfigParamService configParamService) {
		this.configParamService = configParamService;
	}
    
	/*
	 * Initial view
	 */
    @RequestMapping(method = RequestMethod.GET)
    public String getHome(Model uiModel, HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	String selectedCampus = "UA";
    	if (user.getViewCampus() == null) {
    		return "redirect:/campus?toolName=maps";
    	} else {
    		selectedCampus = user.getViewCampus();
    	}
    	MapsFormSearch mapsFormSearch = new MapsFormSearch();
    	mapsFormSearch.setSearchCampus(selectedCampus);
    	uiModel.addAttribute("mapsearchform", mapsFormSearch);
    	return "maps/home";
    }

    @RequestMapping(value = "/location", method = RequestMethod.GET)
    public Object get(Model uiModel, @RequestParam(required = false) String latitude, 
    		@RequestParam(required = false) String longitude) {
//        List<Location> locations = locationService.getLocationsByBuildingCode(buildingCode);
//        if (locations == null || locations.size() < 1) {
//            // Error?
//        } else {
//        	Location location = locations.get(0);
//        	uiModel.addAttribute("location", location);
//        }
        return "maps/location";
    }
    
    @RequestMapping(value = "/form", method = RequestMethod.GET)
    public Object getForm(Model uiModel) {
		MapsFormSearch form = new MapsFormSearch();
//		MapsGroup group = locationService.getMapsGroupByCode(groupCode);
    	uiModel.addAttribute("mapsearchform", form);
        return "maps/formtest";
    }
    
/*    @RequestMapping(value = "/{buildingCode}", method = RequestMethod.GET)
    @ResponseBody
    public Object get(Model uiModel, @PathVariable("buildingCode") String buildingCode) {
        List<Location> locations = locationService.getLocationsByBuildingCode(buildingCode);
        if (locations == null || locations.size() < 1) {
            // Error?
        } else {
        	Location location = locations.get(0);
        	uiModel.addAttribute("location", location);
        }
        return "maps/location";
    }*/
    
    /*
     * Group with Buildings JSON, get by Group Code
     */
    @RequestMapping(value = "/group/{groupCode}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Object getBuildings(@PathVariable("groupCode") String groupCode) {
    	try {
    		MapsGroup group = locationService.getMapsGroupByCode(groupCode);
    		return group.toJson();
    	} catch (Exception e) {
    		LOG.error(e.getMessage(), e);
    	}
    	return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    
    /*
     * Buildings JSON, get by Building Code
     */
    @RequestMapping(value = "/building/{buildingCode}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public Object get(@PathVariable("buildingCode") String buildingCode) {
        List<Location> locations = locationService.getLocationsByBuildingCode(buildingCode);
        if (locations == null || locations.size() < 1) {
            // Error?
        } else {
        	Location location = locations.get(0);
        	return location.toJson();
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
    
    @RequestMapping(value = "/building/{buildingCode}", method = RequestMethod.GET)
    public Object get(Model uiModel, @PathVariable("buildingCode") String buildingCode) {
        List<Location> locations = locationService.getLocationsByBuildingCode(buildingCode);
        if (locations == null || locations.size() < 1) {
            // Error?
        } else {
        	Location location = locations.get(0);
        	uiModel.addAttribute("location", location);
        	uiModel.addAttribute("buildingCode", location.getBuildingCode());
        }
        return "maps/building";
    }
    
    /*
     * Buildings search
     */
	@RequestMapping(value = "/building/search", method = RequestMethod.GET)
	public String searchBuildings(Model uiModel, 
			@RequestParam(required = true) String criteria, 
			@RequestParam(required = true) String groupCode) {
		criteria = criteria.trim();
		LOG.info("Search: " + groupCode + " : " + criteria);
		MapsFormSearchResultContainer container = search(criteria, groupCode);
		uiModel.addAttribute("container", container);
//		uiModel.addAttribute("message", pageLevelException.getMessage());
		return "maps/search";
	}
	
    /*
     * Buildings search autocomplete
     */
	@RequestMapping(value = "/building/searchassist", method = RequestMethod.GET)
	public String searchBuildingsAutocomplete(Model uiModel, 
			@RequestParam(required = true) String criteria, 
			@RequestParam(required = true) String groupCode) {
		criteria = criteria.trim();
		LOG.info("Search: " + groupCode + " : " + criteria);
		MapsFormSearchResultContainer container = search(criteria, groupCode);
		uiModel.addAttribute("container", container);
		return "maps/searchautocomplete";
	}

    /*
     * Buildings search form results
     */
	@RequestMapping(value = "/building/search", method = RequestMethod.POST)
	public String searchBuildings(HttpServletRequest request, @ModelAttribute("mapsearchform") MapsFormSearch mapsFormSearch, BindingResult bindingResult, SessionStatus status, Model uiModel) {
		String searchString = mapsFormSearch.getSearchText();
		searchString = searchString.trim();
		String searchCampus = mapsFormSearch.getSearchCampus();
		MapsFormSearchResultContainer container = search(searchString, searchCampus);
		uiModel.addAttribute("container", container);
//		uiModel.addAttribute("message", pageLevelException.getMessage());
		return "maps/home";
	}

	private MapsFormSearchResultContainer search(String searchString, String searchCampus) {
		MapsFormSearchResultContainer container = new MapsFormSearchResultContainer();
    	try {
    		
    		List<MapsFormSearchResult> results = new ArrayList<MapsFormSearchResult>();
    		MapsGroup group = locationService.getMapsGroupByCode(searchCampus);
    		Set<Location> locationSet = group.getMapsLocations();
    		List<Location> locations = new ArrayList<Location>();
    		locations.addAll(locationSet);
    		Collections.sort(locations, new LocationSort());
    		for (Location location : locations) {
    			boolean addLocation = false;
    			if (location.getName() != null && location.getName().toLowerCase().indexOf(searchString.toLowerCase()) > -1) {
    				addLocation = true;
    			} else if (location.getShortName() != null && location.getShortName().toLowerCase().indexOf(searchString.toLowerCase()) > -1) {
    				addLocation = true;
    			} else if (location.getShortCode() != null && location.getShortCode().toLowerCase().trim().equals(searchString.toLowerCase().trim())) {
    				addLocation = true;
    			} else if (location.getCode() != null && location.getCode().toLowerCase().trim().equals(searchString.toLowerCase().trim())) {
    				addLocation = true;
    			}
    			if (addLocation) {
        			MapsFormSearchResult result = new MapsFormSearchResult();
        			result.setName(location.getName());
        			result.setCode(location.getBuildingCode());
        			result.setLatitude(location.getLatitude());
        			result.setLongitude(location.getLongitude());
    				results.add(result);
    			}
    		}
    		container.setResults(results);
    		
    	} catch (Exception e) {
    		LOG.error(e.getMessage(), e);
    	}
    	return container;
	}
	
	@RequestMapping(value = "/foursquare", method = RequestMethod.GET)
	@ResponseBody
	protected String getFoursquareData(@RequestParam("lat") String latitude, @RequestParam("lng") String longitude, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String foursquareId = "";
		String foursquareSecret = "";
		try {
			foursquareId = configParamService.findValueByName(FOURSQUARE_CLIENT_ID);
			foursquareSecret = configParamService.findValueByName(FOURSQUARE_CLIENT_SECRET);
		} catch (Exception e) {
			LOG.error("Foursquare config parameters are not set.");
		}

		BufferedReader br = null;
        GetMethod get = null;
        StringBuilder sb = new StringBuilder();
        try {
            get = new GetMethod("https://api.foursquare.com/v2/venues/search?v=20110627&ll="+ latitude + "," + longitude +"&limit=8&client_id=" + foursquareId + "&client_secret=" + foursquareSecret);
            br = new BufferedReader(new InputStreamReader(getInputStreamFromGetMethod(get, 10000)));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {

        } finally {
            if (br != null) {
                br.close();
            }
            if (get != null) {
                get.releaseConnection();
            }
        }

        return sb.toString();
	}
	
	@RequestMapping(value = "/foursquare/{id}", method = RequestMethod.GET)
	@ResponseBody
	protected String getFoursquareData(@PathVariable("id") String id, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String foursquareId = "";
		String foursquareSecret = "";
		try {
			foursquareId = configParamService.findValueByName(FOURSQUARE_CLIENT_ID);
			foursquareSecret = configParamService.findValueByName(FOURSQUARE_CLIENT_SECRET);
		} catch (Exception e) {
			LOG.error("Foursquare config parameters are not set.");
		}
        
        BufferedReader br = null;
        GetMethod get = null;
        StringBuilder sb = new StringBuilder();
        try {
            get = new GetMethod("https://api.foursquare.com/v2/venues/" + id + "?client_id=" + foursquareId + "&client_secret=" + foursquareSecret);
            br = new BufferedReader(new InputStreamReader(getInputStreamFromGetMethod(get, 10000)));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
        } catch (Exception e) {

        } finally {
            if (br != null) {
                br.close();
            }
            if (get != null) {
                get.releaseConnection();
            }
        }

        return sb.toString();
	}
	
	private InputStream getInputStreamFromGetMethod(GetMethod get, int timeout) throws Exception {
        get.getParams().setParameter(HttpMethodParams.RETRY_HANDLER, new DefaultHttpMethodRetryHandler(0, false));
        HttpClient httpClient = new HttpClient();
        httpClient.getParams().setParameter(HttpClientParams.SO_TIMEOUT, new Integer(timeout));
        httpClient.getParams().setParameter(HttpClientParams.CONNECTION_MANAGER_TIMEOUT, new Long(timeout));
        httpClient.getParams().setParameter(HttpConnectionParams.CONNECTION_TIMEOUT, new Integer(timeout));
        int status = httpClient.executeMethod(get);
        if (status == HttpStatus.OK.value()) {
        	return get.getResponseBodyAsStream();	
        }
        return null;
    }
	
	@RequestMapping(value = "/kml", method = RequestMethod.GET)
	protected void getKml(HttpServletRequest request, HttpServletResponse response) throws Exception {
		locationService.saveKml();
	}
}
