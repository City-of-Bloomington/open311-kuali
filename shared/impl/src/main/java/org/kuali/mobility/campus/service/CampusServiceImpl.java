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
package org.kuali.mobility.campus.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.kuali.mobility.campus.entity.Campus;
import org.springframework.stereotype.Service;

/**
 * A service for doing the actual work of interacting with Campus objects.
 * 
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
@Service
public class CampusServiceImpl implements CampusService {

	private List<Campus> campuses;

	public List<Campus> findCampusesByTool(String toolName) {
		List<Campus> toolCampuses = new ArrayList<Campus>();

		for (Iterator<Campus> iterator = campuses.iterator(); iterator.hasNext();) {
			Campus campus = iterator.next();
			if (campus.getTools().contains(toolName)) {
				toolCampuses.add(campus);
			}
		}
		return toolCampuses;
	}

	public boolean needToSelectDifferentCampusForTool(String tool, String campus) {
		List<Campus> campuses = findCampusesByTool(tool);
		boolean needDifferentCampus = true;
		if (campuses != null && !campuses.isEmpty()) {
			for (Campus foundCampus : campuses) {
				if (foundCampus.getCode().equals(campus)) {
					needDifferentCampus = false;
				}
			}
		}
		return needDifferentCampus;
	}

	public void setCampuses(List<Campus> campuses) {
		this.campuses = campuses;
	}

}
