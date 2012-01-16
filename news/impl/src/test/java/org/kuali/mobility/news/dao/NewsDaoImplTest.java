package org.kuali.mobility.news.dao;

import static org.junit.Assert.*;
import static org.junit.Assert.fail;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.mobility.news.entity.NewsSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class NewsDaoImplTest {

	private static final Logger LOG = Logger.getLogger( NewsDaoImplTest.class );
	
	private static ApplicationContext applicationContext;
	
    @BeforeClass
    public static void createApplicationContext() {
    	NewsDaoImplTest.setApplicationContext(new FileSystemXmlApplicationContext(getConfigLocations()));
    }

    private static String[] getConfigLocations() {
        return new String[] { "classpath:/SpringBeans.xml" };
    }
    
	@Test
	public void testFindAllActiveNewsSources() {
		NewsDao dao = (NewsDao)getApplicationContext().getBean("newsDao");
		List<NewsSource> sources = dao.findAllActiveNewsSources();
		assertTrue( "Failed to find news sources.", sources != null && sources.size() > 0 );
	}

	@Test
	public void testFindAllNewsSources() {
		NewsDao dao = (NewsDao)getApplicationContext().getBean("newsDao");
		List<NewsSource> sources = dao.findAllNewsSources();
		assertTrue( "Failed to find news sources.", sources != null && sources.size() > 0 );
	}

	@Test
	public void testLookup() {
		NewsDao dao = (NewsDao)getApplicationContext().getBean("newsDao");
		NewsSource source = dao.lookup( new Long( 2 ) );
		assertTrue( "Failed to find news source.", source != null && "CNN.com - Health".equalsIgnoreCase( source.getName() ) );
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		NewsDaoImplTest.applicationContext = applicationContext;
	}

}
