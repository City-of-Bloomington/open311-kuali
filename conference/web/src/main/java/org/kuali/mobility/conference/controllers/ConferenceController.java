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

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.conference.entity.Attendee;
import org.kuali.mobility.conference.entity.ContentBlock;
import org.kuali.mobility.conference.entity.MenuItem;
import org.kuali.mobility.conference.entity.Session;
import org.kuali.mobility.conference.entity.SessionFeedback;
import org.kuali.mobility.conference.service.ConferenceService;
import org.kuali.mobility.configparams.service.ConfigParamService;
import org.kuali.mobility.email.service.EmailService;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import flexjson.JSONSerializer;

@Controller
@RequestMapping("/conference")
public class ConferenceController {

	@Autowired
	private ConferenceService conferenceService;

	@Autowired
	private ConfigParamService configParamService;

	@Autowired
	private EmailService emailService;

	public void setConferenceService(ConferenceService conferenceService) {
		this.conferenceService = conferenceService;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model uiModel) {
		Date d = new Date();
		DateFormat formatter = new SimpleDateFormat("MMddyy");
		String today = formatter.format(d);
		if ("092811".equals(today) || "092911".equals(today) || "093011".equals(today) || "100111".equals(today)) {
		} else { 
			today = "";
		}
		//uiModel.addAttribute("today", today);
		return "conference/index";
	}
	
    @RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
    @ResponseBody
    public String getMenuJson() {
    	List<MenuItem> menuItems = conferenceService.findAllMenuItems();
    	return new JSONSerializer().exclude("*.class").deepSerialize(menuItems);
    }

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcome(Model uiModel) {
		List<ContentBlock> contentBlocks = conferenceService.findAllContentBlocks();
		uiModel.addAttribute("contentBlocks", contentBlocks);
		return "conference/welcome";
	}
	
	@RequestMapping(value = "/featuredSpeakers", method = RequestMethod.GET)
	public String featuredSpeakers(Model uiModel) {
		List<ContentBlock> contentBlocks = conferenceService.findFeaturedSpeakers();
		uiModel.addAttribute("contentBlocks", contentBlocks);
		return "conference/featuredSpeakers";
	}
	
	@RequestMapping(value = "/twitter", method = RequestMethod.GET)
	public String twitter(Model uiModel) {
		List<ContentBlock> contentBlocks = conferenceService.findTwitter();
		uiModel.addAttribute("contentBlocks", contentBlocks);
		return "conference/twitter";
	}
	
	@RequestMapping(value = "/map", method = RequestMethod.GET)
	public String map(Model uiModel) {
		List<ContentBlock> contentBlocks = conferenceService.findMap();
		uiModel.addAttribute("contentBlocks", contentBlocks);
		return "conference/map";
	}
	
	@RequestMapping(value = "/attendeeGroups", method = RequestMethod.GET)
	public String attendeeGroups(Model uiModel) {
		return "conference/attendeeGroups";
	}

	@RequestMapping(value = "/attendees", method = RequestMethod.GET)
	public String attendees(@RequestParam(value="start", required=true) String start, @RequestParam(value="end", required=true) String end, Model uiModel) {
		List<Attendee> attendees = conferenceService.findAllAttendees(start.charAt(0), end.charAt(0));
		uiModel.addAttribute("attendees", attendees);
		return "conference/attendees";
	}

	@RequestMapping(value = "/attendeeDetails/{id}", method = RequestMethod.GET)
	public String attendeeDetails(@PathVariable("id") String id, Model uiModel) {
		Attendee attendee = conferenceService.findAttendeeById(id);
		uiModel.addAttribute("attendee", attendee);
		return "conference/attendeeDetails";
	}

	@RequestMapping(value = "/sessions", method = RequestMethod.GET)
	public String sessions(@RequestParam(value="date", required=false) String date, Model uiModel) {
		List<Session> sessions = conferenceService.findAllSessions(date);
		uiModel.addAttribute("sessions", sessions);
		return "conference/sessions";
	}

	@RequestMapping(value = "/sessionDetails/{id}", method = RequestMethod.GET)
	public String sessionDetails(@PathVariable("id") String id, Model uiModel) {
		Session session = conferenceService.findSessionById(id);
		uiModel.addAttribute("session", session);
		uiModel.addAttribute("sessionFeedback", new SessionFeedback());
		return "conference/sessionDetails";
	}

	@RequestMapping(value = "/sessionFeedback", method = RequestMethod.POST)
	public String submitFeedback(Model uiModel, HttpServletRequest request, @ModelAttribute("sessionFeedback") SessionFeedback sessionFeedback) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		sessionFeedback.setTimePosted(new Timestamp(System.currentTimeMillis()));
		sessionFeedback.setPrincipalName(user.getPrincipalName());
		sendEmail(sessionFeedback);
		return "conference/sessionFeedbackThanks";
	}

	@RequestMapping(value = "/kualiDaysFeedback", method = RequestMethod.POST)
	public String submitKualiDaysFeedback(Model uiModel, HttpServletRequest request, @ModelAttribute("sessionFeedback") SessionFeedback sessionFeedback) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		sessionFeedback.setTimePosted(new Timestamp(System.currentTimeMillis()));
		sessionFeedback.setPrincipalName(user.getPrincipalName());
		sendEmail(sessionFeedback);
		return "conference/kualiDaysSessionFeedbackThanks";
	}

	
	@RequestMapping(value = "/sessionSpeakerDetails", method = RequestMethod.GET)
	public String sessionSpeakerDetails(Model uiModel, @RequestParam(required = true) int id) {
		List<Session> sessions = conferenceService.findAllSessions("");
		uiModel.addAttribute("session", sessions.get(id));
		return "conference/sessionSpeakerDetails";
	}

	private void sendEmail(SessionFeedback f) {
		try {
			String emailAddress = configParamService.findValueByName("Feedback.SendEmail.EmailAddress");
			StringTokenizer stringTokenizer = new StringTokenizer(emailAddress);
			while (stringTokenizer.hasMoreTokens()) {
				String email = stringTokenizer.nextToken();
				//emailService.sendEmail(f.toString(), "SWITC Feedback; "+f.getSessionId()+":"+f.getRating(), email, configParamService.findValueByName("Core.EmailAddress"));
				emailService.sendEmail(f.toString(), "Conference Feedback; "+f.getSessionId()+":"+f.getRating(), email, configParamService.findValueByName("Core.EmailAddress"));
			}
		} catch (Exception e) {
		}
		//System.out.println(f.toString());
	}

}
