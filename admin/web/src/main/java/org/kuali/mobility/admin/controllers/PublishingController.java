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
import org.kuali.mobility.news.entity.NewsSource;
import org.kuali.mobility.news.service.NewsService;
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

/**
 * Controller for performing publishing actions
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
@Controller 
@RequestMapping("/publishing")
public class PublishingController {
    
    @Autowired
    private AdminService adminService;
    
    @Autowired
    private NotificationService notificationService;
    
    @Autowired
    private NewsService newsService;

    /**
     * The main entry point for publishing. Provides links to more specific publishing tools.
     * @param uiModel
     * @return
     */
	@RequestMapping(value = "index", method = RequestMethod.GET)
    public String index(Model uiModel) {
    	return "publishing/index";
    }
	
	//----------------Tools------------------

	/**
	 * Entry point for publishing Tools.  Lists currently defined Tools.
	 * @param uiModel
	 * @return the tool publishing entry page
	 */
    @RequestMapping(value = "tool", method = RequestMethod.GET)
    public String tool(Model uiModel) {
    	List<Tool> tools = adminService.getAllTools();
    	Collections.sort(tools);
    	uiModel.addAttribute("tools", tools);
    	return "publishing/tool";
    }
    
    /**
     * Create a new Tool
     * @param uiModel
     * @return the edit tool page
     */
    @RequestMapping(value = "tool/new", method = RequestMethod.GET)
    public String newTool(Model uiModel) {
    	uiModel.addAttribute("tool", new Tool());
    	return "publishing/editTool";
    }
    
    /**
     * Edit an existing Tool
     * @param uiModel
     * @param toolId id of the Tool to edit
     * @return the edit tool page
     */
    @RequestMapping(value = "tool/edit/{toolId}", method = RequestMethod.GET)
    public String editTool(Model uiModel, @PathVariable("toolId") long toolId) {
    	Tool tool = adminService.getToolById(toolId);
    	uiModel.addAttribute("tool", tool);
    	return "publishing/editTool";
    }
    
    /**
     * Delete a Tool
     * @param uiModel
     * @param toolId id of the Tool to delete
     * @return back to the tool publishing entry page
     */
    @RequestMapping(value = "tool/delete/{toolId}", method = RequestMethod.GET)
    public String deleteTool(Model uiModel, @PathVariable("toolId") long toolId) {
    	adminService.deleteToolById(toolId);
    	return tool(uiModel);
    }
    
    /**
     * Save a Tool
     * @param uiModel
     * @param tool the Tool object to save
     * @param result binding validation result
     * @return back to the tool publishing entry page
     */
    @RequestMapping(value = "tool/edit", method = RequestMethod.POST)
    public String editTool(Model uiModel, @ModelAttribute("tool") Tool tool, BindingResult result) {
    	adminService.saveTool(tool);
    	return tool(uiModel);
    }
    
  //----------------Layouts------------------

    /**
     * The entry point for publishing HomeScreen layouts
     * @param uiModel
     * @return the home screen publishing entry page
     */
    @RequestMapping(value = "layout", method = RequestMethod.GET)
    public String layout(Model uiModel) {
    	uiModel.addAttribute("layouts", adminService.getAllHomeScreens());
    	return "publishing/layout";
    }
    
    /**
     * Create a new HomeScreen
     * @param uiModel
     * @return the home screen editing page
     */
    @RequestMapping(value = "layout/new", method = RequestMethod.GET)
    public String newLayout(Model uiModel) {
    	uiModel.addAttribute("layout", new HomeScreen());
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    /**
     * Edit an existing HomeScreen
     * @param uiModel
     * @param layoutId the id of the HomeScreen to edit
     * @return the home screen editing page
     */
    @RequestMapping(value = "layout/edit/{layoutId}", method = RequestMethod.GET)
    public String editLayout(Model uiModel, @PathVariable("layoutId") long layoutId) {
    	HomeScreen layout = adminService.getHomeScreenById(layoutId);
    	Collections.sort(layout.getHomeTools());
    	uiModel.addAttribute("layout", layout);
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    /**
     * Save a HomeScreen
     * @param uiModel
     * @param homeScreen the HomeScreen to save
     * @param result binding validation result
     * @return the home screen publishing entry page
     */
    @RequestMapping(value = "layout/edit", method = RequestMethod.POST)
    public String editLayout(Model uiModel, @ModelAttribute("layout") HomeScreen homeScreen, BindingResult result) {
    	int index = 0;
    	for (HomeTool ht : homeScreen.getHomeTools()) {
			ht.setOrder(index++);
		}
    	adminService.saveHomeScreen(homeScreen);
    	return layout(uiModel);
    }
    
    /**
     * Delete a HomeScreen
     * @param uiModel
     * @param layoutId the id of the HomeScren to delete
     * @return the home screen publishing entry page
     */
    @RequestMapping(value = "layout/delete/{layoutId}", method = RequestMethod.GET)
    public String deleteLayout(Model uiModel, @PathVariable("layoutId") long layoutId) {
    	adminService.deleteHomeScreenById(layoutId);
    	return layout(uiModel);
    }
    
    /**
     * Associate a Tool with a HomeScreen if it isn't already associated
     * @param uiModel
     * @param homeScreen the HomeScreen to which to add the Tool
     * @param result binding validation result for the HomeScreen
     * @param toolId the id of the Tool to associate with the HomeSreen
     * @return the home screen editing page
     */
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
    
    /**
     * Remove a Tool's association with a HomeScreen
     * @param uiModel
     * @param homeScreen the HomeScreen from which to remove the Tool association
     * @param result the binding validation result for the HomeScreen
     * @param toolId the id of the Tool to remove
     * @return the home screen editing page
     */
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
        	int index = 0;
        	for (HomeTool ht : homeScreen.getHomeTools()) {
    			ht.setOrder(index++);
    		}
        }
    	Collections.sort(homeScreen.getHomeTools());
    	uiModel.addAttribute("layout", homeScreen);
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    /**
     * Move a tool up in the Tool list display order
     * @param uiModel
     * @param homeScreen the HomeScreen to edit
     * @param result binding validation result for the HomeScreen
     * @param toolId the id of the Tool to move
     * @return the home screen editing page
     */
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
    				int swap = ht.getOrder();
    				ht.setOrder(selectedHomeTool.getOrder());
    				selectedHomeTool.setOrder(swap);
    				break;
    			}
    		}
    	}
    	Collections.sort(homeScreen.getHomeTools());
    	uiModel.addAttribute("layout", homeScreen);
    	uiModel.addAttribute("availableTools", adminService.getAllTools());
    	return "publishing/editLayout";
    }
    
    /**
     * Move a tool down in the Tool list display order
     * @param uiModel
     * @param homeScreen the HomeScreen to edit
     * @param result binding validation result for the HomeScreen
     * @param toolId the id of the Tool to move
     * @return the home screen editing page
     */
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
    				int swap = ht.getOrder();
    				ht.setOrder(selectedHomeTool.getOrder());
    				selectedHomeTool.setOrder(swap);
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

    /**
     * the main entry point for notifications
     * @param uiModel
     * @return the notifications entry page
     */
    @RequestMapping(value = "notifications", method = RequestMethod.GET)
    public String notifications(Model uiModel) {
    	uiModel.addAttribute("notifications", notificationService.findAllNotifications());
    	return "publishing/notifications";
    }

    /**
     * Create or edit a notification
     * @param id (optional) the id of the notification to edit
     * @param uiModel
     * @return the notification edit form
     */
    @RequestMapping(value = "notificationForm", method = RequestMethod.GET)
    public String notificationForm(@RequestParam(value="id", required=false) Long id, Model uiModel) {
    	Notification n = new Notification();
    	if (id != null) {
    		n = notificationService.findNotificationById(id);
    	}
    	uiModel.addAttribute("notification", n);
    	return "publishing/notificationForm";
    }

    /**
     * Edit a notification
     * @param id the id of the notification to edit
     * @param uiModel
     * @return the notification edit form
     */
    @RequestMapping(value = "editNotification", method = RequestMethod.GET)
    public String editNotification(@RequestParam(value="id", required=true) Long id, Model uiModel) {
    	Notification n = notificationService.findNotificationById(id);
    	uiModel.addAttribute("notification", n);
    	return "publishing/notificationForm";
    }

    /**
     * Delete a notification
     * @param id the id of the notification to delete
     * @param uiModel
     * @return the notifications entry page
     */
    @RequestMapping(value = "deleteNotification", method = RequestMethod.GET)
    public String deleteNotification(@RequestParam(value="id", required=true) Long id, Model uiModel) {
    	notificationService.deleteNotificationById(id);
    	return "redirect:/publishing/notifications";
    }
    
    /**
     * Save a notification
     * @param request
     * @param uiModel
     * @param notification the Notification to save
     * @param result binding validation result
     * @return the notifications entry page
     */
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

    /**
     * Validate a Notification
     * @param notification the Notification to validate
     * @param result
     * @return true if valid
     */
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
    
  //----------------News------------------
    /**
     * The main entry point for News publishing
     * @param uiModel
     * @return the news entry page
     */
    @RequestMapping(value = "news", method = RequestMethod.GET)
    public String news(Model uiModel) {
    	uiModel.addAttribute("sources", newsService.getAllNewsSources());
    	return "publishing/news";
    }
    
    /**
     * Create a new NewsSource
     * @param uiModel
     * @return the news source editing page
     */
    @RequestMapping(value = "news/add", method = RequestMethod.GET)
    public String editNews(Model uiModel) {
    	NewsSource source = new NewsSource();
    	source.setOrder(newsService.getAllNewsSources().size());
    	source.setActive(true);
    	uiModel.addAttribute("source", source);
    	return "publishing/editNews";
    }
    
    /**
     * Edit an existing NewsSource
     * @param uiModel
     * @param id the id of the NewsSource to edit
     * @return the news source editing page
     */
    @RequestMapping(value = "news/edit/{id}", method = RequestMethod.GET)
    public String editNews(Model uiModel, @PathVariable("id") long id) {
    	NewsSource newsSource = newsService.getNewsSourceById(id);
    	uiModel.addAttribute("source", newsSource);
    	return "publishing/editNews";
    }
    
    /**
     * Delete a NewsSource
     * @param uiModel
     * @param id the id of the NewsSource to delete
     * @return the news entry page
     */
    @RequestMapping(value = "news/delete/{id}", method = RequestMethod.GET)
    public String deleteNewsSource(Model uiModel, @PathVariable("id") long id) {
    	newsService.deleteNewsSourcebyId(id);
    	return news(uiModel);
    }
    
    /**
     * Save a NewsSource
     * @param uiModel
     * @param source the NewsSource to save
     * @param result the binding validation result
     * @return the news entry page
     */
    @RequestMapping(value = "news/edit", method = RequestMethod.POST)
    public String editNewsSource(Model uiModel, @ModelAttribute("source") NewsSource source, BindingResult result) {
    	if ("".equals(source.getUrl().trim())) {
    		Errors errors = (Errors)result;
    		errors.rejectValue("url", "", "Please enter a Url to an RSS or Atom feed.");
    		return "publishing/editNews";
    	}
    	source.setUrl(source.getUrl().trim());
    	newsService.saveNewsSource(source);
    	return news(uiModel);
    }
    
    /**
     * Move a news feed up in the display order
     * @param uiModel
     * @param id the id of the NewsSource to move
     * @return the news entry page
     */
    @RequestMapping(value = "news/up/{id}", method = RequestMethod.GET)
    public String moveUp(Model uiModel, @PathVariable("id") long id) {
    	newsService.moveNewsSourceUp(id);
    	return news(uiModel);
    }
    
    /**
     * Move a news feed down in the display order
     * @param uiModel
     * @param id the id of the NewsSource to move
     * @return the news entry page
     */
    @RequestMapping(value = "news/down/{id}", method = RequestMethod.GET)
    public String moveDown(Model uiModel, @PathVariable("id") long id) {
    	newsService.moveNewsSourceDown(id);
    	return news(uiModel);
    }
}
