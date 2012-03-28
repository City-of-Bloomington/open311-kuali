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
package org.kuali.mobility.events.entity;

import java.util.Date;
import java.util.List;

public interface Event {

	public abstract String getContactEmail();

	public abstract void setContactEmail(String contactEmail);

	public abstract List<List<String>> getOtherInfo();

	public abstract void setOtherInfo(List<List<String>> otherInfo);

	public abstract String getContact();

	public abstract void setContact(String contact);

	public abstract String getCost();

	public abstract void setCost(String cost);

	public abstract Date getEndDate();

	public abstract void setEndDate(Date endDate);

	public abstract String getDisplayStartTime();

	public abstract void setDisplayStartTime(String displayStartTime);

	public abstract String getDisplayEndTime();

	public abstract void setDisplayEndTime(String displayEndTime);

	public abstract List<String> getDescription();

	public abstract void setDescription(List<String> description);

	public abstract String getDisplayEndDate();

	public abstract void setDisplayEndDate(String displayEndDate);

	public abstract String getLocation();

	public abstract void setLocation(String location);

	public abstract String getLink();

	public abstract void setLink(String link);

	public abstract String getDisplayStartDate();

	public abstract void setDisplayStartDate(String displayStartDate);

	public abstract boolean isAllDay();

	public abstract void setAllDay(boolean allDay);

	public abstract String getEventId();

	public abstract void setEventId(String eventId);

	public abstract String getTitle();

	public abstract void setTitle(String title);

	public abstract Date getStartDate();

	public abstract void setStartDate(Date startDate);

	/**
	 * @param category the category to set
	 */
	public abstract void setCategory(Category category);

	/**
	 * @return the category
	 */
	public abstract Category getCategory();

	public abstract boolean equals(Object o);

	public abstract int hashCode();

}