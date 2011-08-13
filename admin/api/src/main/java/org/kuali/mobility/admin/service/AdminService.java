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

public interface AdminService {
	public HomeScreen getCachedHomeScreenByName(String name);

	public List<HomeScreen> getAllHomeScreens();
	public HomeScreen getHomeScreenById(long layoutId);
	public HomeScreen getHomeScreenByName(String name);
	public Long saveHomeScreen(HomeScreen homeScreen);
	public void deleteHomeScreenById(long layoutId);
	
	public List<Tool> getAllTools();
	public Long saveTool(Tool tool);
	public Tool getToolById(long toolId);
	public void deleteToolById(long toolId);
	
	public void stopCache();
	public void startCache();
}
