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

import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author joseswan
 */
public class EventsDaoImplTest {
    
    private static ApplicationContext applicationContext;

    @BeforeClass
    public static void createApplicationContext() throws Exception
    {
        setApplicationContext(new ClassPathXmlApplicationContext( getConfigLocations() ));       
    }
    
    private static String[] getConfigLocations() {
        return new String[] { "/SpringBeans.xml","/webmvc-config.xml" };
    }

    /**
     * Test of initData method, of class EventsDaoImpl.
     */
    @Test
    public void testInitData_3args() {
    }

    /**
     * Test of initData method, of class EventsDaoImpl.
     */
    @Test
    public void testInitData_String_String() {
//        EventsDaoImpl dao = (EventsDaoImpl)getApplicationContext().getBean("eventDao");
//        
//        int eventCount = 0;
//        
//        dao.initData( null, "14" );
//        
//        assertTrue( "Failed to load events.", dao.getEvents() != null && dao.getEvents().size() > 0 );
//
//        eventCount = dao.getEvents().size();
//        System.out.append( "Events list length: " + eventCount );
//        
//        dao.initData( null, "12" );
//
//        assertTrue( "Failed to load events.", dao.getEvents() != null && dao.getEvents().size() > eventCount );
//
//        eventCount = dao.getEvents().size();
//        System.out.append( "Events list length: " + eventCount );
//        
//        dao.initData( null, "12" );
//        
//        assertTrue( "Failed to load events.", dao.getEvents() != null && dao.getEvents().size() > 0 );
//        assertTrue( "Event list improperly removed duplicates.", dao.getEvents().size() == eventCount );
//
//        eventCount = dao.getEvents().size();
//        System.out.append( "Events list length: " + eventCount );
    }

    /**
     * Test of initData method, of class EventsDaoImpl.
     */
    @Test
    public void testInitData_String() {
//        EventsDaoImpl dao = (EventsDaoImpl)getApplicationContext().getBean("eventDao");
//        
//        dao.initData( null );
//        
//        assertTrue( "Failed to load categories.", dao.getCategories() != null && dao.getCategories().size() > 0 );
    }

    /**
     * @return the applicationContext
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * @param aApplicationContext the applicationContext to set
     */
    public static void setApplicationContext(ApplicationContext aApplicationContext) {
        applicationContext = aApplicationContext;
    }
    
}
