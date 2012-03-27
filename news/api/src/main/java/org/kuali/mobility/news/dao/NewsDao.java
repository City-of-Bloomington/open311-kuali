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

import java.util.List;

import org.kuali.mobility.news.entity.NewsSource;

/**
 * An interface for persisting and retrieving NewsSource objects from a data store.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
public interface NewsDao {
	
	public List<NewsSource> findNewsSources( Long parentId, Boolean isActive );
	
	/**
	 * @return a list of active NewsSource objects
	 */
	public List<NewsSource> findAllActiveNewsSources();
	public List<NewsSource> findAllActiveNewsSources( Long parentId );
	
	/**
	 * @return a list of all NewsSource objects
	 */
	public List<NewsSource> findAllNewsSources();
	public List<NewsSource> findAllNewsSources( Long parentId );
	
	/**
	 * @return a single NewsSource object matched by its id
	 */
	public NewsSource lookup(Long id);
	
	/**
	 * saves a NewsSource object to the data store
	 * 
	 * @return the saved NewsSource
	 */
	public NewsSource save(NewsSource newsSource);
	
	/**
	 * deletes a NewsSource object from the data store
	 * 
	 * @return the deleted NewsSource
	 */
	public NewsSource delete(NewsSource newsSource);
}
