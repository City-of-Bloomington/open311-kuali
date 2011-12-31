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
 
package org.kuali.mobility.shared.controllers;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.HomeTool;
import org.kuali.mobility.admin.entity.Tool;
import org.kuali.mobility.admin.service.AdminService;
import org.kuali.mobility.alerts.service.AlertsService;
import org.kuali.mobility.campus.entity.Campus;
import org.kuali.mobility.campus.service.CampusService;
import org.kuali.mobility.configparams.service.ConfigParamService;
import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.shared.CoreService;
import org.kuali.mobility.shared.entity.Backdoor;
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
	
	@Autowired
    private AlertsService alertsService;

	@Autowired
	private ConfigParamService configParamService;
	
	@Autowired
	private CampusService campusService;
	
	@Autowired
	private CoreService coreService;
    
    @RequestMapping(value = "home", method = RequestMethod.GET)
    public String home(HttpServletRequest request, Model uiModel) {      
    	buildHomeScreen(request, uiModel);
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	String ipAddress = "";
    	if (user.isMember(configParamService.findValueByName("Admin.Group.Name"))) {
    		try {
				ipAddress = "<p><i><small>Server: " + InetAddress.getLocalHost().getHostName() + "</small></i></p>";
			} catch (UnknownHostException e) {}
    	}
    	uiModel.addAttribute("ipAddress", ipAddress);
    	return "index";
    }
    
    @RequestMapping(value = "preferences", method = RequestMethod.GET)
    public String preferences(HttpServletRequest request, Model uiModel) {   
    	String homeToolName = "home";
    	List<Campus> campuses = campusService.findCampusesByTool(homeToolName);
		uiModel.addAttribute("campuses", campuses);
		uiModel.addAttribute("toolName", homeToolName);
    	return "preferences";
    }
    
    @RequestMapping(value = "stylize")
    public String stylize(HttpServletRequest request, Model uiModel) {      
    	uiModel.addAttribute("styles", coreService.getCssCustomizations());
    	return "stylize";
    }
    
    @RequestMapping(value = "kme.appcache", method = RequestMethod.GET)
    public String cachemanifest(HttpServletRequest request, HttpServletResponse response, Model uiModel) {      
    	//response.setContentType("text/cache-manifest");
    	return "cacheManifest";
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

    private void buildHomeScreen(HttpServletRequest request, Model uiModel) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	Backdoor backdoor = (Backdoor) request.getSession().getAttribute(Constants.KME_BACKDOOR_USER_KEY);
    	 
    	String alias = "PUBLIC";
    	HomeScreen home = new HomeScreen();
    	if (user != null && user.getViewCampus() != null) {
    		alias = user.getViewCampus();
    	} 
    	
    	home = adminService.getHomeScreenByAlias(alias);
    	if (home == null) {
    		home = adminService.getHomeScreenByAlias("PUBLIC");
    	}
    	
    	List<HomeTool> copy = new ArrayList<HomeTool>(home.getHomeTools());
    	
    	Tool tool = new Tool();

    	if (user.isMember(configParamService.findValueByName("Backdoor.Group.Name"))) {
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
    	}
 	
    	Collections.sort(copy);
    	
    	for (HomeTool homeTool : copy) {
    		tool = homeTool.getTool();
    		if ("alerts".equals(tool.getAlias())) {
    			int count = alertsService.findAlertCountByCampus(user.getViewCampus());
    			if (count > 0) {
    				tool.setBadgeCount(Integer.toString(count));
    				tool.setIconUrl("images/service-icons/srvc-alerts-red.png");
    			} else {
    				tool.setBadgeCount(null);
    				tool.setIconUrl("images/service-icons/srvc-alerts-green.png");
    			}
    			break;
    		}
    	}
    	
    	uiModel.addAttribute("title", home.getTitle());    
    	uiModel.addAttribute("tools", copy);    
    }
    
}
