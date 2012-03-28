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

package org.kuali.mobility.events.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.events.entity.Category;
import org.kuali.mobility.events.entity.CategoryImpl;
import org.kuali.mobility.events.entity.Event;
import org.kuali.mobility.events.service.EventsService;
import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.shared.Constants;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/events")
public class EventsController {
	
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger( EventsController.class );

	private EventsService eventsService;

	public void setEventsService(EventsService eventsService) {
		this.eventsService = eventsService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String homePage(HttpServletRequest request, Model uiModel) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		String campus = null;
		if (user.getViewCampus() == null) {
			return "redirect:/campus?toolName=events";
		} else {
			campus = user.getViewCampus();
		}

		List<Category> categories = eventsService.getCategoriesByCampus(campus);
		LOG.debug( "Found "+categories.size()+" categories via local service for campus "+campus );
		uiModel.addAttribute("categories", categories);
		uiModel.addAttribute("campus", campus);
		return "events/list";
	}

	@RequestMapping(value = "/viewEvents", method = RequestMethod.GET)
	public String viewEvents(HttpServletRequest request, Model uiModel, @RequestParam(required = true) String categoryId, @RequestParam(required = false) String campus) throws Exception {
		List<Event> eventList = eventsService.getAllEvents(campus, categoryId);
		uiModel.addAttribute("events", eventList);
		Category category;
		if ( eventList!=null && eventList.size()>0 ) {
			category = eventList.get(0).getCategory() ;
		}
		else {
			category = eventsService.getCategory(campus, categoryId);
		}
		if (category == null) {
			LOG.error("Couldn't find category for categoryId - " + categoryId);
			category = new CategoryImpl();
			category.setCategoryId(categoryId);
			category.setTitle(categoryId);
		}
		uiModel.addAttribute("category", category );
		uiModel.addAttribute("campus", campus);
		return "events/eventsList";
	}

	@RequestMapping(value = "/viewEvent", method = RequestMethod.GET)
	public String viewEvent(HttpServletRequest request, Model uiModel, @RequestParam(required = true) String categoryId, @RequestParam(required = false) String campus, @RequestParam(required = true) String eventId) throws Exception {
		//Event event = eventsService.getEvent(campus, categoryId, eventId);
		//uiModel.addAttribute("event", event);
		uiModel.addAttribute("event", eventId);
		uiModel.addAttribute("categoryId", categoryId);
		uiModel.addAttribute("campus", campus);
		return "events/detail";
	}

	@RequestMapping(value = "/viewEvent", method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getEventJson(@RequestParam(required = true) String eventId) {
		return  eventsService.getEventJson(eventId);
	}
    
}
