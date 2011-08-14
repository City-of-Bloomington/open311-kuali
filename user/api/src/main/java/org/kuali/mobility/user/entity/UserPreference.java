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
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="USR_PREF_T")
public class UserPreference implements Serializable {

	private static final long serialVersionUID = -9145594618847463951L;

    @Id
    @SequenceGenerator(name="usr_pref_sequence", sequenceName="SEQ_USR_PREF_T", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="usr_pref_sequence")
    @Column(name="PREF_ID")
	private Long preferenceId;

    @Column(name="PERSON_ID")
	private Long principalId;

    @Column(name="PREF_NM")
	private String preferenceName;

    @Version
    @Column(name="VER_NBR")
    protected Long versionNumber;

    @OneToMany(fetch=FetchType.EAGER, cascade={CascadeType.ALL}, mappedBy="preferenceId")
	private List<UserPreferenceValue> preferenceValues;

	public UserPreference() {
		this.preferenceValues = new ArrayList<UserPreferenceValue>();
	}
	
	public UserPreferenceValue getPreferenceValueById(Long preferenceValueId) {
		UserPreferenceValue value = null;
    	for (UserPreferenceValue val : this.getPreferenceValues()) {
    		if (val.getPreferenceValueId().equals(preferenceValueId)) {
    			value = val;
    			break;
    		}
    	}
    	return value;
	}
	
	public void addPreferenceValue(UserPreferenceValue value) {
		value.setPreference(this);
		this.getPreferenceValues().add(value);
	}
	
	public void removePreferenceValue(Long preferenceValueId) {
		this.getPreferenceValues().remove(getPreferenceValueById(preferenceValueId));
	}

	public Long getPrincipalId() {
		return principalId;
	}

	public void setPrincipalId(Long principalId) {
		this.principalId = principalId;
	}

	public Long getPreferenceId() {
		return preferenceId;
	}

	public void setPreferenceId(Long preferenceId) {
		this.preferenceId = preferenceId;
	}

	public String getPreferenceName() {
		return preferenceName;
	}

	public void setPreferenceName(String preferenceName) {
		this.preferenceName = preferenceName;
	}

	public List<UserPreferenceValue> getPreferenceValues() {
		return preferenceValues;
	}

	public void setPreferenceValues(List<UserPreferenceValue> preferenceValues) {
		this.preferenceValues = preferenceValues;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}
	
}
