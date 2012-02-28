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

package org.kuali.mobility.weather.dao;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.kuali.mobility.weather.entity.Weather;

public class WeatherDaoImpl implements WeatherDao {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(WeatherDaoImpl.class);

	private String url;

	@SuppressWarnings("unchecked")
	public Weather parseWeather() {
		Weather weatherData = new Weather();
		try {
			Document doc = retrieveDocumentFromUrl(url, 5000, 5000);
			Element root = doc.getRootElement();
			List<Element> data = root.getChildren("data");
			for (Iterator<Element> iterator = data.iterator(); iterator.hasNext();) {
				Element dataItem = iterator.next();				
				if ("forecast".equalsIgnoreCase(dataItem.getAttribute("type").getValue().trim())) {
					List<Element> items = dataItem.getChildren("time-layout");
					for (Iterator<Element> itemItr = items.iterator(); itemItr.hasNext();) {
						Element xmlItem = itemItr.next();
						if (xmlItem.getChild("layout-key").getValue().trim().startsWith("k-p12h-")) {
							List<Element> items2 = xmlItem.getChildren("start-valid-time");
							int i = 0;
							for (Iterator<Element> itemItr2 = items2.iterator(); itemItr2.hasNext();) {
								Element xmlItem2 = itemItr2.next();
								Map<String, String> temp;
								try {
									temp = weatherData.getForecasts().get(i);
								} catch (Exception e) {
									temp = new HashMap<String, String>();
									weatherData.getForecasts().add(temp);
								}
								temp.put("name", xmlItem2.getAttributeValue("period-name"));
								i++;
								LOG.debug(xmlItem2.getAttributeValue("period-name"));
							}
						}
					}					
					List<Element> itemsIcons = dataItem.getChild("parameters").getChildren("conditions-icon");
					for (Iterator<Element> itemItr = itemsIcons.iterator(); itemItr.hasNext();) {
						Element xmlItem = itemItr.next();
						if (xmlItem.getAttributeValue("time-layout").trim().startsWith("k-p12h-")) {
							List<Element> items2 = xmlItem.getChildren("icon-link");
							int i = 0;
							for (Iterator<Element> itemItr2 = items2.iterator(); itemItr2.hasNext();) {
								Element xmlItem2 = itemItr2.next();
								Map<String, String> temp;
								try {
									temp = weatherData.getForecasts().get(i);
								} catch (Exception e) {
									temp = new HashMap<String, String>();
									weatherData.getForecasts().add(temp);
								}
								temp.put("iconLink", xmlItem2.getValue());
								i++;
								LOG.debug(xmlItem2.getValue());
							}
						}
					}					
					List<Element> itemsText = dataItem.getChild("parameters").getChildren("wordedForecast");
					for (Iterator<Element> itemItr = itemsText.iterator(); itemItr.hasNext();) {
						Element xmlItem = itemItr.next();
						if (xmlItem.getAttributeValue("time-layout").trim().startsWith("k-p12h-")) {
							List<Element> items2 = xmlItem.getChildren("text");
							int i = 0;
							for (Iterator<Element> itemItr2 = items2.iterator(); itemItr2.hasNext();) {
								Element xmlItem2 = itemItr2.next();
								Map<String, String> temp;
								try {
									temp = weatherData.getForecasts().get(i);
								} catch (Exception e) {
									temp = new HashMap<String, String>();
									weatherData.getForecasts().add(temp);
								}
								temp.put("text", xmlItem2.getValue());
								i++;
								LOG.debug(xmlItem2.getValue());
							}
						}
					}					
				} else if ("current observations".equalsIgnoreCase(dataItem.getAttribute("type").getValue().trim())) {
					List<Element> items = dataItem.getChildren("parameters");
					for (Iterator<Element> itemItr = items.iterator(); itemItr.hasNext();) {
						Element xmlItem = itemItr.next();
						if (null != xmlItem.getChild("weather").getChild("weather-conditions").getAttributeValue("weather-summary")) {
							weatherData.setCurrentCondition(xmlItem.getChild("weather").getChild("weather-conditions").getAttributeValue("weather-summary"));
							LOG.debug("Current:" + xmlItem.getChild("weather").getChild("weather-conditions").getAttributeValue("weather-summary"));
						}
						if ("apparent".equalsIgnoreCase(xmlItem.getChild("temperature").getAttributeValue("type").trim())) {
							weatherData.setTemperature(xmlItem.getChild("temperature").getChildText("value"));
							LOG.debug("Temp:" + xmlItem.getChild("temperature").getChildText("value"));
						}
						if ("relative".equalsIgnoreCase(xmlItem.getChild("humidity").getAttributeValue("type").trim())) {
							weatherData.setHumidity(xmlItem.getChild("humidity").getChildText("value"));
							LOG.debug("Humidity:" + xmlItem.getChild("humidity").getChildText("value"));
						}
						if ("barometer".equalsIgnoreCase(xmlItem.getChild("pressure").getAttributeValue("type").trim())) {
							weatherData.setPressure(xmlItem.getChild("pressure").getChildText("value"));
							LOG.debug("Pressure:" + xmlItem.getChild("pressure").getChildText("value"));
						}
						if ("wind".equalsIgnoreCase(xmlItem.getChild("direction").getAttributeValue("type").trim())) {
							weatherData.setWindDirection(xmlItem.getChild("direction").getChildText("value"));
							LOG.debug("Wind direction:" + xmlItem.getChild("direction").getChildText("value"));
						}						
						List<Element> items2 = xmlItem.getChildren("wind-speed");
						for (Iterator<Element> itemItr2 = items2.iterator(); itemItr2.hasNext();) {
							Element xmlItem2 = itemItr2.next();
							if ("sustained".equalsIgnoreCase(xmlItem2.getAttributeValue("type").trim())) {
								weatherData.setWindSpeed(xmlItem2.getChildText("value"));
								LOG.debug("Wind speed:" + xmlItem2.getChildText("value"));
							}			
						}
					}										
				}
			}
		} catch (Exception e) {
			weatherData = null;
			LOG.error(e.getMessage(), e);
		}
		return weatherData;
	}
	
	private Document retrieveDocumentFromUrl(String urlStr, int connectTimeout, int readTimeout) throws IOException, JDOMException {
		SAXBuilder builder = new SAXBuilder();
		Document doc = null;
		URL urlObj = new URL(urlStr);
		URLConnection urlConnection = urlObj.openConnection();
		urlConnection.setConnectTimeout(connectTimeout);
		urlConnection.setReadTimeout(readTimeout);
		doc = builder.build(urlConnection.getInputStream());
		return doc;
	}	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

} 
