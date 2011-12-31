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
import javax.persistence.Table;
import javax.persistence.Version;

/**
 * Defines a home screen with a collection of tools
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
@Entity
@Table(name="KME_HM_SCRN_T")
public class HomeScreen implements Serializable {

	private static final long serialVersionUID = 4947101996672004361L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID")
    private Long homeScreenId;
	
	@Column(name="ALIAS")
	private String alias;

	@Column(name="TTL")
	private String title;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy="homeScreen")
    private List<HomeTool> homeTools;
	
	@Version
    @Column(name="VER_NBR")
    private Long versionNumber;	
	
	public HomeScreen() {
		homeTools = new ArrayList<HomeTool>();
	}

	/**
	 * @return the HomeTool objects associated with this HomeScreen
	 */
	public List<HomeTool> getHomeTools() {
		return homeTools;
	}

	/**
	 * set the HomeTool objects
	 * @param homeTools
	 */
	public void setHomeTools(List<HomeTool> homeTools) {
		this.homeTools = homeTools;
	}
	
	/**
	 * set the HomeTools collection with an array
	 * @param homeTools
	 */
	public void setHomeTools(HomeTool[] homeTools) {
		this.homeTools = Arrays.asList(homeTools);
	}

	public Long getHomeScreenId() {
		return homeScreenId;
	}

	public void setHomeScreenId(Long homeScreenId) {
		this.homeScreenId = homeScreenId;
	}

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

}
