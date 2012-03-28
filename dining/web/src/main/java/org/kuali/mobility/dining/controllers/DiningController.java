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

package org.kuali.mobility.dining.controllers;

import java.util.List;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.log4j.Logger;
import org.kuali.mobility.dining.entity.PlaceByCampusByType;
import org.kuali.mobility.dining.service.DiningService;
import org.kuali.mobility.dining.util.DiningUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller 
@RequestMapping("/dining")
public class DiningController {
    public static final Logger LOG= Logger.getLogger(DiningController.class);
    
    @Autowired
    private DiningService diningService;
    public void setDiningService(DiningService diningService) {
		this.diningService = diningService;
	}

	@RequestMapping(method = RequestMethod.GET)
    public String getPlaces(Model uiModel) { 
    	List<PlaceByCampusByType> placeGroups = DiningUtil.convertPlaceListForUI(diningService.getPlaces());
    	uiModel.addAttribute("placeGroups", placeGroups);
    	return "dining/placesByCampus2";
    }
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getPlaceListJson() { // 
    	List<PlaceByCampusByType> placeGroups = DiningUtil.convertPlaceListForUI(diningService.getPlaces());
    	return new JSONSerializer().exclude("*.class").deepSerialize(placeGroups);
    }
    
    
    @RequestMapping(value="/{name}", method = RequestMethod.GET)
    public String getMenus(Model uiModel, @PathVariable("name") String name, @RequestParam(value = "location", required = false) String location){
    	LOG.debug( "getMenus() : name = "+name+" location = "+location );
    	String place = ( (location==null || location.trim().isEmpty()) ? name : (name + " at " + location) );
    	uiModel.addAttribute("place", place);
    	uiModel.addAttribute("name", StringEscapeUtils.escapeJavaScript(name));
    	uiModel.addAttribute("location", StringEscapeUtils.escapeJavaScript(location));
    	return "dining/menus";
    }
    
    @RequestMapping(value = "/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getMenusJson(@PathVariable("name") String name, @RequestParam(value = "location", required = false) String location) {
    	String jsonData = diningService.getMenusJson(name, location);
    	
    	return jsonData;
    }
    
 
/*
    @RequestMapping(method = RequestMethod.GET)
    public String getList(Model uiModel) {
    	List<Menu> menus = diningService.getMenus("SE");
    	uiModel.addAttribute("menus", menus);
    	return "dining/list";
    }
    
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getListJson() {
    	List<Menu> menus = diningService.getMenus("SE");
    	return new JSONSerializer().exclude("*.class").deepSerialize(menus);
    }
*/
    
}
