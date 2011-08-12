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

package org.kuali.mobility.admin.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
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

@Entity(name="HomeScreen")
@Table(name="HOME_T")
public class HomeScreen implements Serializable {

	private static final long serialVersionUID = 4947101996672004361L;

	@Id
    @SequenceGenerator(name="home_sequence", sequenceName="SEQ_HOME_T", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="home_sequence")
    @Column(name="HOME_ID")
    private Long homeScreenId;
	
	@Column(name="HOME_NM")
	private String homeScreenName;
	
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="homeScreen")
    private List<HomeTool> homeTools;
	
	@Version
    @Column(name="VER_NBR")
    private Long versionNumber;	
	
	public HomeScreen() {
		homeTools = new ArrayList<HomeTool>();
	}

	public List<HomeTool> getHomeTools() {
		return homeTools;
	}

	public void setHomeTools(List<HomeTool> homeTools) {
		this.homeTools = homeTools;
	}
	
	public void setHomeTools(HomeTool[] homeTools) {
		this.homeTools = Arrays.asList(homeTools);
	}

	public Long getHomeScreenId() {
		return homeScreenId;
	}

	public void setHomeScreenId(Long homeScreenId) {
		this.homeScreenId = homeScreenId;
	}

	public String getHomeScreenName() {
		return homeScreenName;
	}

	public void setHomeScreenName(String homeScreenName) {
		this.homeScreenName = homeScreenName;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

}
