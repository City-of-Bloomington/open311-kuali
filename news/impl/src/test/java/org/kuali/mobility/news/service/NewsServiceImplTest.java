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

package org.kuali.mobility.news.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.kuali.mobility.news.entity.NewsArticle;
import org.kuali.mobility.news.entity.NewsFeed;
import org.kuali.mobility.news.entity.NewsSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class NewsServiceImplTest {

	private static final Logger LOG = Logger.getLogger( NewsServiceImplTest.class );
	
	private static ApplicationContext applicationContext;
	
    @BeforeClass
    public static void createApplicationContext() {
    	NewsServiceImplTest.setApplicationContext(new FileSystemXmlApplicationContext(getConfigLocations()));
    }

    private static String[] getConfigLocations() {
        return new String[] { "classpath:/SpringBeans.xml" };
    }
    
	@Test
	public void testGetAllNewsSources() {
		NewsService service = (NewsService)getApplicationContext().getBean("newsService");
		assertTrue( "Service did not instantiate properly.", service != null );
		assertTrue( "Service does not have a dao reference.", service.getDao() != null );
		assertTrue( "Service does not have a cache reference.", service.getCache() != null );
		List<NewsSource> sources = service.getAllNewsSources();
		assertTrue( "Failed to find news sources.", sources != null && sources.size() > 0 );
	}

	@Test
	public void testGetAllActiveNewsSources() {
		NewsService service = (NewsService)getApplicationContext().getBean("newsService");
		List<NewsSource> sources = service.getAllNewsSources();
		assertTrue( "Failed to find news sources.", sources != null && sources.size() > 0 );
	}

	@Test
	public void testGetNewsSourceById() {
		NewsService service = (NewsService)getApplicationContext().getBean("newsService");
		NewsSource source = service.getNewsSourceById(new Long(2) );
		assertTrue( "Failed to find news source.", source != null && "BBC - News".equalsIgnoreCase( source.getName() ) );
	}

	@Test
	public void testGetAllActiveNewsFeeds() {
		NewsService service = (NewsService)getApplicationContext().getBean("newsService");
		List<NewsFeed> feeds = service.getAllActiveNewsFeeds();
		assertTrue( "Failed to find news feeds.", feeds != null && feeds.size() > 0 );
	}

	@Test
	public void testGetNewsFeeds() {
		NewsService service = (NewsService)getApplicationContext().getBean("newsService");
		List<NewsFeed> feeds = service.getNewsFeeds( new Long(1), new Boolean( true ) );
		assertTrue( "Failed to find child news feed for source 1.", feeds != null && feeds.size() > 0 );
	}

	@Test
	public void testGetNewsFeed() {
		NewsService service = (NewsService)getApplicationContext().getBean("newsService");
		NewsFeed feed = service.getNewsFeed( new Long(2) );
		assertTrue( "Failed to find news feed for source 2.", feed != null && feed.getArticles() != null && feed.getArticles().size() > 0 );
	}

	@Test
	public void testGetNewsArticle() {
		NewsService service = (NewsService)getApplicationContext().getBean("newsService");
		NewsFeed feed = service.getNewsFeed( new Long(2) );
		assertTrue( "Failed to find news feed for source 2.", feed != null && feed.getArticles() != null && feed.getArticles().size() > 0 );
		NewsArticle articleFirst = feed.getArticles().get(0);
		assertTrue( "No articles found for feed in source 2.", articleFirst != null );
		LOG.debug( "Article ID: "+articleFirst.getArticleId() );
		LOG.debug( "Article Title: "+articleFirst.getTitle() );
		LOG.debug( "Article's Source ID: "+articleFirst.getSourceId() );
		NewsArticle articleSecond = service.getNewsArticle(articleFirst.getArticleId(), articleFirst.getSourceId() );
		assertTrue( "Failed to lookup article by ID.", articleSecond != null);
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static void setApplicationContext(ApplicationContext applicationContext) {
		NewsServiceImplTest.applicationContext = applicationContext;
	}

}
