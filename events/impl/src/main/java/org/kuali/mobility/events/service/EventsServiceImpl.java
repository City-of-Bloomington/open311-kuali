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
package org.kuali.mobility.events.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.kuali.mobility.events.dao.EventsDaoImpl;
import org.kuali.mobility.events.dao.EventsDaoUMImpl;
import org.kuali.mobility.events.entity.Category;
import org.kuali.mobility.events.entity.Event;
import org.kuali.mobility.events.util.CategoryPredicate;
import org.kuali.mobility.events.util.EventComparator;
import org.kuali.mobility.events.util.EventPredicate;
import org.springframework.stereotype.Service;


@Service
public class EventsServiceImpl implements EventsService {
    
    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(EventsServiceImpl.class);

    private EventsDaoImpl dao;
    
    @Override
    public Event getEvent(String campus, String categoryId, String eventId) {
        Event event;
        
        getDao().initData( campus, categoryId, eventId );
        
        List<Event> events = (List<Event>)CollectionUtils.select( getDao().getEvents(), new EventPredicate( campus, categoryId, eventId ) );
        
        if( events == null || events.isEmpty() )
        {
            LOG.info( "No events matched the criteria." );
            event = null;
        }
        else if( events.size() > 1 )
        {
            LOG.info( "Multiple events found that match the criteria." );
            event = events.get(0);
        }
        else
        {
            event = events.get(0);
        }
        
        return event;
    }

    @Override
    public List<Event> getAllEvents(String campus, String categoryId) {
        getDao().initData( campus, categoryId );
        List<Event> events =  getDao().getEvents();
        Collections.sort(events, new EventComparator());
        return events;
    }

    @Override
    public List<Category> getCategoriesByCampus(String campus) {
        List<Category> categories = new ArrayList<Category>();

        getDao().initData( campus );

        categories = (List<Category>)CollectionUtils.select( getDao().getCategories(), new CategoryPredicate( campus, null ) );
        
        return categories;
    }

    
    @Override
	public Category getCategory(String campus, String categoryId) {
    	
    	if ( getDao().getCategories() == null || getDao().getCategories().isEmpty() ) {
    		getDao().initData( campus );
    	}
    	
    	Category category = (Category) CollectionUtils.find ( getDao().getCategories(), new CategoryPredicate( campus, categoryId ) );
        
        return category;
	}

	/**
     * @return the dao
     */
    public EventsDaoImpl getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(EventsDaoImpl dao) {
        this.dao = dao;
    }

    
	public String getEventJson(final String eventId) {
		String jsonData = null;
		if (dao instanceof EventsDaoUMImpl) {
			jsonData = ((EventsDaoUMImpl) dao).getEventJson(eventId);
		}
		else {
			LOG.error("getEventJson method is NOT supported in dao implementation");
		}
		return jsonData;
	}
}
