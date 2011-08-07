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
 
package org.kuali.mobility.notification.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.mobility.notification.entity.Notification;
import org.kuali.mobility.notification.entity.UserNotification;
import org.springframework.stereotype.Repository;

@Repository
public class NotificationDaoImpl implements NotificationDao {

    @PersistenceContext
    private EntityManager entityManager;

	@Override
	public Notification findNotificationById(Long id) {
		Query query = entityManager.createQuery("select n from Notification n where n.notificationId = :id");
        query.setParameter("id", id);
        try {
        	return (Notification) query.getSingleResult();
        } catch (Exception e) {
        	return null;
        }
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> findAllNotifications() {
        Query query = entityManager.createQuery("select n from Notification n");
        try { 
        	return (List<Notification>) query.getResultList();
        } catch (Exception e) {        	
        	return null;
        }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Notification> findAllValidNotifications(Date date) {
        Query query = entityManager.createQuery("select n from Notification n where (n.startDate is null and n.endDate is null) or (n.startDate is null and :date < n.endDate) or (:date > n.startDate and n.endDate is null) or (:date between n.startDate and n.endDate)");
        query.setParameter("date", date);
        try { 
        	return (List<Notification>) query.getResultList();
        } catch (Exception e) {        	
        	return null;
        }
	}
	
	@Override
	public Long saveNotification(Notification notification) {
        if (notification == null) {
            return null;
        }
        try {
	        if (notification.getNotificationId() == null) {
	            entityManager.persist(notification);
	        } else {
	            entityManager.merge(notification);
	        }
        } catch (OptimisticLockException oe) {
            return null;
        }
        return notification.getNotificationId();
    }

	@Override
	public void deleteNotificationById(Long id) {
        Query query = entityManager.createQuery("delete from Notification n where n.notificationId = :id");
        query.setParameter("id", id);
        query.executeUpdate();
	}

	@Override
	public UserNotification findUserNotificationById(Long id) {
		Query query = entityManager.createQuery("select un from UserNotification un where un.userNotificationId = :id");
        query.setParameter("id", id);
        try {
        	return (UserNotification) query.getSingleResult();
        } catch (Exception e) {
        	return null;
        }
	}
	
	@Override
	public UserNotification findUserNotificationByNotificationId(Long id) {
		Query query = entityManager.createQuery("select un from UserNotification un where un.notificationId = :id");
        query.setParameter("id", id);
        try {
        	return (UserNotification) query.getSingleResult();
        } catch (Exception e) {
        	return null;
        }
	}


	@Override
	public Long saveUserNotification(UserNotification userNotification) {
        if (userNotification == null) {
            return null; 
        }
        try {
	        if (userNotification.getUserNotificationId() == null) {
	            entityManager.persist(userNotification);
	        } else {
	            entityManager.merge(userNotification);
	        }
        } catch (OptimisticLockException oe) {
            return null;
        }
        return userNotification.getUserNotificationId();
    }

	@Override
	public void deleteUserNotificationById(Long id) {
        Query query = entityManager.createQuery("delete from UserNotification un where un.userNotificationId = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

	@SuppressWarnings("unchecked")
	@Override
	public List<UserNotification> findAllUserNotificationsByDeviceId(String id) {
        Query query = entityManager.createQuery("select un from UserNotification un where deviceId = :id");
        query.setParameter("id", id);
        try { 
        	return (List<UserNotification>) query.getResultList();
        } catch (Exception e) {        	
        	return null;
        }
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserNotification> findAllUserNotificationsByPersonId(Long id) {
        Query query = entityManager.createQuery("select un from UserNotification un where personId = :id");
        query.setParameter("id", id);
        try { 
        	return (List<UserNotification>) query.getResultList();
        } catch (Exception e) {        	
        	return null;
        }
	}

}
