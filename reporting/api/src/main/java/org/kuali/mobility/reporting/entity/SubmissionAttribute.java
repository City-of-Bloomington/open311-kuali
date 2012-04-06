/**
 * Copyright 2011-2012 The Kuali Foundation Licensed under the
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

package org.kuali.mobility.reporting.entity;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="KME_SUBMISSION_ATTR_T")
public class SubmissionAttribute implements Serializable {

	private static final long serialVersionUID = 8851390314197309082L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID", nullable=false, updatable=false)
    private Long id;
	
	@Column(name="PRNT_ID")
	private Long parentId;
	
	@Column(name="SUBMISSION_ID", nullable=false, insertable=false, updatable=false)
	private Long submissionId;
	
	@Column(name="KY")
	private String key;
	
	@Column(name="VAL_TXT")
	private String valueText;
	
	@Column(name="VAL_LG_TXT")
	private String valueLargeText;
	
	@Column(name="VAL_NBR")
	private Long valueNumber;
	
	@Column(name="VAL_DT")
	private Timestamp valueDate;
	
	@Column(name="VAL_BIN")
	private byte[] valueBinary;
	    
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="SUBMISSION_ID")
	private Submission submission;

	@Version
    @Column(name="VER_NBR")
    protected Long versionNumber;

    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getSubmissionId() {
		return submissionId;
	}

	public void setSubmissionId(Long submissionId) {
		this.submissionId = submissionId;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValueText() {
		return valueText;
	}

	public void setValueText(String valueText) {
		this.valueText = valueText;
	}

	public String getValueLargeText() {
		return valueLargeText;
	}

	public void setValueLargeText(String valueLargeText) {
		this.valueLargeText = valueLargeText;
	}

	public Long getValueNumber() {
		return valueNumber;
	}

	public void setValueNumber(Long valueNumber) {
		this.valueNumber = valueNumber;
	}

	public Timestamp getValueDate() {
		return valueDate;
	}

	public void setValueDate(Timestamp valueDate) {
		this.valueDate = valueDate;
	}

	public byte[] getValueBinary() {
		return valueBinary;
	}

	public void setValueBinary(byte[] valueBinary) {
		this.valueBinary = valueBinary;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Submission getSubmission() {
		return submission;
	}

	public void setSubmission(Submission submission) {
		this.submission = submission;
	}
		
}
