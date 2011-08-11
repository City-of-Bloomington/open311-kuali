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
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.admin.entity.HomeScreen;
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

    @RequestMapping(value = "tool", method = RequestMethod.GET)
    public String tool(Model uiModel) {
    	uiModel.addAttribute("tools", adminService.getAllTools());
    	return "publishing/tool";
    }
    
    @RequestMapping(value = "tool/new", method = RequestMethod.GET)
    public String newTool(Model uiModel) {
    	uiModel.addAttribute("tool", new Tool());
    	return "publishing/editTool";
    }
    
    @RequestMapping(value = "tool/new", method = RequestMethod.POST)
    public String newTool(Model uiModel, @ModelAttribute("tool") Tool tool, BindingResult result) {
    	adminService.saveTool(tool);
    	return tool(uiModel);
    }

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
    public String editLayout(Model uiModel) {
    	
//    	HomeScreen layout = 
//    	uiModel.addAttribute("layout", layout);
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }

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
