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
 
package org.kuali.mobility.emergencyinfo.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import flexjson.JSONSerializer;

@Entity
@Table(name="KME_EM_INFO_T")
public class EmergencyInfo implements Serializable {

    private static final long serialVersionUID = 8753764116073085733L;

    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID")
    private Long emergencyInfoId;

    @Column(name="TYP")
	private String type;

    @Column(name="TTL")
    private String title;

    @Column(name="LNK")
	private String link;

    @Column(name="CMPS")
    private String campus;
    
    @Column(name="ORDR")
    private int order;

    @Version
    @Column(name="VER_NBR")
    protected Long versionNumber;
	
	public EmergencyInfo() {
	}
    	
    public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }
    
	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

    public Long getEmergencyInfoId() {
        return emergencyInfoId;
    }

    public void setEmergencyInfoId(Long emergencyInfoId) {
        this.emergencyInfoId = emergencyInfoId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getCampus() {
        return campus;
    }

    public void setCampus(String campus) {
        this.campus = campus;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }
    
}
