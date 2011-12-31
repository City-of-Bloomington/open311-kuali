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

/**
 * Interface for a contract for sending emails
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
public interface EmailService {
    
	/**
	 * Send an email to a single recipient
	 * @param body
	 * @param subject
	 * @param emailAddressTo the recipient
	 * @param emailAddressFrom the sender
	 * @return true if successful
	 */
    boolean sendEmail(String body, String subject, String emailAddressTo, String emailAddressFrom);
    
}
