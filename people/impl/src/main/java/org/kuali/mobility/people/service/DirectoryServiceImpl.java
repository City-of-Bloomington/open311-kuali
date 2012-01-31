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

package org.kuali.mobility.people.service;

import java.util.List;

import org.kuali.mobility.people.dao.DirectoryDao;
import org.kuali.mobility.people.entity.DirectoryEntry;
import org.kuali.mobility.people.entity.Group;
import org.kuali.mobility.people.entity.Person;
import org.kuali.mobility.people.entity.SearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectoryServiceImpl implements DirectoryService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DirectoryServiceImpl.class);

	@Autowired
	private DirectoryDao directoryDao;
	
	public Person lookupPerson(String personId) {
		return directoryDao.lookupPerson(personId);
	}

	public Group lookupGroup(String groupId) {
		return directoryDao.lookupGroup(groupId);
	}

	public List<DirectoryEntry> findEntries(SearchCriteria search) {
		return directoryDao.findEntries(search);
	}
	
}
