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

package org.kuali.mobility.weather.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.HomeTool;
import org.kuali.mobility.admin.entity.Tool;
import org.kuali.mobility.admin.service.AdminService;
import org.kuali.mobility.alerts.service.AlertsService;
import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.weather.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
@RequestMapping("/weather")
public class WeatherController {
    
    @Autowired
    private WeatherService weatherService;
    
	@Autowired
    private AdminService adminService;
	
	@Autowired
    private AlertsService alertsService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String index(HttpServletRequest request, Model uiModel) {
    	uiModel.addAttribute("forecast", weatherService.getWeatherForecast());
    	
    	String alias = "PUBLIC";
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);

    	HomeScreen home = new HomeScreen();
    	if (user != null && user.getViewCampus() != null) {
    		alias = user.getViewCampus();
    	} 
    	
    	home = adminService.getHomeScreenByAlias(alias);
    	if (home == null) {
    		home = adminService.getHomeScreenByAlias("PUBLIC");
    	}
    	
    	List<HomeTool> copy = new ArrayList<HomeTool>(home.getHomeTools());    	
    	
    	Tool alerts = null;
    	for (HomeTool homeTool : copy) {
    		Tool tool = homeTool.getTool();
    		if ("alerts".equals(tool.getAlias())) {
    			int count = alertsService.findAlertCountByCampus(user.getViewCampus());
    			if (count > 0) {
    				tool.setBadgeCount(Integer.toString(count));
    				tool.setIconUrl("images/service-icons/srvc-alerts-red.png");
    			} else {
    				tool.setBadgeCount(null);
    				tool.setIconUrl("images/service-icons/srvc-alerts-green.png");
    			}
    			alerts = tool;
    			break;
    		}
    	}

    	uiModel.addAttribute("alertsTool", alerts);
    	
    	return "weather/index";
    }
    
}
