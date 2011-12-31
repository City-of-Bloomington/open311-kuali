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

package org.kuali.mobility.feedback.service;

import org.kuali.mobility.email.service.EmailService;
import org.kuali.mobility.feedback.dao.FeedbackDao;
import org.kuali.mobility.feedback.entity.Feedback;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

public class FeedbackServiceImpl implements FeedbackService {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(FeedbackServiceImpl.class);

	@Autowired
    private FeedbackDao feedbackDao;
	
	@Autowired
	private EmailService emailService;
	
	private String toEmailAddress;

	private String fromEmailAddress;
	
    @Override
    @Transactional
	public void saveFeedback(Feedback feedback) {
		feedbackDao.saveFeedback(feedback);
		sendEmail(feedback);
	}
    
    private void sendEmail(Feedback f) {
    	try {
    		String fromEmail = fromEmailAddress;
    		if (f.getEmail() != null) {
    			fromEmail = f.getEmail();
    		}
    		emailService.sendEmail(f.toString(), "MIU Feedback", toEmailAddress, fromEmail); 		
    	} catch (Exception e) {
    		LOG.error("Error sending feedback email " + f.getFeedbackId(), e);
    	}
    }
    
    public String getToEmailAddress() {
		return toEmailAddress;
	}

	public void setToEmailAddress(String toEmailAddress) {
		this.toEmailAddress = toEmailAddress;
	}

	public String getFromEmailAddress() {
		return fromEmailAddress;
	}

	public void setFromEmailAddress(String fromEmailAddress) {
		this.fromEmailAddress = fromEmailAddress;
	}
    
}


