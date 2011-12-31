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

import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.mobility.campus.entity.Campus;
import org.kuali.mobility.campus.service.CampusService;
import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.shared.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/campus")
public class CampusController {

	@Autowired
	private CampusService campusService;

	@RequestMapping(method = RequestMethod.GET)
	public String getList(HttpServletRequest request, Model uiModel, @RequestParam(required = true) String toolName) {
		List<Campus> campuses = campusService.findCampusesByTool(toolName);
		if (campuses == null || campuses.isEmpty()) {
			User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
			user.setViewCampus("ALL");
			return "redirect:/" + toolName;
		} else if (campuses.size() == 1) {
			User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
			user.setViewCampus(campuses.get(0).getCode());
			return "redirect:/" + toolName;
		}
		uiModel.addAttribute("campuses", campuses);
		uiModel.addAttribute("toolName", toolName);
		return "campus";
	}

	@RequestMapping(value = "/select", method = RequestMethod.GET)
	public String selectCampus(HttpServletRequest request, HttpServletResponse response, Model uiModel, @RequestParam(required = true) String campus, @RequestParam(required = true) String toolName) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		user.setViewCampus(campus);
		
		Cookie cookie = new Cookie("campusSelection", campus);
		cookie.setMaxAge(60*60*24*365); //one year
		cookie.setPath(request.getContextPath());
		response.addCookie(cookie);
		
		return "redirect:/" + toolName;
	}

}
