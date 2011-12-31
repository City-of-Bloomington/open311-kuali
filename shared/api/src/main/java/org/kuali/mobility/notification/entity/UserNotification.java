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

package org.kuali.mobility.notification.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table(name="KME_USR_NTFY_T")
public class UserNotification implements Serializable {

	private static final long serialVersionUID = -95981285235712123L;

    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID")
    private Long userNotificationId;

    @Column(name="NTFY_ID")
    private Long notificationId;

    @Column(name="PRSN_ID")
    private Long personId;

    @Column(name="DEV_ID")
    private String deviceId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="NTFY_DT")
	private Date notifyDate;

    @Version
    @Column(name="VER_NBR")
    protected Long versionNumber;

	public Long getUserNotificationId() {
		return userNotificationId;
	}

	public void setUserNotificationId(Long userNotificationId) {
		this.userNotificationId = userNotificationId;
	}

	public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public Date getNotifyDate() {
		return notifyDate;
	}

	public void setNotifyDate(Date notifyDate) {
		this.notifyDate = notifyDate;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}	

}
