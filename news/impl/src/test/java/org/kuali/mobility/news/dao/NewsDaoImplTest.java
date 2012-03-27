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

package org.kuali.mobility.news.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.mobility.news.entity.NewsSource;
import org.kuali.mobility.news.util.NewsSourcePredicate;
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
		assertTrue( "Failed to find news source.", source != null && "BBC - News".equalsIgnoreCase( source.getName() ) );
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		NewsDaoImplTest.applicationContext = applicationContext;
	}

}
