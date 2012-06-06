package org.kuali.mobility.open311.service;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.mobility.open311.dao.Open311DaoImpl;
import org.kuali.mobility.open311.entity.ServiceEntity;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

public class Open311ServiceImplTest {
	
	private static final Logger LOG = Logger.getLogger( Open311ServiceImplTest.class );
	
	private Open311ServiceImpl open311ServiceImpl;
	
	private static ApplicationContext applicationContext;
	
    @BeforeClass
    public static void createApplicationContext() {
    	Open311ServiceImplTest.setApplicationContext(new FileSystemXmlApplicationContext(getConfigLocations()));
    }

    private static String[] getConfigLocations() {
        return new String[] { "classpath:/Open311SpringBeans.xml" };
    }
    
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		Open311ServiceImplTest.applicationContext = applicationContext;
	}
	
	@Test
	public void testGetService() {
		Open311DaoImpl dao = (Open311DaoImpl) getApplicationContext().getBean("open311Dao");
		open311ServiceImpl = new Open311ServiceImpl();
		open311ServiceImpl.setDao(dao);
		List<ServiceEntity> serviceList = open311ServiceImpl.getService();
		LOG.info("Load open311 service " + serviceList.size());
		assertTrue( "Failed to load a list of open311 service.", serviceList!=null && serviceList.isEmpty()==false);
	}

	@Test
	public void testGetServiceJson() {
		Open311DaoImpl dao = (Open311DaoImpl) getApplicationContext().getBean("open311Dao");
		open311ServiceImpl = new Open311ServiceImpl();
		open311ServiceImpl.setDao(dao);
		String data = open311ServiceImpl.getServiceJson("4e090d24992b941e78000019");
		LOG.info("Service JSON data: " + data);
		assertTrue("Failed to load service JSON data ", data!=null && data.isEmpty()==false);
	}

}
