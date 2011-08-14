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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.kuali.mobility.admin.dao.AdminDao;
import org.kuali.mobility.admin.entity.HomeScreen;
import org.kuali.mobility.admin.entity.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value = "AdminService")
public class AdminServiceImpl implements AdminService {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AdminServiceImpl.class);

	private static final int HOMESCREEN_UPDATE_INTERVAL = 5; //5 min
	
	private static ConcurrentMap<String, HomeScreen> homeScreens;
	
	private static Thread homeScreenReloaderThread = null;

	static {
		homeScreens = new ConcurrentHashMap<String, HomeScreen>();
	} 
	
	@Autowired
    private AdminDao adminDao;
	public void setAdminDao(AdminDao adminDao) {
		this.adminDao = adminDao;
	}
	
	@Override
	public void startCache() {
		homeScreenReloaderThread = new Thread(new HomeScreenReloader());
		homeScreenReloaderThread.setDaemon(true);
		homeScreenReloaderThread.start();
    }
    
	@Override
    public void stopCache() {
    	homeScreenReloaderThread.interrupt();
    	homeScreenReloaderThread = null;
    }
	
	public HomeScreen getCachedHomeScreenByAlias(String alias) {
		HomeScreen homeScreen = homeScreens.get(alias);
		if (homeScreen == null) {
			LOG.warn("Cannot find homeScreen with alias: " + alias + " in the cache. Fetching from database");
			return getHomeScreenByAlias(alias);
		}
		return homeScreen;
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
	public HomeScreen getHomeScreenByAlias(String alias) {
		return adminDao.getHomeScreenByAlias(alias);
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
	
	private class HomeScreenReloader implements Runnable {
        
        public void run() {    
            Calendar updateCalendar = Calendar.getInstance();
            Date nextCacheUpdate = new Date();
                     
            // Cache loop
            while (true) {
                Date now = new Date();
                if (now.after(nextCacheUpdate)) {
                    try {
                    	reloadHomeScreens();	
                    } catch (Exception e) {
                    	LOG.error("Error reloading home screen cache.", e);
                    }
                    updateCalendar.add(Calendar.MINUTE, HOMESCREEN_UPDATE_INTERVAL);
                    nextCacheUpdate = new Date(updateCalendar.getTimeInMillis());
                }
                try {
                    Thread.sleep(1000 * 60);
                } catch (InterruptedException e) {
                    LOG.error("Error:", e);
                }
            }
        }

		private void reloadHomeScreens() {
			List<HomeScreen> names = adminDao.getAllHomeScreens();
			for (HomeScreen homeScreen : names) {
				homeScreens.put(homeScreen.getAlias(), adminDao.getHomeScreenByAlias(homeScreen.getAlias()));
			}			
		}
        
	}
	
}


