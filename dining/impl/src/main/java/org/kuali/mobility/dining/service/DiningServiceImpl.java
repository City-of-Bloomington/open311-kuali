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

package org.kuali.mobility.dining.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.kuali.mobility.dining.entity.FoodItem;
import org.kuali.mobility.dining.entity.Menu;

public class DiningServiceImpl implements DiningService {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DiningServiceImpl.class);
	
	private Map<String, List<String>> diningUrls;
	
	@Override
	public List<Menu> getMenus(String location) {
		String url = getXmlUrl(location);
		if (url != null) {
			return parseUrl(url);
		}
		return null;
	}
	
	private String getXmlUrl(String campusCode) {
		String url = null;
		List<String> urls = diningUrls.get(campusCode);
		if (urls != null && urls.size() > 0) {
			url = urls.get(0);
		}
		return url;
	}
	
	@SuppressWarnings("unchecked")
	private List<Menu> parseUrl(String url){
		List<Menu> menus = new ArrayList<Menu>();
		try {
			// Set up formatters
			SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
			// Process the XML	
			Document doc = retrieveDocumentFromUrl(url, 5000, 5000);
			Element root = doc.getRootElement();
			List<Element> xmlMenus = root.getChildren("menu");
			for (Iterator<Element> iterator = xmlMenus.iterator(); iterator.hasNext();) {
				Element xmlMenu = iterator.next();
				// Make the date format configurable
				String dateStr = xmlMenu.getChildText("date");
				Menu menu = new Menu();
				try {
					Date date = sdf.parse(dateStr);
					menu.setDate(date);
					List<Element> items = xmlMenu.getChildren("item");
					for (Iterator<Element> itemItr = items.iterator(); itemItr.hasNext();) {
						try {
							Element xmlItem = itemItr.next();
							String name = xmlItem.getChildText("name");
							String priceStr = xmlItem.getChildText("price");
							double price = Double.parseDouble(priceStr);
							FoodItem item = new FoodItem(name, price);
							menu.getItems().add(item);							
						} catch (Exception e) {}
					}
					menus.add(menu);
				} catch (ParseException e) {}
			}
		} catch (JDOMException e) {
			LOG.error(e.getMessage(), e);
		} catch (IOException e) {
			LOG.error(e.getMessage(), e);
		}
		return menus;
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

	public Map<String, List<String>> getDiningUrls() {
		return diningUrls;
	}

	public void setDiningUrls(Map<String, List<String>> diningUrls) {
		this.diningUrls = diningUrls;
	}

}


