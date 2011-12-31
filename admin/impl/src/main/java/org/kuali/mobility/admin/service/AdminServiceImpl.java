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
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service for actually performing administrative tasks
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
@Service(value = "AdminService")
public class AdminServiceImpl implements AdminService {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AdminServiceImpl.class);

	@Autowired
    private AdminDao adminDao;

	@Transactional
	@Override
	public List<HomeScreen> getAllHomeScreens() {
		return adminDao.getAllHomeScreens();
	}
	
	@Transactional
	@Override
	@Cacheable(value="homeScreens", key="#homeScreenId")
	public HomeScreen getHomeScreenById(long homeScreenId) {
		return adminDao.getHomeScreenById(homeScreenId);
	}
	
	@Transactional
	@Override
	@Cacheable(value="homeScreens", key="#alias")
	public HomeScreen getHomeScreenByAlias(String alias) {
		return adminDao.getHomeScreenByAlias(alias);
	}
	
	@Transactional
	@Override
	@CacheEvict(value = "homeScreens", key="#homeScreen.homeScreenId", allEntries=false)
	public Long saveHomeScreen(HomeScreen homeScreen) {
		return adminDao.saveHomeScreen(homeScreen);
	}

	@Transactional
	@Override
	@CacheEvict(value = "homeScreens", key="#homeScreenId", allEntries=false)
	public void deleteHomeScreenById(long homeScreenId) {
		adminDao.deleteHomeScreenById(homeScreenId);
	}
	
	@Transactional
	@Override
	public List<Tool> getAllTools() {
		return adminDao.getAllTools();
	}
	
	@Transactional
	@Override
	public Tool getToolById(long toolId) {
		return adminDao.getToolById(toolId);
	}
	
	@Transactional
	@Override
	@CacheEvict(value = "homeScreens", allEntries=true)
	public Long saveTool(Tool tool) {
		return adminDao.saveTool(tool);
	}

	@Transactional
	@Override
	@CacheEvict(value = "homeScreens", allEntries=true)
	public void deleteToolById(long toolId) {
		adminDao.deleteToolById(toolId);
	}
	
}


