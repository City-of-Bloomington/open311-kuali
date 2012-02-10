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

package org.kuali.mobility.feedback.controllers;

import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.feedback.entity.Feedback;
import org.kuali.mobility.feedback.service.FeedbackService;
import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.shared.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
@RequestMapping("/feedback")
public class FeedbackController{
    
    @Autowired
    private FeedbackService feedbackService;
    
    private static final Map<String, String> deviceTypes;
    
    static {
    	deviceTypes = new LinkedHashMap<String, String>();
    	deviceTypes.put("", "Select a Device");
    	deviceTypes.put("computer", "Computer");
    	deviceTypes.put("an", "Android");
    	deviceTypes.put("bb", "BlackBerry");
    	deviceTypes.put("ip", "iPhone");
    	deviceTypes.put("ie", "Windows Mobile");
    	deviceTypes.put("sp", "Other Smartphone");
    	deviceTypes.put("other", "Other Device");
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getList(Model uiModel, HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
   		uiModel.addAttribute("feedback", new Feedback());
   		uiModel.addAttribute("deviceTypes", deviceTypes);
   		Feedback feedback = new Feedback();
    	if (user != null && user.getEmail() != null) {
    		feedback.setEmail(user.getEmail());
    	}
   		uiModel.addAttribute("feedback", feedback);
    	return "feedback/form";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String submitFeedback(Model uiModel, @ModelAttribute("feedback") Feedback feedback, BindingResult result) {
    	feedback.setPostedTimestamp(new Timestamp(System.currentTimeMillis()));
        if (isValidFeedback(feedback, result)) {
            feedbackService.saveFeedback(feedback);
            return "feedback/thanks";                	
        } else {
        	uiModel.addAttribute("deviceTypes", deviceTypes);
        	return "feedback/form";    	
        }
    }
    
    private boolean isValidFeedback(Feedback f, BindingResult result) {
    	boolean hasErrors = false;
    	Errors errors = ((Errors) result);
    	if (f.getNoteText() == null || "".equals(f.getNoteText().trim())) {
    		errors.rejectValue("noteText", "", "Please type some feedback into the input box.");
    		hasErrors = true;
    	} else if (f.getNoteText().length() > 2000) {
    		errors.rejectValue("noteText", "", "Error: Feedback must be less than 2000 characters.");
    		hasErrors = true;    		
    	}
    	if (f.getDeviceType() == null || f.getDeviceType().equals("")) {
     		errors.rejectValue("deviceType", "", "Please select a device type.");
     		hasErrors = true;    		
     	} else if (deviceTypes.get(f.getDeviceType()) == null) {
     		errors.rejectValue("deviceType", "", "Error: Please select a valid device type.");
     		hasErrors = true;    		
     	}
    	return !hasErrors;
    }
    
}
