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
import java.util.ArrayList;
import java.util.List;

import org.kuali.mobility.news.dao.NewsCache;
import org.kuali.mobility.news.dao.NewsDao;
import org.kuali.mobility.news.entity.NewsArticle;
import org.kuali.mobility.news.entity.NewsFeed;
import org.kuali.mobility.news.entity.NewsSource;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for actually doing the work of interacting with the nedws entity objects
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 * @see org.kuali.mobility.news.service.NewsService
 */
@Service(value = "NewsService")
public class NewsServiceImpl implements NewsService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(NewsServiceImpl.class);
	
	private ApplicationContext applicationContext;
	private NewsDao dao;
	private NewsCache cache;
	
	@Override
	public List<NewsSource> getAllNewsSources() {
		LOG.debug( "Called getAllNewsSources web service." );
		return getDao().findAllNewsSources();
	}
	
	@Override
	public List<NewsSource> getAllActiveNewsSources() {
		LOG.debug( "Called getAllActiveNewsSources web service." );
		return getDao().findAllActiveNewsSources();
	}

	@Override
	//@Cacheable(value="newsSource", key="#id")
	public NewsSource getNewsSourceById(Long id) {
		LOG.debug( "Called getNewsSourceById web service." );
		return getDao().lookup(id);
	}
	
	@Override
	//@CacheEvict(value = "newsSource", key="#id", allEntries=false)
	public NewsSource deleteNewsSourcebyId(long id) {
		LOG.debug( "Called deleteNewsSourceById web service." );
		return getDao().delete(getDao().lookup(id));
	}

	@Override
	//@CacheEvict(value = "newsSource", key="#newsSource.id", allEntries=false)
	public NewsSource saveNewsSource(NewsSource newsSource) {
		LOG.debug( "Called saveNewsSourceById web service." );
		LOG.debug( "Dao is "+(null == getDao() ? "" : "not")+" null." );
		LOG.debug( "NewsSourceDBImpl is "+(null == newsSource ? "" : "not")+" null." );
		getDao().save(newsSource);
		LOG.debug( "After saving, sourceId = "+newsSource.getId() );
		getCache().updateCache(newsSource);
		return newsSource;
	}
	
	@Override
	@Transactional
	public void moveNewsSourceUp(long id) {
		List<NewsSource> sources = getAllNewsSources();
    	NewsSource sourceToMove = null;
    	NewsSource sourceToDisplace = null;
    	for (NewsSource source : sources) {
    		if (source.getId() == id) {
    			sourceToMove = source;
    		}
    	}
    	if (sourceToMove != null) {
    		if (sourceToMove.getOrder() > 0) {
    			int currentOrder = -1;
	    		for (NewsSource source : sources) {
	        		if (source.getOrder() > currentOrder  && source.getOrder() < sourceToMove.getOrder()) {
	        			currentOrder = source.getOrder();
	        			sourceToDisplace = source;
	        		}
	        	}
	    		if (sourceToDisplace != null) {
	    			int fromOrder = sourceToMove.getOrder();
		    		int toOrder = sourceToDisplace.getOrder();
		    		sourceToMove.setOrder(toOrder);
	    			sourceToDisplace.setOrder(fromOrder);
	    			saveNewsSource(sourceToMove);
	        		saveNewsSource(sourceToDisplace);
	    		}
    		}
    	}
	}

	@Override
	@Transactional
	public void moveNewsSourceDown(long id) {
		List<NewsSource> sources = getAllNewsSources();
    	NewsSource sourceToMove = null;
    	NewsSource sourceToDisplace = null;
    	for (NewsSource source : sources) {
    		if (source.getId() == id) {
    			sourceToMove = source;
    		}
    	}
    	if (sourceToMove != null) {
    		int currentOrder = -1;
    		for (NewsSource source : sources) {
        		if (source.getOrder() > sourceToMove.getOrder()) {
        			if (currentOrder < 0) {
        				currentOrder = source.getOrder();
            			sourceToDisplace = source;
        			} else {
        				if (source.getOrder() < currentOrder) {
        					currentOrder = source.getOrder();
                			sourceToDisplace = source;
        				}
        			}
        			
        		}
        	}
    		
    		if (sourceToDisplace != null) {
    			int fromOrder = sourceToMove.getOrder();
        		int toOrder = sourceToDisplace.getOrder();
        		sourceToMove.setOrder(toOrder);
    			sourceToDisplace.setOrder(fromOrder);
    			saveNewsSource(sourceToMove);
        		saveNewsSource(sourceToDisplace);
    		}
    	}
	}
	
	@Override
	//@CacheEvict(value = "newsFeed", allEntries=true)
	public List<NewsFeed> getAllActiveNewsFeeds() {
		LOG.debug( "Entering getAllActiveNewsFeeds()." );
		List<NewsFeed> feeds = new ArrayList<NewsFeed>(getCache().getNewsFeeds().values());
		if( feeds == null || feeds.isEmpty() )
		{
			LOG.debug( "No feeds found, refreshing cache." );
			for( NewsSource source : getDao().findAllNewsSources() )
			{
				LOG.debug( "Updating cache for "+source.getName() );
				getCache().updateCache( source );
			}
		}
//		Collections.sort(feeds);
		feeds = new ArrayList<NewsFeed>(getCache().getNewsFeeds().values());
		return feeds;
	}

	@Override
	//@CacheEvict(value = "newsFeed", key="#newsSourceId", allEntries=false)
	public NewsFeed getNewsFeed(long newsSourceId) {
		NewsFeed feed = null;
		Long sourceId = new Long( newsSourceId );
		
		if( getCache().getNewsFeeds() == null )
		{
			LOG.debug( "News feeds were not loaded.  Loading now...." );
			getAllActiveNewsFeeds();
		}
		
		feed = getCache().getNewsFeeds().get( sourceId );
		
		if( feed == null )
		{
			NewsSource source = getCache().getNewsSources().get( sourceId );
			if( source == null )
			{
				LOG.debug( "News source is not found. Loading news sources now...." );
				getDao().findAllNewsSources();
				source = getCache().getNewsSources().get( sourceId );
				if( source == null )
				{
					LOG.debug( "News source is not present in bootstrap data." );
				}
			}
			
			LOG.debug( "News feed for source "+sourceId+" was not found, loading now...." );
			getCache().updateCache(source);
			
			feed = getCache().getNewsFeeds().get( sourceId );
		}
		
		return feed;
	}
	
	@Override
	public NewsArticle getNewsArticle(String articleId, long sourceId) {
		NewsArticle foundArticle = null;
		LOG.debug( "Looking for article id "+articleId );
		NewsFeed feed = getNewsFeed(sourceId);
		for (NewsArticle article : feed.getArticles()) {
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

    /**
     * Iterates through all the active NewsSource objects and updates their corresponding NewsFeed objects.  
     * Any NewsSources that have been deactivated have their NewsFeed objects removed from the cache.
     */
    /*
    private void reloadCache() {
		List<NewsSource> newsSources = newsDao.findAllActiveNewsSources();
		Set<Long> sourceIdsToRemove = new HashSet<Long>(cachedSources.keySet());
		
		for (NewsSource source : newsSources) {
			sourceIdsToRemove.remove(source.getId());
			cachedSources.put(source.getId(), source);
		}
		
		//remove any deleted or inactivated feeds from the cache
		for (Long id : sourceIdsToRemove) {
			cachedFeeds.remove(id);
			cachedSources.remove(id);
		}
		
		Set<Long> cachedSourceIds = cachedSources.keySet();
		
		for (Long id : cachedSourceIds) {
			NewsSource source = cachedSources.get(id);
			NewsFeed feed = cachedFeeds.get(id);
			if (feed == null) {
				feed = new NewsFeed();
				cachedFeeds.put(id, feed);
			}
			updateFeed(feed, source);
		}
	}
	*/

}
