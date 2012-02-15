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

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class EventImpl implements Serializable, Event {

    private static final long serialVersionUID = -2196031917411001051L;
    private String eventId;
    private boolean allDay;
    private String title;
    private Date startDate;
    private Date endDate;
    private String displayStartDate;
    private String displayEndDate;
    private String displayStartTime;
    private String displayEndTime;
    private String location;
    private List<String> description;
    private String link;
    private String contact;
    private String cost;
    private List<List<String>> otherInfo;
    private String contactEmail;
    private Category category;

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getContactEmail()
	 */
    @Override
	public String getContactEmail() {
        return contactEmail;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setContactEmail(java.lang.String)
	 */
    @Override
	public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getOtherInfo()
	 */
    @Override
	public List<List<String>> getOtherInfo() {
        return otherInfo;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setOtherInfo(java.util.List)
	 */
    @Override
	public void setOtherInfo(List<List<String>> otherInfo) {
        this.otherInfo = otherInfo;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getContact()
	 */
    @Override
	public String getContact() {
        return contact;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setContact(java.lang.String)
	 */
    @Override
	public void setContact(String contact) {
        this.contact = contact;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getCost()
	 */
    @Override
	public String getCost() {
        return cost;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setCost(java.lang.String)
	 */
    @Override
	public void setCost(String cost) {
        this.cost = cost;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getEndDate()
	 */
    @Override
	public Date getEndDate() {
        return endDate;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setEndDate(java.util.Date)
	 */
    @Override
	public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getDisplayStartTime()
	 */
    @Override
	public String getDisplayStartTime() {
        return displayStartTime;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setDisplayStartTime(java.lang.String)
	 */
    @Override
	public void setDisplayStartTime(String displayStartTime) {
        this.displayStartTime = displayStartTime;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getDisplayEndTime()
	 */
    @Override
	public String getDisplayEndTime() {
        return displayEndTime;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setDisplayEndTime(java.lang.String)
	 */
    @Override
	public void setDisplayEndTime(String displayEndTime) {
        this.displayEndTime = displayEndTime;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getDescription()
	 */
    @Override
	public List<String> getDescription() {
        return description;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setDescription(java.util.List)
	 */
    @Override
	public void setDescription(List<String> description) {
        this.description = description;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getDisplayEndDate()
	 */
    @Override
	public String getDisplayEndDate() {
        return displayEndDate;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setDisplayEndDate(java.lang.String)
	 */
    @Override
	public void setDisplayEndDate(String displayEndDate) {
        this.displayEndDate = displayEndDate;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getLocation()
	 */
    @Override
	public String getLocation() {
        return location;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setLocation(java.lang.String)
	 */
    @Override
	public void setLocation(String location) {
        this.location = location;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getLink()
	 */
    @Override
	public String getLink() {
        return link;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setLink(java.lang.String)
	 */
    @Override
	public void setLink(String link) {
        this.link = link;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getDisplayStartDate()
	 */
    @Override
	public String getDisplayStartDate() {
        return displayStartDate;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setDisplayStartDate(java.lang.String)
	 */
    @Override
	public void setDisplayStartDate(String displayStartDate) {
        this.displayStartDate = displayStartDate;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#isAllDay()
	 */
    @Override
	public boolean isAllDay() {
        return allDay;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setAllDay(boolean)
	 */
    @Override
	public void setAllDay(boolean allDay) {
        this.allDay = allDay;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getEventId()
	 */
    @Override
	public String getEventId() {
        return eventId;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setEventId(java.lang.String)
	 */
    @Override
	public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getTitle()
	 */
    @Override
	public String getTitle() {
        return title;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setTitle(java.lang.String)
	 */
    @Override
	public void setTitle(String title) {
        this.title = title;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getStartDate()
	 */
    @Override
	public Date getStartDate() {
        return startDate;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setStartDate(java.util.Date)
	 */
    @Override
	public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#setCategory(org.kuali.mobility.events.entity.Category)
	 */
    @Override
	public void setCategory(Category category) {
        this.category = category;
    }

    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#getCategory()
	 */
    @Override
	public Category getCategory() {
        return category;
    }
    
    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#equals(java.lang.Object)
	 */
    @Override
	public boolean equals( Object o )
    {
        boolean isEqual = false;
        
        if( o != null && o instanceof Event )
        {
            if( getEventId() != null && getEventId().equalsIgnoreCase( ((Event)o).getEventId() ) )
            {
                isEqual = true;
            }
        }
        
        return isEqual;
    }
    
    /* (non-Javadoc)
	 * @see org.kuali.mobility.events.entity.Event#hashCode()
	 */
    @Override
	public int hashCode()
    {
        return (41 + Integer.parseInt( getEventId().substring(5) ) );
    }
}
