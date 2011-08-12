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

package org.kuali.mobility.admin.controllers;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.HomeTool;
import org.kuali.mobility.admin.entity.Tool;
import org.kuali.mobility.admin.service.AdminService;
import org.kuali.mobility.notification.entity.Notification;
import org.kuali.mobility.notification.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
@RequestMapping("/publishing")
public class PublishingController {
    
    @Autowired
    private AdminService adminService;
    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }
    
    @Autowired
    private NotificationService notificationService;
    public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model uiModel) {

    	return "publishing/index";
    }
	
	//----------------Tools------------------

    @RequestMapping(value = "tool", method = RequestMethod.GET)
    public String tool(Model uiModel) {
    	List<Tool> tools = adminService.getAllTools();
    	Collections.sort(tools);
    	uiModel.addAttribute("tools", tools);
    	return "publishing/tool";
    }
    
    @RequestMapping(value = "tool/new", method = RequestMethod.GET)
    public String newTool(Model uiModel) {
    	uiModel.addAttribute("tool", new Tool());
    	return "publishing/editTool";
    }
    
    @RequestMapping(value = "tool/edit/{toolId}", method = RequestMethod.GET)
    public String editTool(Model uiModel, @PathVariable("toolId") long toolId) {
    	Tool tool = adminService.getToolById(toolId);
    	uiModel.addAttribute("tool", tool);
    	return "publishing/editTool";
    }
    
    @RequestMapping(value = "tool/delete/{toolId}", method = RequestMethod.GET)
    public String deleteTool(Model uiModel, @PathVariable("toolId") long toolId) {
    	adminService.deleteToolById(toolId);
    	return tool(uiModel);
    }
    
    @RequestMapping(value = "tool/edit", method = RequestMethod.POST)
    public String editTool(Model uiModel, @ModelAttribute("tool") Tool tool, BindingResult result) {
    	adminService.saveTool(tool);
    	return tool(uiModel);
    }
    
  //----------------Layouts------------------

    @RequestMapping(value = "layout", method = RequestMethod.GET)
    public String layout(Model uiModel) {
    	uiModel.addAttribute("layouts", adminService.getAllHomeScreens());
    	return "publishing/layout";
    }
    
    @RequestMapping(value = "layout/new", method = RequestMethod.GET)
    public String newLayout(Model uiModel) {
    	uiModel.addAttribute("layout", new HomeScreen());
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    @RequestMapping(value = "layout/edit/{layoutId}", method = RequestMethod.GET)
    public String editLayout(Model uiModel, @PathVariable("layoutId") long layoutId) {
    	HomeScreen layout = adminService.getHomeScreenById(layoutId);
    	Collections.sort(layout.getHomeTools());
    	uiModel.addAttribute("layout", layout);
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    @RequestMapping(value = "layout/edit", method = RequestMethod.POST)
    public String editLayout(Model uiModel, @ModelAttribute("layout") HomeScreen homeScreen, BindingResult result) {
    	adminService.saveHomeScreen(homeScreen);
    	return layout(uiModel);
    }
    
    @RequestMapping(value = "layout/delete/{layoutId}", method = RequestMethod.GET)
    public String deleteLayout(Model uiModel, @PathVariable("layoutId") long layoutId) {
    	adminService.deleteHomeScreenById(layoutId);
    	return layout(uiModel);
    }
    
    @RequestMapping(value = "layout/edit", method = RequestMethod.POST, params = "add")
    public String addTool(Model uiModel, @ModelAttribute("layout") HomeScreen homeScreen, BindingResult result, @RequestParam("toolToAdd") Long toolId) {
    	Tool tool = adminService.getToolById(toolId);
    	boolean found = false;
    	for (HomeTool homeTool : homeScreen.getHomeTools()) {
    		if (homeTool.getToolId().equals(toolId)) {
    			found = true;
    			break;
    		}
    	}
    	if (!found) {
    		HomeTool homeTool = new HomeTool(homeScreen, tool, homeScreen.getHomeTools().size());
    		homeScreen.getHomeTools().add(homeTool);
    	}
    	Collections.sort(homeScreen.getHomeTools());
    	uiModel.addAttribute("layout", homeScreen);
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    @RequestMapping(value = "layout/edit", method = RequestMethod.POST, params = "remove")
    public String removeTool(Model uiModel, @ModelAttribute("layout") HomeScreen homeScreen, BindingResult result, @RequestParam("removeId") Long toolId) {
    	Integer removedOrder = null;
    	for (Iterator<HomeTool> iter = homeScreen.getHomeTools().iterator(); iter.hasNext();) {
    		HomeTool homeTool = iter.next();
    		if (homeTool.getToolId().equals(toolId)) {
    			removedOrder = homeTool.getOrder();
    			iter.remove();
    			break;
    		}
    	}
    	if (removedOrder != null) {
    		for (HomeTool ht : homeScreen.getHomeTools()) {
    			if (ht.getOrder() > removedOrder) {
    				ht.setOrder(ht.getOrder() - 1);
    			}
    		}
    	}
    	Collections.sort(homeScreen.getHomeTools());
    	uiModel.addAttribute("layout", homeScreen);
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    @RequestMapping(value = "layout/edit", method = RequestMethod.POST, params = "up")
    public String moveToolUp(Model uiModel, @ModelAttribute("layout") HomeScreen homeScreen, BindingResult result, @RequestParam("removeId") Long toolId) {
    	HomeTool selectedHomeTool = null;
    	
    	for (HomeTool homeTool : homeScreen.getHomeTools()) {
    		if (homeTool.getToolId().equals(toolId)) {
    			selectedHomeTool = homeTool;
    			break;
    		}
    	}
    	if (selectedHomeTool != null && selectedHomeTool.getOrder() > 0) {
    		for (HomeTool ht : homeScreen.getHomeTools()) {
    			if (ht.getOrder() == selectedHomeTool.getOrder() - 1) {
    				ht.setOrder(ht.getOrder() + 1);
    				selectedHomeTool.setOrder(selectedHomeTool.getOrder() - 1);
    				break;
    			}
    		}
    	}
    	Collections.sort(homeScreen.getHomeTools());
    	uiModel.addAttribute("layout", homeScreen);
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    @RequestMapping(value = "layout/edit", method = RequestMethod.POST, params = "down")
    public String moveToolDown(Model uiModel, @ModelAttribute("layout") HomeScreen homeScreen, BindingResult result, @RequestParam("removeId") Long toolId) {
    	HomeTool selectedHomeTool = null;
    	
    	for (HomeTool homeTool : homeScreen.getHomeTools()) {
    		if (homeTool.getToolId().equals(toolId)) {
    			selectedHomeTool = homeTool;
    			break;
    		}
    	}
    	if (selectedHomeTool != null) {
    		for (HomeTool ht : homeScreen.getHomeTools()) {
    			if (ht.getOrder() == selectedHomeTool.getOrder() + 1) {
    				ht.setOrder(ht.getOrder() + 1);
    				selectedHomeTool.setOrder(selectedHomeTool.getOrder() - 1);
    				break;
    			}
    		}
    	}
    	Collections.sort(homeScreen.getHomeTools());
    	uiModel.addAttribute("layout", homeScreen);
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    
    //----------------Notifications------------------

    @RequestMapping(value = "notifications", method = RequestMethod.GET)
    public String notifications(Model uiModel) {
    	uiModel.addAttribute("notifications", notificationService.findAllNotifications());
    	return "publishing/notifications";
    }

    @RequestMapping(value = "notificationForm", method = RequestMethod.GET)
    public String notificationForm(@RequestParam(value="id", required=false) Long id, Model uiModel) {
    	Notification n = new Notification();
    	if (id != null) {
    		n = notificationService.findNotificationById(id);
    	}
    	uiModel.addAttribute("notification", n);
    	return "publishing/notificationForm";
    }

    @RequestMapping(value = "editNotification", method = RequestMethod.GET)
    public String editNotification(@RequestParam(value="id", required=true) Long id, Model uiModel) {
    	Notification n = notificationService.findNotificationById(id);
    	uiModel.addAttribute("notification", n);
    	return "publishing/notificationForm";
    }

    @RequestMapping(value = "deleteNotification", method = RequestMethod.GET)
    public String deleteNotification(@RequestParam(value="id", required=true) Long id, Model uiModel) {
    	notificationService.deleteNotificationById(id);
    	return "redirect:/publishing/notifications";
    }
    
    @RequestMapping(value="notificationSubmit", method = RequestMethod.POST)
    public String submit(HttpServletRequest request, Model uiModel, @ModelAttribute("notification") Notification notification, BindingResult result) {
    	if (isValidNotification(notification, result)) {
    		notificationService.saveNotification(notification);
        	return "redirect:/publishing/notifications"; 
        } else {
        	return "publishing/notificationForm";    	
        }
    }
    
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    private boolean isValidNotification(Notification notification, BindingResult result) {
    	boolean hasErrors = false;
    	Errors errors = ((Errors) result);
    	if (errors.hasFieldErrors("startDate")) {    		
    		errors.rejectValue("startDate", "", "Please enter a valid start date (empty or YYYY-MM-DD)");
    		hasErrors = true;    		
    	}
    	if (errors.hasFieldErrors("endDate")) {
    		errors.rejectValue("endDate", "", "Please enter a valid end date (empty or YYYY-MM-DD)");
    		hasErrors = true;    		
    	}
    	if (notification.getMessage() == null || "".equals(notification.getMessage().trim())) {
    		errors.rejectValue("message", "", "Please enter a message");
    		hasErrors = true;
    	}
    	if (notification.getTitle() == null || "".equals(notification.getTitle().trim())) {
    		errors.rejectValue("title", "", "Please enter a title");
    		hasErrors = true;
    	}
    	if (notification.getNotificationType() == null) {
    		notification.setNotificationType(new Long(1));
    	}
    	return !hasErrors;
    }

}
