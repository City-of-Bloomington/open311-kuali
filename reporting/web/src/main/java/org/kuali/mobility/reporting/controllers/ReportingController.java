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
 
package org.kuali.mobility.reporting.controllers;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.reporting.domain.Incident;
import org.kuali.mobility.reporting.entity.Submission;
import org.kuali.mobility.reporting.entity.SubmissionAttribute;
import org.kuali.mobility.reporting.service.ReportingService;
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
@RequestMapping("/reporting")
public class ReportingController {
    
	public static final String INCIDENT_TYPE = "INCIDENT";
	public static final String INCIDENT_GROUP = "INCIDENT_GROUP";
	public static final String SUMMARY = "SUMMARY";
	public static final String EMAIL = "EMAIL";
	public static final String AFFILLIATION_STUDENT = "AFFILLIATION_STUDENT";
	public static final String AFFILLIATION_FACULTY = "AFFILLIATION_FACULTY";
	public static final String AFFILLIATION_STAFF = "AFFILLIATION_STAFF";
	public static final String AFFILLIATION_OTHER = "AFFILLIATION_OTHER";
	public static final String CONTACT_ME = "CONTACT_ME";
	
    @Autowired
    private ReportingService reportingService;

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model uiModel, HttpServletRequest request) {
    	//User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		
    	return "reporting/index";
    }

    
    // 
    // Reporting Administration Front End
    //
    
    @RequestMapping(value = "/admin/index", method = RequestMethod.GET)
    public String adminIndex(Model uiModel, HttpServletRequest request) {
    	//User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		
    	// TODO: Based on the user, find the reporting types that she is allowed to see and use as the filter.
    	
    	List<Submission> submissions = reportingService.findAllSubmissions();
    	
   		uiModel.addAttribute("submissions", submissions);    	
    	return "reporting/admin/index";
    }

    
    
    
    //
    // Incident Reporting Tool Front End
    // TODO: Should probably move this to an IncidentController in a separate incident tool 
    // 
    
    @RequestMapping(value="/incidentForm", method = RequestMethod.GET)
    public String incidentForm(Model uiModel, HttpServletRequest request) {
    	//User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
   		Incident incident = new Incident();
   		uiModel.addAttribute("incident", incident);
    	return "incident/form";
    }

    @RequestMapping(value="/incidentPost", method = RequestMethod.POST)
    public String submitIncident(Model uiModel, HttpServletRequest request, @ModelAttribute("incident") Incident incident, BindingResult result) {
       	User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY); 	
    	    	
        if (isValidIncident(incident, result)) {
        	Submission submission = new Submission();
        	submission.setType(INCIDENT_TYPE);
        	submission.setActive(1);
        	submission.setGroup(INCIDENT_GROUP);
        	//submission.setIpAddress(InetAddress.getLocalHost().getHostAddress());
        	submission.setPostDate(new Timestamp(new Date().getTime()));
        	submission.setRevisionNumber(0L);
        	submission.setUserId(user.getPrincipalName());
        	//submission.setUserAgent();
        	
            Long pk = reportingService.saveSubmission(submission);

        	SubmissionAttribute submissionAttributeSummary = new SubmissionAttribute();
        	submissionAttributeSummary.setKey(SUMMARY);
        	submissionAttributeSummary.setValueLargeText(incident.getSummary());
        	submissionAttributeSummary.setSubmissionId(pk);
        	submissionAttributeSummary.setSubmission(submission);
        	
        	SubmissionAttribute submissionAttributeContactMe = new SubmissionAttribute();
        	submissionAttributeContactMe.setKey(CONTACT_ME);
        	submissionAttributeContactMe.setValueNumber(incident.isContactMe() ? 1L : 0L);
        	submissionAttributeContactMe.setSubmissionId(pk);
        	submissionAttributeContactMe.setSubmission(submission);
        	
        	SubmissionAttribute submissionAttributeEmail = new SubmissionAttribute();
        	submissionAttributeEmail.setKey(EMAIL);
        	submissionAttributeEmail.setValueText(incident.getEmail());
        	submissionAttributeEmail.setSubmissionId(pk);
        	submissionAttributeEmail.setSubmission(submission);
        	
        	SubmissionAttribute submissionAttributeAffiliationStudent = new SubmissionAttribute();
        	submissionAttributeAffiliationStudent.setKey(AFFILLIATION_STUDENT);
        	submissionAttributeAffiliationStudent.setValueText(incident.getAffiliationStudent());
        	submissionAttributeAffiliationStudent.setSubmissionId(pk);
        	submissionAttributeAffiliationStudent.setSubmission(submission);
        	
        	SubmissionAttribute submissionAttributeAffiliationFaculty = new SubmissionAttribute();
        	submissionAttributeAffiliationFaculty.setKey(AFFILLIATION_FACULTY);
        	submissionAttributeAffiliationFaculty.setValueText(incident.getAffiliationFaculty());
        	submissionAttributeAffiliationFaculty.setSubmissionId(pk);
        	submissionAttributeAffiliationFaculty.setSubmission(submission);
        	
        	SubmissionAttribute submissionAttributeAffiliationStaff = new SubmissionAttribute();
        	submissionAttributeAffiliationStaff.setKey(AFFILLIATION_STAFF);
        	submissionAttributeAffiliationStaff.setValueText(incident.getAffiliationStaff());
        	submissionAttributeAffiliationStaff.setSubmissionId(pk);
        	submissionAttributeAffiliationStaff.setSubmission(submission);
        	
        	SubmissionAttribute submissionAttributeAffiliationOther = new SubmissionAttribute();
        	submissionAttributeAffiliationOther.setKey(AFFILLIATION_OTHER);
        	submissionAttributeAffiliationOther.setValueText(incident.getAffiliationOther());
        	submissionAttributeAffiliationOther.setSubmissionId(pk);
        	submissionAttributeAffiliationOther.setSubmission(submission);
        	
        	List<SubmissionAttribute> attributes = new ArrayList<SubmissionAttribute>();
        	attributes.add(submissionAttributeSummary);
        	attributes.add(submissionAttributeEmail);
        	attributes.add(submissionAttributeContactMe);
        	attributes.add(submissionAttributeAffiliationStudent);
        	attributes.add(submissionAttributeAffiliationFaculty);
        	attributes.add(submissionAttributeAffiliationStaff);
        	attributes.add(submissionAttributeAffiliationOther);
        	
        	submission.setAttributes(attributes);
        	
            reportingService.saveSubmission(submission);
            
            return "incident/thanks";                	
        } else {
        	return "incident/form";    	
        }
    }    
    
    private boolean isValidIncident(Incident incident, BindingResult result) {
    	boolean hasErrors = false;
    	Errors errors = ((Errors) result);
    	if (incident == null || incident.getSummary() == null || "".equals(incident.getSummary().trim())) {
    		errors.rejectValue("summary", "", "Please enter a summary.");
    		hasErrors = true;
    	}
    	return !hasErrors;
    }

}
