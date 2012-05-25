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
import org.kuali.mobility.open311.entity.Submission;
import org.kuali.mobility.open311.service.Open311Service;
//import org.kuali.mobility.open311.entity.File;
import org.kuali.mobility.file.entity.File;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class Open311ServiceImpl implements Open311Service {
  
    @Autowired
    private Open311Dao open311Dao;

	@Override
	@Transactional
    public Submission findSubmissionById(Long id) {
		return open311Dao.findSubmissionById(id);    	
    }
    
	@Override
	@Transactional
    public List<Submission> findAllSubmissions() {
		return open311Dao.findAllSubmissions();
    }
    
	@Override
	@Transactional
	public Long saveSubmission(Submission submission) {		
		return open311Dao.saveSubmission(submission);
	} 
    
	@Override
	@Transactional
    public Long saveAttachment(File file) {
		return open311Dao.saveAttachment(file);
    }
    
	@Override
	@Transactional
    public List<Submission> findAllSubmissionsByParentId(Long id) {
		return open311Dao.findAllSubmissionsByParentId(id);
	}

}
