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

package org.kuali.mobility.open311.dao;

import java.util.List;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;	

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.mobility.file.entity.File;

//import org.kuali.mobility.open311.entity.File;
import org.kuali.mobility.open311.dao.Open311Dao;
import org.kuali.mobility.open311.entity.ServiceEntity;
import org.kuali.mobility.open311.entity.Submission;
import org.springframework.stereotype.Repository;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import org.kuali.mobility.util.mapper.DataMapper;

@Repository
public class Open311DaoImpl implements Open311Dao {

	private static final Logger LOG = Logger.getLogger( Open311DaoImpl.class );
	private static final String DEFAULT_CHARACTER_SET = "UTF-8"; //ISO-8859-1
	
	@PersistenceContext
	private EntityManager entityManager;

	private DataMapper mapper;
	private List<ServiceEntity> serviceList;
	
	private String serviceSourceFile;
	private String serviceSourceUrl;
	private String serviceMappingFile;
	private String serviceMappingUrl;
	
	private String serviceJsonURI;  //"http://akekee.dsc.umich.edu:8180/dining/getMeals?_type=json"
	
	public String getServiceSourceFile() {
		return serviceSourceFile;
	}

	public void setServiceSourceFile(String serviceSourceFile) {
		this.serviceSourceFile = serviceSourceFile;
	}

	public String getServiceSourceUrl() {
		return serviceSourceUrl;
	}

	public void setServiceSourceUrl(String serviceSourceUrl) {
		this.serviceSourceUrl = serviceSourceUrl;
	}

	public String getServiceMappingFile() {
		return serviceMappingFile;
	}

	public void setServiceMappingFile(String serviceMappingFile) {
		this.serviceMappingFile = serviceMappingFile;
	}

	public String getServiceMappingUrl() {
		return serviceMappingUrl;
	}

	public void setServiceMappingUrl(String serviceMappingUrl) {
		this.serviceMappingUrl = serviceMappingUrl;
	}

	public List<ServiceEntity> getServiceList() {
		if (serviceList==null || serviceList.isEmpty()) {
			initData();
		}
		return serviceList;
	}

	public void setMapper(DataMapper mapper) {
		this.mapper = mapper;
	}

	private void initData() {
		if (serviceList==null)
			serviceList = new ArrayList<ServiceEntity>();
		
		boolean isServiceSourceUrlAvailable = (getServiceSourceUrl() != null ? true : false) ;
		boolean isServiceMappingUrlAvailable = (getServiceMappingUrl() != null ? true : false) ;
		
		try {
			if(isServiceSourceUrlAvailable) {
				if (isServiceMappingUrlAvailable) {
					serviceList = mapper.mapData(serviceList, new URL(getServiceSourceUrl()), new URL(getServiceMappingUrl()));
				}
				else {
					serviceList = mapper.mapData(serviceList, new URL(getServiceSourceUrl()), getServiceMappingFile());
					
				}
			}
			else {
				if (isServiceMappingUrlAvailable) {
					LOG.error("DataMapper does NOT support this case, sourceFile with mappingUrl!");
					return;
				}
				else {
					serviceList = mapper.mapData(serviceList, getServiceSourceFile(), getServiceMappingFile());
				}	
			}
		
		} catch (MalformedURLException e) {
			LOG.error(e.getMessage());
		} catch (ClassNotFoundException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}
	}
	
	public String getServiceJsonURI() {
		return serviceJsonURI;
	}

	public void setServiceJsonURI(String serviceJsonURI) {
		this.serviceJsonURI = serviceJsonURI;
	}

	public String getServiceJson(final String serviceCode){
		String jsonData = null;
		String queryString = null;
		try {
			queryString = (serviceCode==null? URLEncoder.encode(serviceCode,"ISO-8859-1") : (URLEncoder.encode("4e090d24992b941e78000027","ISO-8859-1"))).concat(".json");
		} catch (UnsupportedEncodingException e1) {
			LOG.error(e1.getMessage());
		}
		LOG.debug("RUL = " + getServiceJsonURI() + queryString);
		try {			
			URLConnection connection = new URL(getServiceJsonURI() + queryString).openConnection();
			jsonData = IOUtils.toString( connection.getInputStream(), DEFAULT_CHARACTER_SET );			
		} catch (MalformedURLException e) {
			LOG.error(e.getMessage());
		} catch (IOException e) {
			LOG.error(e.getMessage());
		}

		return jsonData; 
	}

	
	
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
			Query query = entityManager.createQuery("select s from Submission s where s.parentId = :id or s.id = :id order by s.revisionNumber desc");
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

	public Long saveAttachment(File file) {
		if (file == null) {
			return null;
		}
		try {
			if (file.getId() == null) {
				entityManager.persist(file);
			} else {
				entityManager.merge(file);
			}
		} catch (OptimisticLockException oe) {
			return null;
		}
		return file.getId();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

}
