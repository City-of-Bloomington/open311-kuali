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

/**
 * Defines an object to link HomeScreen objects with Tool objects
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
@Entity
@Table(name="KME_HM_TL_T")
public class HomeTool implements Serializable, Comparable<HomeTool> {
	
	private static final long serialVersionUID = -8942674782383943102L;

	@Id
	@GeneratedValue(strategy = GenerationType.TABLE)
    @Column(name="ID")
    private Long homeToolId;
	
    @Column(name="HM_SCRN_ID", insertable=false, updatable=false)
    private Long homeScreenId;
	
    @Column(name="TL_ID", insertable=false, updatable=false)
    private Long toolId;
	
	@Column(name="ORDR")
	private int order;
		
	@ManyToOne
	@JoinColumn(name="HM_SCRN_ID")
	private HomeScreen homeScreen;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="TL_ID")
	private Tool tool;
	
	@Version
    @Column(name="VER_NBR")
    private Long versionNumber;

	public HomeTool() {}
	
	public HomeTool(HomeScreen homeScreen, Tool tool, int order) {
		this.homeScreen = homeScreen;
		this.homeScreenId = homeScreen.getHomeScreenId();
		this.tool = tool;
		this.toolId = tool.getToolId();
		this.order = order;
	}

	public Long getHomeScreenId() {
		return homeScreenId;
	}

	public void setHomeScreenId(Long homeScreenId) {
		this.homeScreenId = homeScreenId;
	}

	public Long getToolId() {
		return toolId;
	}

	public void setToolId(Long toolId) {
		this.toolId = toolId;
	}

	/**
	 * @return the display order of the Tool for the associated HomeScreen
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * set the display order of the Tool for the associated HomeScreen
	 * @param order
	 */
	public void setOrder(int order) {
		this.order = order;
	}

	public Long getVersionNumber() {
		return versionNumber;
	}

	public void setVersionNumber(Long versionNumber) {
		this.versionNumber = versionNumber;
	}

	public Long getHomeToolId() {
		return homeToolId;
	}

	public void setHomeToolId(Long homeToolId) {
		this.homeToolId = homeToolId;
	}

	/**
	 * @return the HomeScreen associated with the Tool
	 */
	public HomeScreen getHomeScreen() {
		return homeScreen;
	}

	/**
	 * set the HomeScreen to associate with the Tool
	 * @param homeScreen
	 */
	public void setHomeScreen(HomeScreen homeScreen) {
		this.homeScreen = homeScreen;
	}

	/**
	 * @return the Tool associated with the HomeScreen
	 */
	public Tool getTool() {
		return tool;
	}

	/**
	 * set the Tool to associate with the HomeScreen
	 * @param tool
	 */
	public void setTool(Tool tool) {
		this.tool = tool;
	}

	@Override
	public int compareTo(HomeTool that) {
		if (that == null) {
			return -1;
		}
		if (this.order == that.order) {
			return 0;
		}
		return this.order < that.order ? -1 : 1;
	}	
	
}
