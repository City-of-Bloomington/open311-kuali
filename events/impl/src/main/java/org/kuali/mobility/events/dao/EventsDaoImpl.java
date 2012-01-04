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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.persistence.EntityManager;

import org.kuali.mobility.events.entity.Category;
import org.kuali.mobility.events.entity.Event;
import org.kuali.mobility.util.mapper.DataMapperImpl;
import org.springframework.stereotype.Repository;

@Repository
public class EventsDaoImpl implements EventsDao {

    private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger( EventsDaoImpl.class );
    
//    @PersistenceContext
    private EntityManager entityManager;
    
    private DataMapperImpl mapper;
    
    private List<Category> categories;
    private List<Event> events;
    
    public void initData( final String campus, final String categoryId, final String eventId )
    {
        if( null == getEvents() || getEvents().isEmpty() )
        {
            this.initData( campus, categoryId );
        }
        
        // TODO: Implement a check for the category presence in cache.
    }
   
    public void initData( final String campus, final String categoryId )
    {
        if( null == getEvents() || getEvents().isEmpty() )
        {
            
            setEvents( new ArrayList<Event>() );
        }

        List<Event> newEvents = new ArrayList<Event>();

        
        // TODO: Implement proper caching mechanism at this level.
        try
        {
            URL url = new URL( "http://localhost:8180/events/getEvents?category="+categoryId );
            newEvents = mapper.mapData( newEvents, url, "eventMapping.xml" );
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

            try
            {
//                URL url = new URL("http://webservicesdev.dsc.umich.edu/events/getCategories");
                URL url = new URL("http://localhost:8180/events/getCategories");
                cats = mapper.mapData( cats, url, "categoryMapping.xml" );
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

            if( cats.size() > 0 )
            {
                setCategories( cats );
            }
        }
    }
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
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

}
