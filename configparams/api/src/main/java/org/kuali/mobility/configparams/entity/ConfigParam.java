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

package org.kuali.mobility.configparams.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import flexjson.JSONSerializer;

/**
 * Class defining a configuration parameter
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
@Entity
@Table(name="KME_CNFG_PARM_T")
public class ConfigParam implements Serializable {

    private static final long serialVersionUID = -7425581809827657649L;

    @Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID")
    private Long configParamId;

    @Column(name="NM")
	private String name;

    @Column(name="VAL")
    private String value;

    @Version
    @Column(name="VER_NBR")
    protected Long versionNumber;
	
	public ConfigParam() {
	}
	
	/**
	 * @return the ConfigParam in a JSON format
	 */
    public String toJson() {
        return new JSONSerializer().exclude("*.class").serialize(this);
    }

    public Long getConfigParamId() {
        return configParamId;
    }

    public void setConfigParamId(Long configParamId) {
        this.configParamId = configParamId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getVersionNumber() {
        return versionNumber;
    }

    public void setVersionNumber(Long versionNumber) {
        this.versionNumber = versionNumber;
    }

}
