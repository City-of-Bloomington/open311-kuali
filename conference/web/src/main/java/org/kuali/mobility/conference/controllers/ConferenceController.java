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

package org.kuali.mobility.conference.controllers;

import java.util.List;

import org.kuali.mobility.conference.entity.Attendee;
import org.kuali.mobility.conference.entity.Session;
import org.kuali.mobility.conference.service.ConferenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller 
@RequestMapping("/conference")
public class ConferenceController {

	@Autowired
	private ConferenceService conferenceService;
    public void setConferenceService(ConferenceService conferenceService) {
    	this.conferenceService = conferenceService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel) {
		//List<Attendee> attendees = conferenceService.findAllAttendees();
		//uiModel.addAttribute("attendees", attendees);
    	return "conference/index";
    }
    
    @RequestMapping(value = "/attendees", method = RequestMethod.GET)
    public String attendees(Model uiModel) {
		List<Attendee> attendees = conferenceService.findAllAttendees();
		uiModel.addAttribute("attendees", attendees);
    	return "conference/attendees"; 
    }
	
	@RequestMapping(value = "/attendeeDetails", method = RequestMethod.GET)
	public String attendeeDetails(Model uiModel, @RequestParam(required = true) int id) {
	    List<Attendee> attendees = conferenceService.findAllAttendees();
	    uiModel.addAttribute("attendee", attendees.get(id));
	    return "conference/attendeeDetails";
	}

    @RequestMapping(value = "/sessions", method = RequestMethod.GET)
    public String sessions(Model uiModel) {
		List<Session> sessions = conferenceService.findAllSessions();
		uiModel.addAttribute("sessions", sessions);
    	return "conference/sessions";
    }
	
	@RequestMapping(value = "/sessionDetails", method = RequestMethod.GET)
	public String sessionDetails(Model uiModel, @RequestParam(required = true) int id) {
	    List<Session> sessions = conferenceService.findAllSessions();
	    uiModel.addAttribute("session", sessions.get(id));
	    return "conference/sessionDetails";
	}
	
	@RequestMapping(value = "/sessionSpeakerDetails", method = RequestMethod.GET)
	public String sessionSpeakerDetails(Model uiModel, @RequestParam(required = true) int id) {
	    List<Session> sessions = conferenceService.findAllSessions();
	    uiModel.addAttribute("session", sessions.get(id));
	    return "conference/sessionSpeakerDetails";
	}
	
}
