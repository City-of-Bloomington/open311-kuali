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

package org.kuali.mobility.reporting.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.mobility.reporting.entity.Submission;
import org.springframework.stereotype.Repository;

@Repository
public class ReportingDaoImpl implements ReportingDao {

    @PersistenceContext
    private EntityManager entityManager;
    
    @SuppressWarnings("unchecked")
	public List<Submission> findAllSubmissions() {
        try {
	        Query query = entityManager.createQuery("select s from Submission s where s.archivedDate is null");
	        return query.getResultList();
	    } catch (NoResultException e) {
	        return null;
	    }    	
    }

    @SuppressWarnings("unchecked")
    public List<Submission> findAllSubmissionsByParentId(Long id) {
        try {
	        Query query = entityManager.createQuery("select s from Submission s where s.parentId = :id");
	        query.setParameter("id", id);
	        return query.getResultList();
	    } catch (NoResultException e) {
	        return null;
	    }    	
    } 

    public Submission findSubmissionById(Long id) {
        try {
	  	    Query query = entityManager.createQuery("select s from Submission s where s.id = :id");  	    
	  	    query.setParameter("id", id);
	  	    return (Submission) query.getSingleResult();
	    } catch (Exception e) {
  	        return null;
  	    }
    }
    
    public Long saveSubmission(Submission submission) {
      if (submission == null) {
    	  return null;
	  }	  
      try {
	      if (submission.getId() == null) {
	          entityManager.persist(submission);
	      } else {
	          entityManager.merge(submission);
	      }
	  } catch (OptimisticLockException oe) {
	      return null;
	  }
	  return submission.getId();
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
