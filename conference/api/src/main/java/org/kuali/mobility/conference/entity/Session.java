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
import java.util.List;

public class Session implements Serializable {

	private static final long serialVersionUID = 4116516860696428892L;

	private String id;
	private String title;
	private String startTime;
	private String endTime;
	private String description;
	private String location;
	private String latitude;
	private String longitude;
	private String link;
	private List<Attendee> speakers;
	
	public String getId() {
    	return id;
    }

	public void setId(String id) {
    	this.id = id;
    }

	public String getLatitude() {
    	return latitude;
    }

	public void setLatitude(String latitude) {
    	this.latitude = latitude;
    }

	public String getLongitude() {
    	return longitude;
    }

	public void setLongitude(String longitude) {
    	this.longitude = longitude;
    }

	public String getLink() {
    	return link;
    }

	public void setLink(String link) {
    	this.link = link;
    }

	public String getTitle() {
		return title;
	}

	public String getStartTime() {
    	return startTime;
    }

	public void setStartTime(String startTime) {
    	this.startTime = startTime;
    }

	public String getEndTime() {
    	return endTime;
    }

	public void setEndTime(String endTime) {
    	this.endTime = endTime;
    }

	public String getLocation() {
    	return location;
    }

	public void setLocation(String location) {
    	this.location = location;
    }

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Attendee> getSpeakers() {
		return speakers;
	}

	public void setSpeakers(List<Attendee> speakers) {
		this.speakers = speakers;
	}
	
}
