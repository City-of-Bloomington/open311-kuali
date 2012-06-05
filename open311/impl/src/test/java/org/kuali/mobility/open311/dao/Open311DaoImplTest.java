package org.kuali.mobility.open311.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.mobility.open311.entity.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Open311DaoImplTest {
	
private static final Logger LOG = Logger.getLogger( Open311DaoImplTest.class );
	
	private static ApplicationContext applicationContext;
	
    @BeforeClass
    public static void createApplicationContext() {
    	Open311DaoImplTest.setApplicationContext(new FileSystemXmlApplicationContext(getConfigLocations()));
    }

    private static String[] getConfigLocations() {
        return new String[] { "classpath:/Open311SpringBeans.xml" };
    }
    
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		Open311DaoImplTest.applicationContext = applicationContext;
	}
	
	@Test
	public void testGetServiceList() {
		Open311DaoImpl dao = (Open311DaoImpl) getApplicationContext().getBean("open311Dao");
		List<Service> serviceList = dao.getServiceList();
		LOG.info("Load open311 srevice " + serviceList.size());
		assertTrue( "Failed to load a list of open311 service.", serviceList!=null && serviceList.isEmpty()==false);
	}

	@Test
	public void testGetServiceJson() {
		Open311DaoImpl dao = (Open311DaoImpl) getApplicationContext().getBean("open311Dao");
		String data = dao.getServiceJson("4e090d24992b941e78000019");
		LOG.info("Service JSON data: " + data);
		assertTrue("Failed to load service JSON data ", data!=null && data.isEmpty()==false);
	}

}
