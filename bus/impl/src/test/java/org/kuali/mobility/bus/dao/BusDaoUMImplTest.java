/*
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
package org.kuali.mobility.bus.dao;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.kuali.mobility.bus.entity.Bus;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 *
 * @author Joe Swanson <joseswan@umich.edu>
 */
public class BusDaoUMImplTest {
    
	private static final Logger LOG = Logger.getLogger( BusDaoUMImplTest.class );
	
	private static ApplicationContext applicationContext;

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
	
    public BusDaoUMImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    	BusDaoUMImplTest.setApplicationContext(new FileSystemXmlApplicationContext(getConfigLocations()));
    }

    private static String[] getConfigLocations() {
        return new String[] { "classpath:/BusSpringBeans.xml" };
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Test
    public void testLoadRoutes() {
		BusDaoUMImpl dao = (BusDaoUMImpl)getApplicationContext().getBean("busDao");
        dao.loadRoutes();
        
		assertTrue( "Failed to find bus routes.", dao.getBusRoutes() != null );
    }
    
    @Test
    public void testLoadBusLocations() {
		BusDaoUMImpl dao = (BusDaoUMImpl)getApplicationContext().getBean("busDao");
        dao.loadBusLocations();
        
        if( dao.getBuses() != null ) {
            for( Bus b : dao.getBuses() ) {
                LOG.debug( "Bus: "+b.getId()+" : "+b.getRouteName()+" loaded.");
            }
        }
        
		assertTrue( "Failed to find bus routes.", dao.getBuses() != null );
    }
}
