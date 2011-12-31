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

import java.util.List;

import org.kuali.mobility.campus.entity.Campus;

/**
 * Interface for a contract for interacting with Campus objects
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
public interface CampusService {
    
	/**
	 * @return a list of all available Campus objects
	 */
    public List<Campus> findCampusesByTool(String toolName);
    
	/**
	 * @return a boolean if this tool needs a different campus selected.  If a tool has no campuses, true is always returned.
	 */
    public boolean needToSelectDifferentCampusForTool(String tool, String campus);
}
