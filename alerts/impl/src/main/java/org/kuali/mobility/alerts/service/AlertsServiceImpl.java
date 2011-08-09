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

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.kuali.mobility.alerts.entity.Alert;
import org.kuali.mobility.alerts.entity.CampusAlerts;
import org.kuali.mobility.configparams.service.ConfigParamService;
import org.kuali.mobility.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import flexjson.JSONDeserializer;

@Service
public class AlertsServiceImpl implements AlertsService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger
			.getLogger(AlertsServiceImpl.class);

	private static ConcurrentMap<String, CampusAlerts> cachedAlerts;

	// private static final String CP_JSON_ALERTS_URL = "Alerts.Json.Url";

	private static final ConcurrentMap<String, String> campusCodeMap;

	private static final long UPDATE_INTERVAL = 1000 * 60 * 60; // one hour

	@Autowired
	private ConfigParamService configParamService;

	static {
		cachedAlerts = new ConcurrentHashMap<String, CampusAlerts>();
		campusCodeMap = new ConcurrentHashMap<String, String>();
		campusCodeMap.put("UA", "IU");
		campusCodeMap.put("BL", "IUB");
		campusCodeMap.put("IN", "IUPUI");
		campusCodeMap.put("CO", "IUPUC");
		campusCodeMap.put("EA", "IUE");
		campusCodeMap.put("KO", "IUK");
		campusCodeMap.put("NW", "IUN");
		campusCodeMap.put("SE", "IUS");
		campusCodeMap.put("SB", "IUSB");
	}

	/**
	 * @see org.kuali.mobility.alerts.service.AlertsService#findAllAlertsByCriteria(java.util.Map)
	 */
	@Override
	public int findAlertCountByCriteria(Map<String, String> criteria) {
		return findAllAlertsByCriteria(criteria).size();
	}

	@Override
	public List<Alert> findAllAlertsByCriteria(Map<String, String> criteria) {
		// Note: RI does not use the criteria parameter

		if (criteria != null) {
			String campus = criteria.get("campus");
			if (campus != null && !"".equals(campus)) {
				return findAllAlertsByCampus(campus);
			} else {
				return findAllAlertsByCampus("UA");
			}
		} else {
			return findAllAlertsByCampus("UA");
		}
		// return
		// findAllAlertsFromJson(configParamService.findConfigParamByName(CP_JSON_ALERTS_URL).getValue());
	}

	@Override
	public List<Alert> findAllAlertsFromJson(String url) {
		String json = HttpUtil.stringFromUrl(url);

		if (json == null || "".equals(json)) {
			return new ArrayList<Alert>();
		}

		return new JSONDeserializer<ArrayList<Alert>>().deserialize(json);
	}

	private List<Alert> findAllAlertsByCampus(String campus) {
		List<Alert> campusStatuses = this.getAlertsByCode(campus);
		if (campusStatuses != null && "UA".equals(campus) && campusStatuses.size() > 1) {
			List<Alert> uaStatuses = new ArrayList<Alert>();
			List<Alert> filteredStatuses = new ArrayList<Alert>();
			Iterator<Alert> iter = campusStatuses.iterator();
			while (iter.hasNext()) {
				Alert status = (Alert) iter.next();
				if (isAlertToReport(status)) {
					filteredStatuses.add(status);
				} else if (campusCodeMap.get("UA").equals(status.getCampus())) {
					uaStatuses.add(status);
				}
			}
			if (filteredStatuses.size() < 1) {
				filteredStatuses.addAll(uaStatuses);
			}
			campusStatuses = filteredStatuses;
		}
		return campusStatuses;
	}

	private List<Alert> getAlertsByCode(String code) {
		CampusAlerts alerts = cachedAlerts.get(code);
		if (alerts == null) {
			// Entry does not yet exist.
			alerts = new CampusAlerts();
			CampusAlerts existing = cachedAlerts.putIfAbsent(code, alerts);
			if (existing != null) {
				alerts = existing;
			}
		}

		Calendar now = Calendar.getInstance();
		if (alerts.getUpdateTime() + UPDATE_INTERVAL < now.getTimeInMillis()) {
			alerts.setAlerts(parseAlerts(code, false));
			Calendar cal = Calendar.getInstance();
			alerts.setUpdateTime(cal.getTimeInMillis());
		}
		return alerts.getAlerts();
	}

	private boolean isAlertToReport(Alert alert) {
		return alert.getType() != null
				&& !alert.getType().equalsIgnoreCase("normal");
	}

	@SuppressWarnings("unchecked")
	public List<Alert> parseAlerts(String campus, boolean ignoreXmlCampus) {
		List<Alert> alerts = new ArrayList<Alert>();
		try {
			String url = configParamService.findValueByName("CAMPUS_STATUS_XML_URL") + campus;
			if ("IN".equals(campus)) {
				url = "http://www.iupui.edu/rss/jagalert_iu.xml";
			} else if ("EA".equals(campus)) {
				url = "http://www.iue.edu/emergency/feed.php";
			}

			Document doc = retrieveDocumentFromUrl(url, 5000, 5000);
			if (doc != null) {
				Element root = doc.getRootElement();
				List<Element> items = root.getChildren("status");
				for (Iterator<Element> iterator = items.iterator(); iterator.hasNext();) {
					Element item = iterator.next();
					String campusXml = item.getChildTextTrim("campus");
					String compareCampus = campusCodeMap.get(campus);
					if (ignoreXmlCampus || compareCampus.equalsIgnoreCase(campusXml) || campusCodeMap.get("UA").equals(campusXml)) {
						Alert alert = new Alert();
						alert.setCampus(item.getChildTextTrim("campus"));
						alert.setMobileText(item.getChildTextTrim("mobile-text"));
						alert.setPriority(item.getChildTextTrim("priority"));
						alert.setTitle(item.getChildTextTrim("title"));
						alert.setType(item.getChildTextTrim("type"));
						alert.setUrl(item.getChildTextTrim("url"));
						String keyStr = item.getChildTextTrim("key");
						int key = -1;
						if (keyStr != null && keyStr.length() > 0 && !keyStr.equals("null")) {
							try {
								key = Integer.parseInt(keyStr);
							} catch (NumberFormatException e) {
								LOG.error("Error parsing Alert key: (" + keyStr + ") for Alert: " + alert.getTitle(), e);
							}
						}
						alert.setKey(key);
						alerts.add(alert);
					}
				}
			}
		} catch (JDOMException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}

		return alerts;
	}

	protected Document retrieveDocumentFromUrl(String urlStr, int connectTimeout, int readTimeout) throws IOException, JDOMException {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		URL urlObj = new URL(urlStr);
		URLConnection urlConnection = urlObj.openConnection();
		urlConnection.setConnectTimeout(connectTimeout);
		urlConnection.setReadTimeout(readTimeout);

		doc = builder.build(urlConnection.getInputStream());
		return doc;
	}

	public void setConfigParamService(ConfigParamService configParamService) {
		this.configParamService = configParamService;
	}
}
