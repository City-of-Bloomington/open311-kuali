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
package org.kuali.mobility.events.util;

import org.apache.commons.collections.Predicate;
import org.kuali.mobility.events.entity.Category;
import org.kuali.mobility.events.entity.Event;

/**
 *
 * @author joseswan
 */
public final class EventPredicate implements Predicate {

    private String campus;
    private String categoryId;
    private String eventId;
    
    public EventPredicate( final String campus, final String categoryId, final String eventId)
    {
        this.setCampus(campus);
        this.setCategoryId(categoryId);
        this.setEventId(eventId);
    }
    
    @Override
    public boolean evaluate(Object obj) {
        boolean campusMatch = false;
        boolean categoryMatch = false;
        boolean eventMatch = false;

        if( obj instanceof Event )
        {
            if( getCampus() == null )
            {
                campusMatch = true;
            }
            if( getCampus() != null && getCampus().equalsIgnoreCase( ((Category)obj).getCampus() ))
            {
                campusMatch = true;
            }
            if( getCategoryId() == null )
            {
                categoryMatch = true;
            }
            if( getCategoryId() != null && getCategoryId().equalsIgnoreCase( ((Category)obj).getCategoryId() ) )
            {
                categoryMatch = true;
            }
            if( getEventId() == null )
            {
                eventMatch = true;
            }
            if( getEventId() != null && getEventId().equalsIgnoreCase( ((Event)obj).getEventId() ) )
            {
                eventMatch = true;
            }
        }
        return campusMatch && categoryMatch && eventMatch;
    }

    /**
     * @return the campus
     */
    public String getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(String campus) {
        this.campus = campus;
    }

    /**
     * @return the categoryId
     */
    public String getCategoryId() {
        return categoryId;
    }

    /**
     * @param categoryId the categoryId to set
     */
    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    /**
     * @return the eventId
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * @param eventId the eventId to set
     */
    public void setEventId(String eventId) {
        this.eventId = eventId;
    }
}
