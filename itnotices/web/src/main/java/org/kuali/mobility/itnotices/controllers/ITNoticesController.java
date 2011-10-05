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

package org.kuali.mobility.itnotices.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.itnotices.entity.ITNotice;
import org.kuali.mobility.itnotices.service.ITNoticesService;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller 
@RequestMapping("/itnotices")
public class ITNoticesController {
 
    @Autowired
    private ITNoticesService itNoticesService;
	
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getListJson() {
       	List<ITNotice> notices = itNoticesService.findAllITNotices();
		return itNoticesService.toJson(notices);
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String getList(Model uiModel) {
//   		List<ITNotice> notices = itNoticesService.findAllITNotices();
//   		uiModel.addAttribute("notices", notices);
    	return "itnotices/list";
    }
    
    @RequestMapping(value = "/details", method = RequestMethod.GET)
    public String getDetails(Model uiModel, HttpServletRequest request, @RequestParam(required = true) String id) {
        List<ITNotice> notices = itNoticesService.findAllITNotices();
//        uiModel.addAttribute("notice", notices.get(id));
        
        User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
        
        ITNotice itNotice = null;
        for (ITNotice notice : notices) {
        	if (notice.getId().equals(id)) {
        		itNotice = notice;
        		break;
        	}
        }
        user.putInCache("ITNotices.Notice", itNotice);
        
        return "itnotices/details";
    }
    
    @RequestMapping(value = "/details", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getDetailsJson(Model uiModel, HttpServletRequest request) {
    	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
    	ITNotice notice = (ITNotice)user.getFromCache("ITNotices.Notice").getItem();
		if (notice != null) {
			return new JSONSerializer().exclude("*.class").deepSerialize(notice);
		} else {
			return "";
		}
    }
     
}
