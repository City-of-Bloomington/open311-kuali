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
package org.kuali.mobility.events.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.kuali.mobility.events.entity.Category;
import org.kuali.mobility.events.entity.Event;
import org.kuali.mobility.events.util.CategoryPredicate;

public class EventsDaoUMImpl extends EventsDaoImpl {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger( EventsDaoUMImpl.class );

    private String eventSourceFile;
    private String eventMappingFile;
    private String eventJsonURL; 
    
    @Override
	public void initData( final String campus, final String categoryId ) {
    	LOG.debug( "Loading event feed for category "+categoryId );
        if( null == getEvents() || getEvents().isEmpty() )
        {
        	LOG.debug( "Events list was empty, creating a new one." );
            //setEvents( new ArrayList<Event>() );
        }
        if( null == getCategories() || getCategories().isEmpty() )
        {
        	LOG.debug( "Category list was empty, initializing a new one." );
        	initData( campus );
        }

        List<Event> newEvents = new ArrayList<Event>();

		Category category = (Category) CollectionUtils.find ( getCategories(), new CategoryPredicate( campus, categoryId ) );;
		
		if ( category != null ) {
			LOG.debug( "Found category object for id "+categoryId );
	        try {
            	if( getEventSourceFile() != null ) {
            		LOG.debug( "Mapping events from file: "+getEventSourceFile() );
	            	newEvents = getMapper().mapData( newEvents, 
	            			getEventSourceFile(), 
	            			getEventMappingFile() );
            	}
	            else {
		            URL url = new URL( category.getUrlString() );
		            LOG.debug( "Mapping events from url: "+category.getUrlString() );
		            if( url != null ) {
		            	newEvents = getMapper().mapData( newEvents, url, getEventMappingFile() );
		            }
	            }
	        }
	        catch(MalformedURLException mue) {
	        	LOG.error( mue.getLocalizedMessage() );
	        }
	        catch( IOException ioe ) {
	        	LOG.error( ioe.getLocalizedMessage() );
	        }
	        catch( ClassNotFoundException cnfe ) {
	        	LOG.error( cnfe.getLocalizedMessage() );
	        }
		}
		
		for( Event e : newEvents )
		{
			e.setCategory(category);
		}
		
		setEvents( newEvents );
	}

	public String getEventSourceFile() {
		return eventSourceFile;
	}

	public void setEventSourceFile(String eventSourceFile) {
		this.eventSourceFile = eventSourceFile;
	}

	public String getEventMappingFile() {
		return eventMappingFile;
	}

	public void setEventMappingFile(String eventMappingFile) {
		this.eventMappingFile = eventMappingFile;
	}

	
	public String getEventJsonURL() {
		return eventJsonURL;
	}

	public void setEventJsonURL(String eventJsonURL) {
		this.eventJsonURL = eventJsonURL;
	}

	public String getEventJson(final String eventId) {
		//String BASEURL = "http://webservicesdev.dsc.umich.edu/events/getEvents/id/"; //7494-1135874
		String jsonData = null;
		try {
			
			URLConnection connection = new URL(getEventJsonURL() + eventId + "?_type=json").openConnection();
			jsonData = IOUtils.toString( connection.getInputStream(), "UTF-8" );
			
		} catch (MalformedURLException e) {
			LOG.error(e.getMessage());
			//e.printStackTrace();
		} catch (IOException e) {
			LOG.error(e.getMessage());
			//e.printStackTrace();
		}

		return jsonData;
	}
}
