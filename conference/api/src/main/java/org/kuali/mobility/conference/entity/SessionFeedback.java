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

package org.kuali.mobility.conference.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class SessionFeedback implements Serializable {

	private static final long serialVersionUID = 3061400673321665040L;

	private String rating;
	private String comments;
	private String sessionId;
	private String sessionName;
	private String principalName;
	private Timestamp timePosted;

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getSessionId() {
    	return sessionId;
    }

	public void setSessionId(String sessionId) {
    	this.sessionId = sessionId;
    }

	public String getSessionName() {
		return sessionName;
	}

	public void setSessionName(String sessionName) {
		this.sessionName = sessionName;
	}

	public String getPrincipalName() {
		return principalName;
	}

	public void setPrincipalName(String principalName) {
		this.principalName = principalName;
	}

	public Timestamp getTimePosted() {
		return timePosted;
	}

	public void setTimePosted(Timestamp timePosted) {
		this.timePosted = timePosted;
	}

	@Override
	public String toString() {
		String newline = "\r\n";
		 String str = "Session ID: "+ this.getSessionId();
		 str = str + newline + "Session Name: " + this.getSessionName();
		 str = str + newline + "Rating: " + this.getRating();
		 str = str + newline + "Comments: " + this.getComments();
		 str = str + newline + "Network ID: " + this.getPrincipalName();
		 str = str + newline + "Time Posted: " + this.getTimePosted();
		 return str;
	}
}
