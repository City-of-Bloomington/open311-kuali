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

package org.kuali.mobility.coursedescriptions.service;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;
import org.kuali.mobility.coursedescriptions.entity.Course;
import org.kuali.mobility.coursedescriptions.entity.CourseSubject;
import org.springframework.stereotype.Service;

@Service
public class CourseDescriptionsServiceImpl implements CourseDescriptionsService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(CourseDescriptionsServiceImpl.class);
	
	
	@Override
	public List<CourseSubject> getCourseSubjects(String location) {
		String url = getXmlUrl(location);
		if (url != null) {
			return parseUrl(url);
		}
		return null;
	}
	
	//Takes two strings and returns a course who's
	//number matches the number passed in and is inside of the subject of who's id was passed in.
	@Override
	public Course getCourseByNumber(String id, String number) {
		List<CourseSubject> courseSubjects = getCourseSubjects("Optometry");
		if (courseSubjects != null){
			for(CourseSubject courseSubject : courseSubjects){
				if (courseSubject.getId().equals(id)) {
					List<Course> courses = courseSubject.getItems();
					for (Course course : courses) {
						if (course.getNumber().equals(number)) {
							return course;
						}
					}
				}
			}
		}
		return null;
	}
	
	private String getXmlUrl(String campusCode) {
		String url = null;
		if (campusCode.equals("Optometry")) {
			url = this.getUrlOptometry();
		}
		return url;
	}
	
	private String getUrlOptometry() {
		String url = null;
        try {
            url = "http://www.indiana.edu/~iumobile/coursedescriptions/optometry.xml";
        } catch (Exception e) {
            LOG.error("Config Param: Food.Url.Southeast does not exist.", e);
        }
        return url;
	}
	
	@SuppressWarnings("unchecked")
	private List<CourseSubject> parseUrl(String url){
		List<CourseSubject> menus = new ArrayList<CourseSubject>();
		try {
			// Process the XML	
			Document doc = retrieveDocumentFromUrl(url, 5000, 5000);
			Element root = doc.getRootElement();
			List<Element> xmlMenus = root.getChildren("system-folder");
			for (Element xmlMenu : xmlMenus) {
				String area = xmlMenu.getChildText("display-name");
				String id = xmlMenu.getAttributeValue("id");
				CourseSubject menu = new CourseSubject(area, id);
				List<Element> courses = xmlMenu.getChildren("system-page");
				for(Element xmlItem : courses) { 
					try {
						xmlItem = xmlItem.getChild("system-data-structure");
						xmlItem = xmlItem.getChild("course");
						String title = xmlItem.getChildText("title");
						String departmentCode = xmlItem.getChildText("department-code");
						String number = xmlItem.getChildText("number");
						String credits = xmlItem.getChildText("credits");
						String maxCredits = xmlItem.getChildText("max-credits");
						String prerequisite = xmlItem.getChildText("prerequisite");
						String corequisite = xmlItem.getChildText("corequisite");
						String description = xmlItem.getChildText("bulletin-description");
						Course item = new Course(title, departmentCode, number, credits, maxCredits, prerequisite, corequisite, description);
						menu.getItems().add(item);							
					} catch (Exception e) {}
				}
				menus.add(menu);
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

}


