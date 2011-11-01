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

package org.kuali.mobility.askiu.controllers;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller 
@RequestMapping("/campus411")
public class Campus411Controller {
    
    @RequestMapping(method = RequestMethod.GET)
    public String getList(Model uiModel, HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);

    	if (user.getViewCampus() == null) {
    		return "redirect:/campus?toolName=campus411";
    	}
    	
    	String title = null;
    	String phoneNumber = null;
    	if ("BL".equals(user.getViewCampus())){
    		title = "812-855-IUIU (4848)";
    		phoneNumber = "18128554848";
    	}
    	if ("IN".equals(user.getViewCampus())){
    		title = "317-274-5555";
    		phoneNumber = "13172745555";
    	}
   		
    	uiModel.addAttribute("title", title);
   		uiModel.addAttribute("phoneNumber", phoneNumber);
    	
   		return "campus411/index";
    }
    
}
