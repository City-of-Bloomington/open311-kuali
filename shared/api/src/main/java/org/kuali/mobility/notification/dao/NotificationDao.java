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

import org.kuali.mobility.notification.entity.Notification;
import org.kuali.mobility.notification.entity.UserNotification;

public interface NotificationDao {

    Notification findNotificationById(Long id);
    Long saveNotification(Notification notification);
    void deleteNotificationById(Long id);
    List<Notification> findAllNotifications();
    List<Notification> findAllValidNotifications(Date date);

    UserNotification findUserNotificationById(Long id);
    UserNotification findUserNotificationByNotificationId(Long id);
    Long saveUserNotification(UserNotification userNotification);
    void deleteUserNotificationById(Long id);
    List<UserNotification> findAllUserNotificationsByDeviceId(String id);
    List<UserNotification> findAllUserNotificationsByPersonId(Long id);
    
}