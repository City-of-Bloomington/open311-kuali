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

import java.util.List;

import org.kuali.mobility.reporting.dao.ReportingDao;
//import org.kuali.mobility.reporting.entity.File;
import org.kuali.mobility.file.entity.File;
import org.kuali.mobility.reporting.entity.Submission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReportingServiceImpl implements ReportingService {
  
    @Autowired
    private ReportingDao reportingDao;

	@Override
	@Transactional
    public Submission findSubmissionById(Long id) {
		return reportingDao.findSubmissionById(id);    	
    }
    
	@Override
	@Transactional
    public List<Submission> findAllSubmissions() {
		return reportingDao.findAllSubmissions();
    }
    
	@Override
	@Transactional
	public Long saveSubmission(Submission submission) {		
		return reportingDao.saveSubmission(submission);
	} 
    
	@Override
	@Transactional
    public Long saveAttachment(File file) {
		return reportingDao.saveAttachment(file);
    }
    
	@Override
	@Transactional
    public List<Submission> findAllSubmissionsByParentId(Long id) {
		return reportingDao.findAllSubmissionsByParentId(id);
	}

}
