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

package org.kuali.mobility.user.service;

import java.util.List;

import org.kuali.mobility.user.dao.UserDao;
import org.kuali.mobility.user.entity.User;
import org.kuali.mobility.user.entity.UserPreference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    public User findUserByDeviceId(String deviceId) {
        return userDao.findUserByDeviceId(deviceId);
    }
    
    @Transactional
    public User findUserByPrincipalName(String principalName) {
        return userDao.findUserByPrincipalName(principalName);
    }
    
    @Transactional
    public User findUserByPrincipalId(Long principalId) {
        return userDao.findUserByPrincipalId(principalId);
    }

    @Transactional
    public void saveUser(User user) {
        userDao.saveUser(user);
    }

    @Transactional
	public void saveUserPreference(UserPreference userPreference) {
		userDao.saveUserPreference(userPreference);
	}

    @Transactional
	public void deleteUserPreferenceById(Long preferenceId) {
		userDao.deleteUserPreferenceById(preferenceId);
	}

    @Transactional
	public List<UserPreference> findAllUserPreferencesByPrincipalId(Long principalId) {
		return userDao.findAllUserPreferencesByPrincipalId(principalId);
	}

    @Transactional
	public UserPreference findUserPreferenceByPreferenceId(Long preferenceId) {
		return userDao.findUserPreferenceByPreferenceId(preferenceId);
	}
    
}
