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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity(name="Notification")
@Table(name="NOTIFICATION_T")
public class Notification implements Serializable {

	private static final long serialVersionUID = -95981285235712123L;

    @Id
    @SequenceGenerator(name="notification_sequence", sequenceName="SEQ_NOTIFICATION_T", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="notification_sequence")
    @Column(name="NOTIFICATION_ID")
    private Long notificationId;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="START_DT")
	private Date startDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name="END_DT")
	private Date endDate;

    @Column(name="TITLE")
	private String title;

    @Column(name="MESSAGE")
    private String message;

    // TODO: This needs to be refactored to be a set of generic user attributes, permissions, etc
    @Column(name="PCAMPUS")
    private String primaryCampus;

    @Column(name="NOTIFICATION_TYPE")
    private Long notificationType;

    @Version
    @Column(name="VER_NBR")
    protected Long versionNumber;	

    public Long getNotificationId() {
		return notificationId;
	}

	public void setNotificationId(Long notificationId) {
		this.notificationId = notificationId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPrimaryCampus() {
		return primaryCampus;
	}

	public void setPrimaryCampus(String primaryCampus) {
		this.primaryCampus = primaryCampus;
	}

	public Long getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(Long notificationType) {
		this.notificationType = notificationType;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}
	
}
