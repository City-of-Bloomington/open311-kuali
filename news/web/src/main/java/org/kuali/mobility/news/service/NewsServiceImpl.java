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

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import org.kuali.mobility.news.dao.NewsCache;
import org.kuali.mobility.news.dao.NewsDao;
import org.kuali.mobility.news.entity.NewsArticle;
import org.kuali.mobility.news.entity.NewsSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Service for actually doing the work of interacting with the nedws entity objects
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 * @see org.kuali.mobility.news.service.NewsService
 */
public class NewsServiceImpl implements NewsService, ApplicationContextAware {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(NewsServiceImpl.class);
	
	private ApplicationContext applicationContext;
	private NewsDao dao;
	private NewsCache cache;
	
	@Override
	@GET
	@Path("/sources/getAll")
	public List<NewsSource> getAllNewsSources() {
		LOG.debug( "Called getAllNewsSources web service." );
		return (List<NewsSource>)getDao().findAllNewsSources();
	}
	
	@Override
	@GET
	@Path("/sources/getAllActive")
	public List<NewsSource> getAllActiveNewsSources() {
		LOG.debug( "Called getAllActiveNewsSources web service." );
		return getDao().findAllActiveNewsSources();
	}

	@Override
	@GET
	@Path("/sources/getAllActive/{parentId}")
	public List<NewsSource> getAllActiveNewsSources( @PathParam( "parentId" ) final Long parentId ) {
		LOG.debug( "Called getAllActiveNewsSources web service." );
		return getDao().findAllActiveNewsSources(parentId);
	}
	
	@Override
	@GET
	@Path("/sources/getChildNewsSource/{parentId}")
	public List<NewsSource> getNewsSources( @PathParam("parentId") final Long parentId, @QueryParam("active") final Boolean isActive) {
		return getDao().findNewsSources(parentId, isActive);
	}

	@Override
	@GET
	@Path("/sources/getNewsSource/{sourceId}")
	public NewsSource getNewsSourceById( @PathParam("sourceId") final Long id) {
		LOG.debug( "Called getNewsSourceById web service." );
		return getDao().lookup(id);
	}
		
//	private List<NewsFeed> getAllActiveNewsFeeds() {
//		LOG.debug( "Entering getAllActiveNewsFeeds()." );
//		List<NewsFeed> feeds = new ArrayList<NewsFeed>(getCache().getNewsFeeds().values());
//		if( feeds == null || feeds.isEmpty() )
//		{
//			LOG.debug( "No feeds found, refreshing cache." );
//			for( NewsSource source : getDao().findAllNewsSources() )
//			{
//				LOG.debug( "Updating cache for "+source.getName() );
//				getCache().updateCache( source );
//			}
//		}
////		Collections.sort(feeds);
//		feeds = new ArrayList<NewsFeed>(getCache().getNewsFeeds().values());
//		return feeds;
//	}

//	public List<NewsFeed> getNewsFeeds( final Long parentId, final Boolean isActive ) {
//		List<NewsFeed> feeds = new ArrayList<NewsFeed>();
//		
//		for( NewsSource s : getNewsSources( parentId, isActive ) ) {
//			feeds.add( getNewsFeed( s.getId().longValue() ) );
//		}
//		
//		return feeds;
//	}
//	

//	private NewsFeed getNewsFeed(long newsSourceId) {
//		NewsFeed feed = null;
//		Long sourceId = new Long( newsSourceId );
//		
//		if( getCache().getNewsFeeds() == null )
//		{
//			LOG.debug( "News feeds were not loaded.  Loading now...." );
//			getAllActiveNewsFeeds();
//		}
//		
//		feed = getCache().getNewsFeeds().get( sourceId );
//		
//		if( feed == null )
//		{
//			NewsSource source = getCache().getNewsSources().get( sourceId );
//			if( source == null )
//			{
//				LOG.debug( "News source is not found. Loading news sources now...." );
//				getDao().findAllNewsSources();
//				source = getCache().getNewsSources().get( sourceId );
//				if( source == null )
//				{
//					LOG.debug( "News source is not present in bootstrap data." );
//				}
//			}
//			
//			LOG.debug( "News feed for source "+sourceId+" was not found, loading now...." );
//			getCache().updateCache(source);
//			
//			feed = getCache().getNewsFeeds().get( sourceId );
//		}
//		
//		return feed;
//	}
	
	@Override
    @GET
    @Path("/source/{sourceId}/article/{articleId}")
	public NewsArticle getNewsArticle(@PathParam("articleId") final String articleId, @PathParam("sourceId") final long sourceId) {
		NewsArticle foundArticle = null;
		LOG.debug( "Looking for article id "+articleId );
		NewsSource source = getNewsSourceById(sourceId);
		for (NewsArticle article : source.getArticles()) {
			LOG.debug( "Comparing with: "+article.getArticleId() );
			String id;
			try {
				id = URLDecoder.decode(article.getArticleId(), "UTF-8");
//				if (articleId.equals(id)) {
//					return article.copy();
//				}
				if( articleId.equalsIgnoreCase( id ) || articleId.equalsIgnoreCase( article.getArticleId() ) )
				{
					foundArticle = article.copy();
					break;
				}
			} catch (UnsupportedEncodingException e) {
				LOG.error( e.getLocalizedMessage() );
			}
		}
		return foundArticle;
	}
	
	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public NewsDao getDao() {
		return dao;
	}

	public void setDao(NewsDao dao) {
		this.dao = dao;
	}

	public NewsCache getCache() {
		return cache;
	}

	public void setCache(NewsCache cache) {
		this.cache = cache;
	}

}
