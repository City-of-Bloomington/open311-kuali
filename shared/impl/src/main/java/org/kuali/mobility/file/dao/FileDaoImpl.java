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

package org.kuali.mobility.file.dao;

import java.io.IOException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.mobility.file.entity.File;
import org.springframework.stereotype.Repository;

@Repository
public class FileDaoImpl implements FileDao {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(FileDaoImpl.class);		
	private String ME = this.getClass().getName();
	
	@PersistenceContext
    private EntityManager entityManager;
	
    public FileDaoImpl(){}
    
    
	public Long saveFile(File file){

		if(file == null){
    		return null;
    	}
		
    	try {
			if(file.getId() == null){
				entityManager.persist(file);
			}else{
				entityManager.merge(file);
			}
		} catch (OptimisticLockException e) {
			return null;
		}
    	return file.getId();
	}
	
    @SuppressWarnings("unchecked")
	public File findFileById(Long Id){
        Query query = entityManager.createQuery("select f from File f where f.id = " + Id);
        return (File) query.getSingleResult();
	}
	
    @SuppressWarnings("unchecked")
	public List<File> findFilesByName(String name){
        Query query = entityManager.createQuery("select f from File f where f.fileName like '" + name + "'");
        return query.getResultList();
	}

    @SuppressWarnings("unchecked")
	public List<File> findAllFiles(){
        Query query = entityManager.createQuery("select f from File f");
        return query.getResultList();
	}
    
    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }	
}