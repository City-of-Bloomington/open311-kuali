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
 
package org.kuali.mobility.mdot.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.HomeTool;
import org.kuali.mobility.admin.entity.Tool;
import org.kuali.mobility.admin.service.AdminService;
import org.kuali.mobility.alerts.service.AlertsService;
import org.kuali.mobility.mdot.entity.Backdoor;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
@RequestMapping("/")
public class HomeController {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(HomeController.class);
	
	@Autowired
    private AdminService adminService;
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

	@Autowired
    private AlertsService alertsService;
    public void setAlertsService(AlertsService alertsService) {
        this.alertsService = alertsService;
    }

    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model uiModel) {      
    	buildHomeScreen(request, uiModel);
    	return "index";
    }

    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, Model uiModel) {      
    	return "logout";
    }

    @RequestMapping(value = "yesLogout", method = RequestMethod.GET)
    public void fullLogout(HttpServletRequest request, HttpServletResponse response, Model uiModel) {      
    	request.getSession().setAttribute(Constants.KME_USER_KEY, null);
    	request.getSession().setAttribute(Constants.KME_BACKDOOR_USER_KEY, null);
    	request.getSession().removeAttribute("edu.iu.uis.cas.filter.CASAuthenticationMap");
    	try {
			response.sendRedirect("https://cas.iu.edu/cas/logout");
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
    }

    /*
    @RequestMapping(value = "home.json", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getHomeScreenJson() {
    	return new JSONSerializer().exclude("*.class").include("tools").serialize(buildHomeScreen());
    }
    */

    private void buildHomeScreen(HttpServletRequest request, Model uiModel) {
    	
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	Backdoor backdoor = (Backdoor) request.getSession().getAttribute(Constants.KME_BACKDOOR_USER_KEY);
    	 
    	String alias = "PUBLIC";
    	HomeScreen home = new HomeScreen();
    	if (user != null && user.getViewCampus() != null) {
    		alias = user.getViewCampus();
    	} else {
    		// TODO: Refactor out this specific IU feature
            String ref = request.getParameter("ref");
            if (ref != null) {
            	ref = ref.trim();
            	if (ref.indexOf("m.iub.edu") >= 0) {
            		user.setViewCampus("BL");
            	} else if (ref.indexOf("m.iupui.edu") >= 0) {
            		user.setViewCampus("IN");
            	} else if (ref.indexOf("m.iupuc.edu") >= 0) {
            		user.setViewCampus("CO");
            	} else if (ref.indexOf("m.iue.edu") >= 0) {
            		user.setViewCampus("EA");
            	} else if (ref.indexOf("m.iuk.edu") >= 0) {
            		user.setViewCampus("KO");
            	} else if (ref.indexOf("m.iun.edu") >= 0) {
            		user.setViewCampus("NW");
            	} else if (ref.indexOf("m.iusb.edu") >= 0) {
            		user.setViewCampus("SB");
            	} else if (ref.indexOf("m.ius.edu") >= 0) {
            		user.setViewCampus("SE");
            	}
            }
    	}
    	
    	home = adminService.getCachedHomeScreenByAlias(alias);
    	
    	List<HomeTool> copy = new ArrayList<HomeTool>(home.getHomeTools());
    	
    	Tool tool = new Tool();
    	if (backdoor != null) {
    		tool.setBadgeCount(backdoor.getUserId());
    	} else {
    		tool.setBadgeCount("");
    	}
    	tool.setDescription("Impersonate a user.");
    	tool.setIconUrl("images/service-icons/srvc-backdoor.png");
    	tool.setTitle("Backdoor");
    	tool.setUrl("backdoor");
    	copy.add(new HomeTool(home, tool, home.getHomeTools().size() + 1000));

    	// TODO: MOBILITY-53
    	for (HomeTool homeTool : copy) {
			if ("Campus Alerts".equals(homeTool.getTool().getTitle())) {
		    	Map<String, String> criteria = new HashMap<String, String>();
		    	String campus = user.getViewCampus();
		    	if (campus != null) {
		    		criteria.put("campus", campus);
		    	} else {
		    		criteria.put("campus", "UA");
		    	}
		    	int count = alertsService.findAlertCountByCriteria(criteria);
		    	tool = homeTool.getTool();
		    	if (count == 0) {
		    		tool.setBadgeCount("");
		    	} else {
		    		tool.setBadgeCount("" + count);
		    	}
		    	if (count > 0) {
		    		tool.setIconUrl("images/service-icons/srvc-alerts-red.png");
		    	} else { 
		    		tool.setIconUrl("images/service-icons/srvc-alerts-green.png");
		    	}
			}
		}
 	
    	Collections.sort(copy);
    	
    	uiModel.addAttribute("title", home.getTitle());    
    	uiModel.addAttribute("tools", copy);    
    }
    
}
