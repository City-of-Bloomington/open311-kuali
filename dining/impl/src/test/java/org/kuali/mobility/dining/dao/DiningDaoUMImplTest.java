package org.kuali.mobility.dining.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.mobility.dining.entity.Place;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class DiningDaoUMImplTest {
	
private static final Logger LOG = Logger.getLogger( DiningDaoUMImplTest.class );
	
	private static ApplicationContext applicationContext;
	
    @BeforeClass
    public static void createApplicationContext() {
    	DiningDaoUMImplTest.setApplicationContext(new FileSystemXmlApplicationContext(getConfigLocations()));
    }

    private static String[] getConfigLocations() {
        return new String[] { "classpath:/SpringBeans.xml" };
    }
    
	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		DiningDaoUMImplTest.applicationContext = applicationContext;
	}
	
	@Test
	public void testGetPlaceList() {
		DiningDaoUMImpl dao = (DiningDaoUMImpl) getApplicationContext().getBean("diningDao");
		List<Place> placeList = dao.getPlaceList();
		LOG.info("Load dining place " + placeList.size());
		assertTrue( "Failed to load a list of dining place.", placeList!=null && placeList.isEmpty()==false);
	}

	@Test
	public void testGetMenusJson() {
		DiningDaoUMImpl dao = (DiningDaoUMImpl) getApplicationContext().getBean("diningDao");
		String data = dao.getMenusJson("Barbour Dining Hall", null);
		LOG.info("Menu JSON data: " + data);
		assertTrue("Failed to load menu JSON data ", data!=null && data.isEmpty()==false);
	}

}
