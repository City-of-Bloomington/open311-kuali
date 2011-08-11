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
import org.kuali.mobility.admin.entity.Tool;
import org.springframework.stereotype.Repository;

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
    
    @SuppressWarnings("unchecked")
	public List<Tool> getAllTools(){
    	Query query = entityManager.createQuery("select t from Tool t");
        try { 
        	return query.getResultList();
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
} 
