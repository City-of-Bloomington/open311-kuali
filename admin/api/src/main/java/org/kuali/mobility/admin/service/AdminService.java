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

package org.kuali.mobility.admin.service;

import java.util.List;

import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.Tool;

/**
 * Interface for a contract for administrative tasks
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
public interface AdminService {
	
	/**
	 * @return all defined home screens
	 */
	public List<HomeScreen> getAllHomeScreens();
	/**
	 * @param layoutId the id of the home screen to retrieve
	 * @return the home screen matching the id
	 */
	public HomeScreen getHomeScreenById(long layoutId);
	/**
	 * @param alias the alias of the home screen to retrieve
	 * @return the home screen matching the alias
	 */
	public HomeScreen getHomeScreenByAlias(String alias);
	/**
	 * @param homeScreen the HomeScreen to save
	 * @return the id of the saved HomeScreen
	 */
	public Long saveHomeScreen(HomeScreen homeScreen);
	/**
	 * @param layoutId the id of the HomeScreen to delete
	 */
	public void deleteHomeScreenById(long layoutId);
	
	/**
	 * @return all defined Tool objects
	 */
	public List<Tool> getAllTools();
	/**
	 * @param tool the Tool to save
	 * @return the id of the saved Tool
	 */
	public Long saveTool(Tool tool);
	/**
	 * @param toolId the id of the tool to retrieve
	 * @return the Tool matching the id
	 */
	public Tool getToolById(long toolId);
	/**
	 * @param toolId the id of the Tool to delete
	 */
	public void deleteToolById(long toolId);

}
