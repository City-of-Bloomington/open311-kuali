package org.kuali.mobility.alerts.entity;

import java.util.ArrayList;
import java.util.List;

public class CampusAlerts {
	private String campusCode;
	private long updateTime;
	private List<Alert> alerts;
	
	public CampusAlerts() {
		alerts = new ArrayList<Alert>();
	}
	
	public String getCampusCode() {
		return campusCode;
	}
	public void setCampusCode(String campusCode) {
		this.campusCode = campusCode;
	}
	public long getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(long updateTime) {
		this.updateTime = updateTime;
	}
	public List<Alert> getAlerts() {
		return alerts;
	}
	public void setAlerts(List<Alert> alerts) {
		this.alerts = alerts;
	}
	
}
