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
package org.kuali.mobility.email.service;

import edu.iu.uis.sit.util.mail.AuthenticatedMailer;

public class IUEmailServiceImpl implements EmailService {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(IUEmailServiceImpl.class);
	
	private String username;
	private String password;

	@Override
	public boolean sendEmail(String body, String subject, String emailAddressTo, String emailAddressFrom) {
        try {
            AuthenticatedMailer mailer = new AuthenticatedMailer(emailAddressFrom, username, password);
            mailer.sendMessage(emailAddressTo, subject, body);
        } catch (Exception e) {
            LOG.error("Error in sendEmail message", e);
            return false;
        }
        return true;
    }
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
