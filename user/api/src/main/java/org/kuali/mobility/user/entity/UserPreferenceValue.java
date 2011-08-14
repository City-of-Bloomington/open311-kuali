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

package org.kuali.mobility.user.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="USR_PREF_VAL_T")
public class UserPreferenceValue implements Serializable {

	private static final long serialVersionUID = -4767899922710427387L;

    @Id
    @SequenceGenerator(name="pref_sequence", sequenceName="SEQ_PREF_T", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="pref_sequence")
    @Column(name="PREF_VAL_ID")
	private Long preferenceValueId;

    @Column(name="PREF_VAL")
	private String value;

    @Basic
    @Column(name="PREF_ID", insertable=false, updatable=false)
	private Long preferenceId;

    @OneToOne(fetch=FetchType.LAZY)
    @JoinColumn(
    	name="PREF_ID", nullable=false
    )
	protected UserPreference preference;
	
    @Version
    @Column(name="VER_NBR")
    protected Long versionNumber;

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Long getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(Long preferenceId) {
		this.preferenceId = preferenceId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Long getPreferenceValueId() {
		return preferenceValueId;
	}

	public void setPreferenceValueId(Long preferenceValueId) {
		this.preferenceValueId = preferenceValueId;
	}

	public UserPreference getPreference() {
		return preference;
	}

	public void setPreference(UserPreference preference) {
		this.preference = preference;
	}
	
}
