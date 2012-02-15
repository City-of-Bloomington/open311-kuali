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
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.persistence.EntityManager;

import org.kuali.mobility.events.entity.Category;
import org.kuali.mobility.events.entity.Event;
import org.kuali.mobility.util.mapper.DataMapperImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;

@Repository
public class EventsDaoImpl implements EventsDao, ApplicationContextAware {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger( EventsDaoImpl.class );
    
    private ApplicationContext applicationContext;
    
    private DataMapperImpl mapper;
    
	private String categorySourceFile;
	private String categorySourceUrl;
	private String categoryMappingFile;
	private String categoryMappingUrl;

    private List<Category> categories;
    private List<Event> events;
    
    public void initData( final String campus, final String categoryId, final String eventId )
    {
        if( null == getEvents() || getEvents().isEmpty() )
        {
            this.initData( campus, categoryId );
        }
    }
   
    
    @SuppressWarnings("unchecked")
	public void initData( final String campus, final String categoryId )
    {
        if( null == getEvents() || getEvents().isEmpty() )
        {
            setEvents( new ArrayList<Event>() );
        }

        List<Event> newEvents = new ArrayList<Event>();

		Category category = null;
		for( Category c : getCategories() )
		{
			if( c.getCategoryId() != null && c.getCategoryId().equalsIgnoreCase(categoryId) )
			{
				category = c;
				break;
			}
		}
		
		if ( category != null ) {
		
	        try
	        {
	            URL url = new URL( category.getUrlString() );
	            if( url != null )
	            {
	            	SyndFeedInput input = new SyndFeedInput();
	            	SyndFeed feed = null;
	            	feed = input.build( new InputStreamReader( url.openStream() ) );
	            	
	            	if( feed != null )
	            	{
	            		for( SyndEntryImpl entry : (List<SyndEntryImpl>)feed.getEntries() )
	            		{
	            			Event event = (Event)getApplicationContext().getBean("event");
	            			event.setTitle( entry.getTitle() );
	            			if( entry.getDescription() != null ) {
	            				List<String> d = new ArrayList<String>();
	            				d.add( entry.getDescription().getValue() );
	            				event.setDescription( d );
	            			}
	            			event.setLink( entry.getLink() );
	        				try
	        				{
	        					event.setStartDate(new Timestamp(entry.getPublishedDate().getTime()));
	        				}
	        				catch( Exception e )
	        				{
	        					LOG.error( "Error creating timestamp for Event: "+entry.getTitle() );
	        					LOG.error( e.getLocalizedMessage() );
	        				}
	        				event.setEventId(category.getCategoryId());
	        				try {
	        					event.setEventId(URLEncoder.encode(entry.getUri(), "UTF-8"));
	        				} catch (UnsupportedEncodingException e) {
	        					event.setEventId(entry.getUri());
	        				}
	        				
	        				newEvents.add( event );
	            		}
	            	}
	            }
	        }
	        catch( MalformedURLException mue )
	        {
	            LOG.error( mue.getLocalizedMessage() );
	        }
	        catch( FeedException fe )
	        {
	        	LOG.error( fe.getLocalizedMessage() );
	        }
	        catch( IOException ioe )
	        {
	            LOG.error( ioe.getLocalizedMessage() );
	        }
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
    
    public void initData( final String campus )
    {
        if( null == getCategories() || getCategories().isEmpty() )
        {
            List<Category> cats = new ArrayList<Category>();

    		boolean isCategorySourceUrlAvailable = (getCategorySourceUrl() != null ? true : false);
    		boolean isCategoryMappingUrlAvailable = (getCategoryMappingUrl() != null ? true : false);

            try
            {
            	if( isCategorySourceUrlAvailable ) {
        			LOG.debug( "Loading categories from "+getCategorySourceUrl());
            		if( isCategoryMappingUrlAvailable ) {
            			cats = mapper.mapData( cats, 
            					new URL( getCategorySourceUrl() ), 
            					new URL( getCategoryMappingUrl() ) );
            		} else {
            			cats = mapper.mapData( cats, 
            					new URL( getCategorySourceUrl() ), 
            					getCategoryMappingFile() );
            		}
            	}
            	else {
        			LOG.debug( "Loading categories from "+getCategorySourceFile());
            		if( isCategoryMappingUrlAvailable ) {
            			// not supported in mapper.mapData
            			LOG.error( "DataMapper does not support this case." );
            			return;
            		} else {
            			cats = mapper.mapData( cats, 
            					getCategorySourceFile(), 
            					getCategoryMappingFile() );
            			
            		}
            	}
            }
            catch( ClassNotFoundException cnfe )
            {
                LOG.error( cnfe.getMessage() );
            }
            catch( MalformedURLException mue )
            {
                LOG.error( mue.getMessage() );
            }
            catch( IOException ioe )
            {
                LOG.error( ioe.getMessage() );
            }
            
            LOG.debug( cats.size()+" categories mapped.");
            if( cats.size() > 0 )
            {
                setCategories( cats );
            }
        }
    }
    
    /**
     * @return the events
     */
    @Override
    public List<Event> getEvents() {
        return events;
    }

    /**
     * @param events the events to set
     */
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    /**
     * @return the categories
     */
    @Override
    public List<Category> getCategories() {
        return categories;
    }

    /**
     * @param categories the categories to set
     */
    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    /**
     * @return the mapper
     */
    public DataMapperImpl getMapper() {
        return mapper;
    }

    /**
     * @param mapper the mapper to set
     */
    public void setMapper(DataMapperImpl mapper) {
        this.mapper = mapper;
    }

	public String getCategorySourceFile() {
		return categorySourceFile;
	}

	public void setCategorySourceFile(String categorySourceFile) {
		this.categorySourceFile = categorySourceFile;
	}

	public String getCategorySourceUrl() {
		return categorySourceUrl;
	}

	public void setCategorySourceUrl(String categorySourceUrl) {
		this.categorySourceUrl = categorySourceUrl;
	}

	public String getCategoryMappingFile() {
		return categoryMappingFile;
	}

	public void setCategoryMappingFile(String categoryMappingFile) {
		this.categoryMappingFile = categoryMappingFile;
	}

	public String getCategoryMappingUrl() {
		return categoryMappingUrl;
	}

	public void setCategoryMappingUrl(String categoryMappingUrl) {
		this.categoryMappingUrl = categoryMappingUrl;
	}


	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}


	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

}
