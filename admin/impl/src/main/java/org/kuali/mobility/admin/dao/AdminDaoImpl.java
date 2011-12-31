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

package org.kuali.mobility.admin.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.HomeTool;
import org.kuali.mobility.admin.entity.Tool;
import org.springframework.stereotype.Repository;

/**
 * The DAO for actually performing administrative tasks on the data store.
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 *
 */
@Repository
public class AdminDaoImpl implements AdminDao {

    @PersistenceContext
    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @SuppressWarnings("unchecked")
	public List<HomeScreen> getAllHomeScreens(){
    	Query query = entityManager.createQuery("select h from HomeScreen h");
        try { 
        	return query.getResultList();
        } catch (Exception e) {        	
        	return null;
        }
    }
    
    @Override
	public HomeScreen getHomeScreenById(long homeScreenId) {
		Query query = entityManager.createQuery("select h from HomeScreen h where h.homeScreenId = :id");
        query.setParameter("id", homeScreenId);
        try {
        	return (HomeScreen) query.getSingleResult();
        } catch (Exception e) {
        	return null;
        }
	}
    
    @Override
	public HomeScreen getHomeScreenByAlias(String alias) {
		Query query = entityManager.createQuery("select h from HomeScreen h where h.alias = :alias");
        query.setParameter("alias", alias);
        try {
        	return (HomeScreen) query.getSingleResult();
        } catch (Exception e) {
        	return null;
        }
	}

	@Override
	public Long saveHomeScreen(HomeScreen homeScreen) {
		if (homeScreen == null) {
            return null;
        }
        if (homeScreen.getAlias() != null) {
        	homeScreen.setAlias(homeScreen.getAlias().trim());
        }
        for (HomeTool ht : homeScreen.getHomeTools()) {
        	ht.setHomeScreen(homeScreen);
        }
        try {
	        if (homeScreen.getHomeScreenId() == null) {
	            entityManager.persist(homeScreen);
	        } else {
	        	deleteHomeToolsByHomeScreenId(homeScreen.getHomeScreenId());
	            entityManager.merge(homeScreen);
	        }
        } catch (OptimisticLockException oe) {
            return null;
        }
        return homeScreen.getHomeScreenId();
	}
	
	/**
	 * Deletes all the HomeTool objects associated with the given HomeScreen.  This effectively removes all Tool associations from a HomeScreen.
	 * @param homeScreenId
	 */
	private void deleteHomeToolsByHomeScreenId(long homeScreenId) {
		Query query = entityManager.createQuery("delete from HomeTool ht where ht.homeScreenId = :id");
        query.setParameter("id", homeScreenId);
        query.executeUpdate();
	}

	@Override
	public void deleteHomeScreenById(long homeScreenId) {
		Query query = entityManager.createQuery("delete from HomeScreen h where h.homeScreenId = :id");
        query.setParameter("id", homeScreenId);
        query.executeUpdate();
	}
    
    @SuppressWarnings("unchecked")
	public List<Tool> getAllTools(){
    	Query query = entityManager.createQuery("select t from Tool t");
        try { 
        	return query.getResultList();
        } catch (Exception e) {        	
        	return null;
        }
    }
    
    public Tool getToolById(long toolId) {
    	Query query = entityManager.createQuery("select t from Tool t where t.toolId = :id");
        query.setParameter("id", toolId);
        try {
        	return (Tool) query.getSingleResult();
        } catch (Exception e) {
        	return null;
        }
    }
    
    public Long saveTool(Tool tool) {
        if (tool == null) {
            return null;
        }
        if (tool.getTitle() != null) {
        	tool.setTitle(tool.getTitle().trim());
        }
        if (tool.getUrl() != null) {
        	tool.setUrl(tool.getUrl().trim());
        }
        if (tool.getIconUrl() != null) {
        	tool.setIconUrl(tool.getIconUrl().trim());
        }
        if (tool.getDescription() != null) {
        	tool.setDescription(tool.getDescription().trim());
        }
        if (tool.getContacts() != null) {
        	tool.setContacts(tool.getContacts().trim());
        }
        if (tool.getProgrammingLanguage() != null) {
        	tool.setProgrammingLanguage(tool.getProgrammingLanguage().trim());
        }
        if (tool.getKeywords() != null) {
        	tool.setKeywords(tool.getKeywords().trim());
        }
        if (tool.getLicence() != null) {
        	tool.setLicence(tool.getLicence().trim());
        }
        try {
	        if (tool.getToolId() == null) {
	            entityManager.persist(tool);
	        } else {
	            entityManager.merge(tool);
	        }
        } catch (OptimisticLockException oe) {
            return null;
        }
        return tool.getToolId();
    }
    
    public void deleteToolById(Long toolId) {
        Query query = entityManager.createQuery("delete from Tool t where t.toolId = :toolId");
        query.setParameter("toolId", toolId);
        query.executeUpdate();
    }
} 
