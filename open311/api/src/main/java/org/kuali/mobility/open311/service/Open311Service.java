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
 
package org.kuali.mobility.open311.service;

import java.util.List;

import org.kuali.mobility.open311.dao.Open311Dao;
//import org.kuali.mobility.open311.entity.File;
import org.kuali.mobility.file.entity.File;
import org.kuali.mobility.open311.entity.Submission;
import org.kuali.mobility.open311.entity.ServiceEntity;
import org.kuali.mobility.open311.entity.Attributes;
 
public interface Open311Service {

	public Submission findSubmissionById(Long id);
	public Long saveSubmission(Submission submission);
    public List<Submission> findAllSubmissions();
    public List<Submission> findAllSubmissionsByParentId(Long id);
	
	public Open311Dao getDao();
	public void setDao(Open311Dao dao);
	public List<ServiceEntity> getService();
	public Attributes getServiceAttributes(final String serviceCode);
	public String getServiceJson(final String serviceCode);
	
    public Long saveAttachment(File file);
    
}
