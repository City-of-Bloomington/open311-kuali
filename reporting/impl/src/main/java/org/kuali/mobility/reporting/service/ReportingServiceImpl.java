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

package org.kuali.mobility.reporting.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.kuali.mobility.reporting.dao.ReportingDao;
import org.kuali.mobility.reporting.entity.Reporting;
import org.kuali.mobility.reporting.entity.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Service
public class ReportingServiceImpl implements ReportingService {
  
    @Autowired
    private ReportingDao reportingDao;

//    @Transactional
//    public void deleteEmergencyInfoById(Long id) {
//        emergencyInfoDao.deleteEmergencyInfoById(id);
//    }
//
//    @Transactional
//    public List<Reporting> findAllEmergencyInfo() {
//        return emergencyInfoDao.findAllEmergencyInfo();
//    }
//
//    @Transactional
//    public Reporting findEmergencyInfoById(Long id) {
//        return emergencyInfoDao.findEmergencyInfoById(id);
//    }
//
//    @Transactional
//    public Long saveEmergencyInfo(Reporting emergencyInfo) {
//        return emergencyInfoDao.saveEmergencyInfo(emergencyInfo);
//    }
//    
//    @Transactional
//    public void reorder(Long id, boolean up) {
//        emergencyInfoDao.reorder(id, up); 
//    }
//
//    @Transactional
//    public ReportingDao getEmergencyInfoDao() {
//        return emergencyInfoDao;
//    }
//
//    @Transactional
//    public List<Reporting> findAllEmergencyInfoByCampus(String campus) {
//        return emergencyInfoDao.findAllEmergencyInfoByCampus(campus);
//    }

    public Reporting fromJsonToEntity(String json) {
        return new JSONDeserializer<Reporting>().use(null, Reporting.class).deserialize(json);
    }

    public String toJson(Collection<Reporting> collection) {
        return new JSONSerializer().exclude("*.class").serialize(collection);
    }

    public Collection<Reporting> fromJsonToCollection(String json) {
        return new JSONDeserializer<List<Reporting>>().use(null, ArrayList.class).use("values", Reporting.class).deserialize(json);
    }

	@Override
	@Transactional
	public Long saveSubmission(Submission submission) {		
		return reportingDao.saveSubmission(submission);
	} 
    
}
