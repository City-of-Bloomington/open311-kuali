package org.kuali.mobility.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity(name="HomeTool")
@Table(name="HOME_TOOL_T")
public class HomeTool implements Serializable {
	
	private static final long serialVersionUID = -8942674782383943102L;

	@Id
    @SequenceGenerator(name="home_tool_sequence", sequenceName="SEQ_HOME_TOOL_T", initialValue=1000, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="home_tool_sequence")
    @Column(name="HOME_TOOL_ID")
    private Long homeToolId;
	
    @Column(name="HOME_ID")
    private Long homeScreenId;
	
    @Column(name="TOOL_ID")
    private Long toolId;
	
	@Column(name="ORDR")
	private int order;
	
	@Version
    @Column(name="VER_NBR")
    private Long versionNumber;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="HOME_ID")
	private HomeScreen homeScreen;
	
	@ManyToOne
	@PrimaryKeyJoinColumn(name="TOOL_ID")
	private Tool tool;

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

	public int getOrder() {
		return order;
	}

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

	public HomeScreen getHomeScreen() {
		return homeScreen;
	}

	public void setHomeScreen(HomeScreen homeScreen) {
		this.homeScreen = homeScreen;
	}

	public Tool getTool() {
		return tool;
	}

	public void setTool(Tool tool) {
		this.tool = tool;
	}	
}