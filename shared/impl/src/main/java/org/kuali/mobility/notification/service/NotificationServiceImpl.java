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
 
package org.kuali.mobility.notification.service;

import java.util.Date;
import java.util.List;

import org.kuali.mobility.notification.dao.NotificationDao;
import org.kuali.mobility.notification.entity.Notification;
import org.kuali.mobility.notification.entity.UserNotification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NotificationServiceImpl implements NotificationService {

	@Autowired
	private NotificationDao notificationDao;	
	public void setNotificationDao(NotificationDao notificationDao) {
		this.notificationDao = notificationDao;
	}

	@Override
	@Transactional
	public Notification findNotificationById(Long id) {
		return notificationDao.findNotificationById(id);
	}

	@Override
	@Transactional
	public List<Notification> findAllValidNotifications(Date date) {
		return notificationDao.findAllValidNotifications(date);
	}

	@Override
	@Transactional
	public Long saveNotification(Notification notification) {
		return notificationDao.saveNotification(notification);
	}

	@Override
	@Transactional
	public void deleteNotificationById(Long id) {
		notificationDao.deleteNotificationById(id);
	}

	@Override
	@Transactional
	public UserNotification findUserNotificationById(Long id) {
		return notificationDao.findUserNotificationById(id);
	}

	@Override
	@Transactional
	public UserNotification findUserNotificationByNotificationId(Long id) {
		return notificationDao.findUserNotificationByNotificationId(id);
	}

	@Override
	@Transactional
	public Long saveUserNotification(UserNotification userNotification) {
		return notificationDao.saveUserNotification(userNotification);
	}

	@Override
	@Transactional
	public void deleteUserNotificationById(Long id) {
		notificationDao.deleteUserNotificationById(id);
	}

	@Override
	@Transactional
	public List<UserNotification> findAllUserNotificationsByDeviceId(String id) {
		return findAllUserNotificationsByDeviceId(id);
	}

	@Override
	@Transactional
	public List<UserNotification> findAllUserNotificationsByPersonId(Long id) {
		return findAllUserNotificationsByPersonId(id);
	}

}
