package org.kuali.mobility.events.dao;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.kuali.mobility.events.entity.Category;
import org.kuali.mobility.events.entity.Event;

public class EventsDaoUMImpl extends EventsDaoImpl {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger( EventsDaoUMImpl.class );

    private String eventSourceFile;
    private String eventMappingFile;
    
    @Override
	public void initData( final String campus, final String categoryId ) {
    	LOG.debug( "Loading event feed for category "+categoryId );
        if( null == getEvents() || getEvents().isEmpty() )
        {
        	LOG.debug( "Events list was empty, creating a new one." );
            setEvents( new ArrayList<Event>() );
        }
        if( null == getCategories() || getCategories().isEmpty() )
        {
        	LOG.debug( "Category list was empty, initializing a new one." );
        	initData( campus );
        }

        List<Event> newEvents = new ArrayList<Event>();

		Category category = null;
		for( Category c : getCategories() )
		{
			if( c.getCategoryId() != null && c.getCategoryId().equalsIgnoreCase(categoryId) )
			{
				LOG.debug( "Found category object for id "+categoryId );
				category = c;
				break;
			}
		}
		
		if ( category != null ) {
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
		
        // TODO: Fix this.  It works but doing it preemptively is better.
        List<Event> oldEvents = getEvents();
        oldEvents.addAll( newEvents );
        
        HashSet tempSet = new HashSet();
        tempSet.addAll( oldEvents );
        oldEvents.clear();
        oldEvents.addAll( tempSet );

        setEvents( oldEvents );
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

}
