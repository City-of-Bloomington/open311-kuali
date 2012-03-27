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

import java.util.List;

import org.kuali.mobility.news.dao.NewsCache;
import org.kuali.mobility.news.dao.NewsDao;
import org.kuali.mobility.news.entity.NewsArticle;
import org.kuali.mobility.news.entity.NewsFeed;
import org.kuali.mobility.news.entity.NewsSource;

/**
 * An interface for a contract for interacting with the news entity objects.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
public interface NewsService {

	public List<NewsSource> getNewsSources( Long parentId, Boolean isActive);
	
	/**
	 * @return a list of all NewsSource objects sorted on NewsSource.order from least to greatest
	 */
	public List<NewsSource> getAllNewsSources();
	
	/**
	 * @return a list of all active NewsSource objects sorted on NewsSource.order from least to greatest
	 */
	public List<NewsSource> getAllActiveNewsSources();
	public List<NewsSource> getAllActiveNewsSources( Long parentId );
	
	/**
	 * Delete a NewsSource object
	 * 
	 * @param id the id of the NewsSource to delete
	 * @return the deleted NewsSource
	 */
	public NewsSource deleteNewsSourcebyId(long id);
	
	/**
	 * Save a NewsSource object
	 * 
	 * @param newsSource the NewsSource to save
	 * @return the saved NewsSource
	 */
	public NewsSource saveNewsSource(NewsSource newsSource);
	
	/**
	 * Retrieve a NewsSource object
	 * 
	 * @param id the id of the NewsSource to retrieve
	 * @return the found NewsSource if it exists, null otherwise
	 */
	public NewsSource getNewsSourceById(Long id);
	
	/**
	 * Make a NewsSource appear earlier in the lists returned by getAllNewsSources() and getAllActiveNewsSources().
	 * The NewsSource must move up in the list unless it is already the first item.  This change should be persisted.
	 * 
	 * @param id the id of the NewsSource to move
	 */
	public void moveNewsSourceUp(long id);
	
	/**
	 * Make a NewsSource appear later in the lists returned by getAllNewsSources() and getAllActiveNewsSources().
	 * The NewsSource must move down in the list unless it is already the last item.  This change should be persisted.
	 * 
	 * @param id the id of the NewsSource to move
	 */
	public void moveNewsSourceDown(long id);
	
	
	/**
	 * @param newsSourceId the id of the NewsSource that defines this feed
	 * @return a NewsFeed populated with data from the URL stored in the NewsSource, or null if the feed does not exists or there are errors parsing.
	 */
	public NewsFeed getNewsFeed(long newsSourceId);
	
	/**
	 * @return a list of NewsFeed objects for each of the active NewsSource objects, sorted on their display order.
	 */
	public List<NewsFeed> getAllActiveNewsFeeds();
	
	public List<NewsFeed> getNewsFeeds( Long parentId, Boolean isActive );

	/**
	 * Retrieve the details of an article
	 * 
	 * @param articleId the id of the article to retrieve
	 * @param sourceId the id of the NewsSource to which the article belongs.
	 * @return a NewsArticle object
	 */
	public NewsArticle getNewsArticle(String articleId, long sourceId);
	
	public void setDao( NewsDao dao );
	public NewsDao getDao();
	public void setCache( NewsCache cache );
	public NewsCache getCache();
}
