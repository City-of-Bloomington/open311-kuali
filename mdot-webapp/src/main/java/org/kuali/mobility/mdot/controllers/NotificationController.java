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

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.notification.entity.Notification;
import org.kuali.mobility.notification.entity.UserNotification;
import org.kuali.mobility.notification.service.NotificationService;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.kuali.mobility.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller 
@RequestMapping("/notifications")
public class NotificationController {
        
	@Autowired
	private NotificationService notificationService;
    public void setNotificationService(NotificationService notificationService) {
		this.notificationService = notificationService;
	}

    @Autowired 
    private UserService userService;
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public String notifications(HttpServletRequest request, @RequestParam("deviceId") String deviceId, Model uiModel) {
    	List<Notification> notifications = notificationService.findAllValidNotifications(new Date());
    	if (notifications == null) {
    		return "[]";
    	}
    	
    	List<Notification> newNotifications = new ArrayList<Notification>();
    	for (Notification notification : notifications) {
			UserNotification un = notificationService.findUserNotificationByNotificationId(notification.getNotificationId());
			if (un == null) {
				User user = userService.findUserByDeviceId(deviceId);
				if (user != null) {
					if (notification.getPrimaryCampus() == null || notification.getPrimaryCampus().equals(user.getPrimaryCampus())) {
						newNotifications.add(notification);
						UserNotification userNotification = new UserNotification();
						userNotification.setDeviceId(deviceId);
						userNotification.setNotifyDate(new Timestamp(new Date().getTime()));
						userNotification.setPersonId(user.getPrincipalId());
						userNotification.setNotificationId(notification.getNotificationId());
						//notificationService.saveUserNotification(userNotification);
					}
				}
			}
		}
    	
        return new JSONSerializer().exclude("*.class", "notificationId", "startDate", "endDate", "notificationType", "versionNumber").serialize(newNotifications);
    }
	
    @RequestMapping(method = RequestMethod.POST)
    public String submit(HttpServletRequest request, Model uiModel, @ModelAttribute("command") Object command, BindingResult result) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	String service = user.getUserAttribute("service");
    	user.removeUserAttribute("service");
    	user.setUserAttribute("acked", "true");
    	return "redirect:" + service; 
    }

}
