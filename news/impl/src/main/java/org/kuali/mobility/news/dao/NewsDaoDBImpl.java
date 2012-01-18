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

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.log4j.Logger;
import org.kuali.mobility.news.entity.NewsSource;
import org.kuali.mobility.util.mapper.DataMapperImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * DAO for actually persisting and retrieving NewsSource objects
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 * @see org.kuali.mobility.news.dao.NewsDao
 */
@Repository
public class NewsDaoDBImpl implements NewsDao {
	
	public static Logger LOG = Logger.getLogger( NewsDaoDBImpl.class );

	private ApplicationContext applicationContext;
	private NewsCache cache;

	@PersistenceContext
    private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<NewsSource> findAllActiveNewsSources() {
		Query query = entityManager.createQuery("select s from NewsSource s where s.active = :active order by s.order");
		query.setParameter("active", true);
        try { 
        	return query.getResultList();
        } catch (Exception e) {        	
        	return new ArrayList<NewsSource>();
        }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<NewsSource> findAllNewsSources() {
		Query query = entityManager.createQuery("select s from NewsSource s order by s.order");
        try { 
        	return query.getResultList();
        } catch (Exception e) {        	
        	return new ArrayList<NewsSource>();
        }
	}

	@Override
	@Transactional
	public NewsSource lookup(Long id) {
		Query query = entityManager.createQuery("select s from NewsSource s where s.id = :id");
        query.setParameter("id", id);
        try {
        	NewsSource s = (NewsSource)query.getSingleResult();
        	return s;
        } catch (Exception e) {
        	return null;
        }
	}
	
	@Override
	@Transactional
	public NewsSource save(NewsSource newsSource) {
		if (newsSource == null) {
            return null;
        }
        try {
	        if (newsSource.getId() == null) {
	            entityManager.persist(newsSource);
	        } else {
	            entityManager.merge(newsSource);
	        }
        } catch (OptimisticLockException oe) {
            return null;
        }
        return newsSource;
	}

	@Override
	@Transactional
	public NewsSource delete(NewsSource newsSource) {
		Query query = entityManager.createQuery("delete from NewsSource ns where ns.id = :id");
        query.setParameter("id", newsSource.getId());
        query.executeUpdate();
        return newsSource;
	}

	public ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public void setApplicationContext(ApplicationContext applicationContext) {
		this.applicationContext = applicationContext;
	}

	public NewsCache getCache() {
		return cache;
	}

	public void setCache(NewsCache cache) {
		this.cache = cache;
	}

}
