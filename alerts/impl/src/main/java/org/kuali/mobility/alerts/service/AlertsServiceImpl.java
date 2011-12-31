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

package org.kuali.mobility.alerts.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.kuali.mobility.alerts.entity.Alert;
import org.kuali.mobility.alerts.entity.Alerts;
import org.kuali.mobility.campus.entity.Campus;
import org.kuali.mobility.campus.service.CampusService;
import org.kuali.mobility.util.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AlertsServiceImpl implements AlertsService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AlertsServiceImpl.class);

	private ConcurrentMap<String, Alerts> cachedAlerts;

	private Map<String, List<String>> alertUrls;

	private String cacheMinutes;

	private String dataMappingUrl;

	@Autowired
	private CampusService campusService;

	@Autowired
	private DataMapper dataMapper;

	public AlertsServiceImpl() {
		cachedAlerts = new ConcurrentHashMap<String, Alerts>();
	}

	@Override
	public List<Alert> findAlertsByCampus(String campus) {
		String selectedCampus = "ALL";
		if (campus != null && !"".equals(campus.trim())) {
			selectedCampus = campus;
		}

		List<Alert> campusStatuses = this.getAlertsByCode(selectedCampus);
		if (campusStatuses != null && campusStatuses.size() > 1) {
			List<Alert> filteredStatuses = new ArrayList<Alert>();
			Iterator<Alert> iter = campusStatuses.iterator();
			while (iter.hasNext()) {
				Alert status = (Alert) iter.next();
				if (isAlertToReport(status) && !filteredStatuses.contains(status)) {
					filteredStatuses.add(status);
				}
			}
			campusStatuses = filteredStatuses;
		}
		if (campusStatuses == null || campusStatuses.isEmpty()) {
			List<Alert> alerts = new ArrayList<Alert>();
			Alert alert = new Alert();
			alert.setCampus("ALL");
			alert.setKey(-1);
			alert.setMobileText("Status is normal.");
			alert.setPriority("1");
			alert.setTitle("Normal");
			alert.setType("normal");
			alert.setUrl("");
			alerts.add(alert);
			campusStatuses = alerts;
		}
		return campusStatuses;
	}
	
	@Override
	public int findAlertCountByCampus(String campus) {
		String selectedCampus = "ALL";
		if (campus != null && !"".equals(campus.trim())) {
			selectedCampus = campus;
		}

		List<Alert> campusStatuses = this.getAlertsByCode(selectedCampus);
		if (campusStatuses != null && campusStatuses.size() > 1) {
			List<Alert> filteredStatuses = new ArrayList<Alert>();
			Iterator<Alert> iter = campusStatuses.iterator();
			while (iter.hasNext()) {
				Alert status = (Alert) iter.next();
				if (isAlertToReport(status) && !filteredStatuses.contains(status)) {
					filteredStatuses.add(status);
				}
			}
			campusStatuses = filteredStatuses;
		}
		if (campusStatuses == null || campusStatuses.isEmpty()) {
			return 0;
		} else {
			return campusStatuses.size();
		}
	}

	private List<Alert> getAlertsByCode(String campus) {
		if ("ALL".equals(campus)) {
			List<Alert> foundAlerts = new ArrayList<Alert>();
			for (Campus toolCampus : campusService.findCampusesByTool("alerts")) {
				foundAlerts.addAll(getAlerts(toolCampus.getCode()));
			}
			return foundAlerts;
		} else {
			return getAlerts(campus);
		}
	}

	private List<Alert> getAlerts(String campus) {
		Alerts alerts = cachedAlerts.get(campus);
		if (alerts == null) {
			alerts = new Alerts();
			Alerts existing = cachedAlerts.putIfAbsent(campus, alerts);
			if (existing != null) {
				alerts = existing;
			}
		}
		long updateInterval = 0;
		try {
			if (cacheMinutes != null && !"".equals(cacheMinutes.trim())) {
				updateInterval = 1000 * 60 * Long.parseLong(cacheMinutes);
			}
		} catch (Exception e) {
			updateInterval = 0;
		}
		if (alerts.getUpdateTime() + updateInterval < System.currentTimeMillis()) {
			alerts.setAlerts(parseAlerts(campus));
			alerts.setUpdateTime(System.currentTimeMillis());
		}
		return alerts.getAlerts();
	}

	private boolean isAlertToReport(Alert alert) {
		return alert.getType() != null && !alert.getType().equalsIgnoreCase("normal");
	}

	private List<Alert> parseAlerts(String campus) {
		List<Alert> alerts = new ArrayList<Alert>();
		if (alertUrls == null || alertUrls.get(campus) == null) {
			return alerts;
		}
		for (String sourceUrl : alertUrls.get(campus)) {
			try {
				URL url = new URL(sourceUrl);
				if (dataMappingUrl != null && !"".equals(dataMappingUrl.trim())) {
					alerts.addAll(dataMapper.mapData(alerts, url, new URL(dataMappingUrl)));
				} else {
					alerts.addAll(dataMapper.mapData(alerts, url, "alertMapping.xml"));
				}
			} catch (Exception e) {
				LOG.error("errors", e);
			}
		}
		return alerts;
	}

	public void setAlertUrls(Map<String, List<String>> alertUrls) {
		this.alertUrls = alertUrls;
	}

	public void setCacheMinutes(String cacheMinutes) {
		this.cacheMinutes = cacheMinutes;
	}

	public void setDataMappingUrl(String dataMappingUrl) {
		this.dataMappingUrl = dataMappingUrl;
	}

	public void setDataMapper(DataMapper dataMapper) {
		this.dataMapper = dataMapper;
	}

}
