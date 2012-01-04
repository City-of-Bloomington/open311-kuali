/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
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
