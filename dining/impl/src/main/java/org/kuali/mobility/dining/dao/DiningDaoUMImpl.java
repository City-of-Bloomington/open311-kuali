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

package org.kuali.mobility.dining.dao;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.kuali.mobility.dining.entity.Place;
import org.kuali.mobility.util.mapper.DataMapper;

public class DiningDaoUMImpl implements DiningDao {
	
	private static final Logger LOG = Logger.getLogger( DiningDaoUMImpl.class );
	private static final String DEFAULT_CHARACTER_SET = "UTF-8"; //ISO-8859-1
	
	private DataMapper mapper;
	private List<Place> placeList;
	
	private String placeSourceFile;
	private String placeSourceUrl;
	private String placeMappingFile;
	private String placeMappingUrl;
	
	private String menusJsonURI;  //"http://akekee.dsc.umich.edu:8180/dining/getMeals?_type=json"
	
	public String getPlaceSourceFile() {
		return placeSourceFile;
	}

	public void setPlaceSourceFile(String placeSourceFile) {
		this.placeSourceFile = placeSourceFile;
	}

	public String getPlaceSourceUrl() {
		return placeSourceUrl;
	}

	public void setPlaceSourceUrl(String placeSourceUrl) {
		this.placeSourceUrl = placeSourceUrl;
	}

	public String getPlaceMappingFile() {
		return placeMappingFile;
	}

	public void setPlaceMappingFile(String placeMappingFile) {
		this.placeMappingFile = placeMappingFile;
	}

	public String getPlaceMappingUrl() {
		return placeMappingUrl;
	}

	public void setPlaceMappingUrl(String placeMappingUrl) {
		this.placeMappingUrl = placeMappingUrl;
	}

	public List<Place> getPlaceList() {
		if (placeList==null || placeList.isEmpty()) {
			initData();
		}
		return placeList;
	}

	public void setMapper(DataMapper mapper) {
		this.mapper = mapper;
	}

	private void initData() {
		if (placeList==null)
			placeList = new ArrayList<Place>();
		
		boolean isPlaceSourceUrlAvailable = (getPlaceSourceUrl() != null ? true : false) ;
		boolean isPlaceMappingUrlAvailable = (getPlaceMappingUrl() != null ? true : false) ;
		
		try {
			if(isPlaceSourceUrlAvailable) {
				if (isPlaceMappingUrlAvailable) {
					placeList = mapper.mapData(placeList, new URL(getPlaceSourceUrl()), new URL(getPlaceMappingUrl()));
				}
				else {
					placeList = mapper.mapData(placeList, new URL(getPlaceSourceUrl()), getPlaceMappingFile());
				}
			}
			else {
				if (isPlaceMappingUrlAvailable) {
					LOG.error("DataMapper does NOT support this case, sourceFile with mappingUrl!");
					return;
				}
				else {
					placeList = mapper.mapData(placeList, getPlaceSourceFile(), getPlaceMappingFile());
				}	
			}
		
		} catch (MalformedURLException e) {
			LOG.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
	
	public String getMenusJsonURI() {
		return menusJsonURI;
	}

	public void setMenusJsonURI(String menusJsonURI) {
		this.menusJsonURI = menusJsonURI;
	}

	public String getMenusJson(final String name, final String location){
		String jsonData = null;
		String queryString = null;
		try {
			queryString = "&name=".concat(location==null? URLEncoder.encode(name,"ISO-8859-1") : (URLEncoder.encode(name,"ISO-8859-1") + "&location=" + URLEncoder.encode(location,"ISO-8859-1")));
		} catch (UnsupportedEncodingException e1) {
			LOG.error(e1.getMessage());
		}
		LOG.debug("RUL = " + getMenusJsonURI() + queryString);
		try {			
			URLConnection connection = new URL(getMenusJsonURI() + queryString).openConnection();
			jsonData = IOUtils.toString( connection.getInputStream(), DEFAULT_CHARACTER_SET );			
		} catch (MalformedURLException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}

		return jsonData; 
	}

	
/*	public List<Menu> getMenus(final String name, final String location){
		List<Menu> menuList = new ArrayList<Menu>();
		String queryString = (location==null? name : (name + "&location=" + location));
		
		try {
			URLConnection connection = new URL(BASEURL + queryString).openConnection();
			
			// TODO parse
			
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return menuList; 
	}*/
	
} 
