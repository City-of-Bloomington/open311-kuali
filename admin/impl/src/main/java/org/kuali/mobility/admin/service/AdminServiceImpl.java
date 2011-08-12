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

import org.kuali.mobility.admin.dao.AdminDao;
import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
    private AdminDao adminDao;
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	@Override
	public List<HomeScreen> getAllHomeScreens() {
		return adminDao.getAllHomeScreens();
	}
	
	@Override
	public HomeScreen getHomeScreenById(long homeScreenId) {
		return adminDao.getHomeScreenById(homeScreenId);
	}
	
	@Override
	public HomeScreen getHomeScreenByName(String name) {
		return adminDao.getHomeScreenByName(name);
	}
	
	@Override
	@Transactional
	public Long saveHomeScreen(HomeScreen homeScreen) {
		return adminDao.saveHomeScreen(homeScreen);
	}

	@Override
	@Transactional
	public void deleteHomeScreenById(long homeScreenId) {
		adminDao.deleteHomeScreenById(homeScreenId);
	}
	
	@Override
	public List<Tool> getAllTools() {
		return adminDao.getAllTools();
	}
	
	@Override
	public Tool getToolById(long toolId) {
		return adminDao.getToolById(toolId);
	}
	
	@Override
	@Transactional
	public Long saveTool(Tool tool) {
		return adminDao.saveTool(tool);
	}

	@Override
	@Transactional
	public void deleteToolById(long toolId) {
		adminDao.deleteToolById(toolId);
	}

	
}


