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

package org.kuali.mobility.people.service;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.kuali.mobility.people.entity.Person;
import org.kuali.mobility.people.entity.PersonImpl;
import org.kuali.mobility.people.entity.Search;
import org.kuali.mobility.shared.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.iu.uis.sit.util.directory.AdsPerson;
import edu.iu.uis.sit.util.directory.IUEduJob;

@Service
public class PeopleServiceImpl implements PeopleService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(PeopleServiceImpl.class);

	@Autowired
	private PeopleAdsService adsService;

	@Override
	public List<Person> performSearch(Search search) {
		List<Person> persons = null;
		int resultLimit = adsService.getResultLimit();
		try {
			List<AdsPerson> adsPersons = new ArrayList<AdsPerson>();
			if (search.getUserName() != null && !search.getUserName().trim().isEmpty()) {
				AdsPerson adsPerson = this.getPeopleAdsService().getAdsPerson(search.getUserName());
				if (adsPerson != null) {
					if (ferpaRestricted(adsPerson)) {
						return persons;
					}
					adsPersons.add(adsPerson);
				}
			} else {
				adsPersons = this.getPeopleAdsService().getAdsPersons(search.getLastName(), search.getFirstName(), search.getStatus(), search.getLocation(), search.isExactLastName(), resultLimit);
			}
			this.filterAdsPersons(adsPersons);
			persons = this.convertAdsPersons(adsPersons);
		} catch (Exception e) {
			LOG.error("Could not find users: " + search.getLastName() + " " + search.getFirstName() + " " + search.getStatus() + " " + search.getLocation() + " " + search.isExactLastName(), e);
		}
		Collections.sort(persons, new PersonSort());
		if (persons.size() > resultLimit) {
			return persons.subList(0, resultLimit - 1);
		}
		return persons;
	}

	public Map<String, String> getStatusTypes() {
		Map<String, String> statusTypes = new LinkedHashMap<String, String>();
		statusTypes.put("Any", "Any Status");
		statusTypes.put("Student", "Student");
		statusTypes.put("Faculty", "Faculty");
		statusTypes.put("Employee", "Employee");
		return statusTypes;
	}

	@Override
	public Person getUserDetails(String userName) {
		AdsPerson adsPerson;
		try {
			adsPerson = adsService.getAdsPerson(userName);
			if (ferpaRestricted(adsPerson)) {
				return null;
			}
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return null;
		}
		Person p = new PersonImpl();
		if (convertAdsPerson(adsPerson, p)) {
			return p;
		} else {
			return null;
		}
	}

	@Override
	public BufferedImage generateObfuscatedImage(String text) {
		int width = 250;
		int height = 25;

		BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

		Graphics2D g2d = bufferedImage.createGraphics();
		Font font = new Font("Arial", Font.PLAIN, 14);
		g2d.setFont(font);

		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		rh.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

		g2d.setRenderingHints(rh);

		Paint bg = new Color(255, 255, 255);
		g2d.setPaint(bg);
		g2d.fillRect(0, 0, width, height);

		int x = 0;
		int y = height - 7;

		Paint textPaint = new Color(0, 0, 0);
		g2d.setPaint(textPaint);
		g2d.drawString(text, x, y);

		g2d.dispose();
		return bufferedImage;
	}

	private void filterAdsPersons(List<AdsPerson> adsPersons) {
		AdsPerson adsPerson = null;
		boolean remove;
		for (Iterator<AdsPerson> i = adsPersons.iterator(); i.hasNext();) {
			remove = false;
			adsPerson = i.next();
			remove = ferpaRestricted(adsPerson);
			if (remove) {
				i.remove();
			}
		}
	}

	private boolean ferpaRestricted(AdsPerson adsPerson) {
		int ferpa = adsPerson.getIuEduFERPAMask();
		if (ferpa > 0 && ((ferpa & 31) != 0 || (ferpa & 121) != 0)) {
			return true;
		}
		return false;
	}

	private List<Person> convertAdsPersons(List<AdsPerson> adsPersons) {
		List<Person> persons = new ArrayList<Person>();
		Person person = null;
		for (AdsPerson adsPerson : adsPersons) {
			person = new PersonImpl();
			if (this.convertAdsPerson(adsPerson, person)) {
				persons.add(person);
			}
		}
		return persons;
	}

	@SuppressWarnings("unchecked")
	private boolean convertAdsPerson(AdsPerson adsPerson, Person person) {
		person.setFirstName(adsPerson.getGivenName());
		person.setLastName(adsPerson.getSn());
		person.setEmail(adsPerson.getMail());
		person.setPhone(this.getStringAttribute(adsPerson, "telephoneNumber"));
		person.setAddress(this.getStringAttribute(adsPerson, "physicalDeliveryOfficeName"));
		person.setUserName(this.getStringAttribute(adsPerson, "cn"));
		person.setDisplayName(this.getStringAttribute(adsPerson, "displayName"));
		if (adsPerson.getAttribute("ou") != null && adsPerson.getAttribute("ou") instanceof ArrayList) {
			for (String ou : (ArrayList<String>) adsPerson.getAttribute("ou")) {
				person.getLocations().add(this.convertCampusCode(ou));
			}
		}
		if (adsPerson.getIuEduJobs() != null) {
			for (IUEduJob job : (List<IUEduJob>) adsPerson.getIuEduJobs()) {
				person.getDepartments().add(this.toProperCase(job.getDepartment()));
			}
		}
		if (adsPerson.getIuEduPersonAffiliation() != null) {
			StringBuffer b = new StringBuffer();
			for (String affiliation : (List<String>) adsPerson.getIuEduPersonAffiliation()) {
				b.append(affiliation + "-");
			}
			String s = b.toString().toLowerCase();
			if (s.indexOf("hourly") > -1 || s.indexOf("staff") > -1) {
				person.getAffiliations().add("Employee");
			}
			if (s.indexOf("faculty") > -1) {
				person.getAffiliations().add("Faculty");
			}
			if (s.indexOf("affiliate") > -1) {
				person.getAffiliations().add("Affiliate");
			}
			if ((s.indexOf("graduate") > -1 || s.indexOf("professional") > -1) && "Enrolled".equals(this.getStringAttribute(adsPerson, "iuEduPrimaryStudentAffiliation")) && "Y".equals(this.getStringAttribute(adsPerson, "iuEduCurrentlyEnrolled"))) {
				person.getAffiliations().add("Student");
			}
		}
		if (person.getAffiliations().size() < 1) {
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	private String getStringAttribute(AdsPerson adsPerson, String attributeName) {
		if (adsPerson.getAttribute(attributeName) != null && adsPerson.getAttribute(attributeName) instanceof ArrayList) {
			if (((ArrayList<String>) adsPerson.getAttribute(attributeName)).size() > 0) {
				return ((ArrayList<String>) adsPerson.getAttribute(attributeName)).get(0);
			}
		}
		return null;
	}

	private String convertCampusCode(String code) {
		String name = null;
		if (code != null) {
			if (Constants.CAMPUS_NAMES.containsKey(code.trim())) {
				name = Constants.CAMPUS_NAMES.get(code.trim());
			}
		}
		if (name == null) {
			name = new String(code);
		}
		return name;
	}

	private String toProperCase(String name) {
		if (name == null || name.trim().length() == 0) {
			return name;
		}

		name = name.toLowerCase();
		String[] split = name.split(" ");
		name = "";
		for (String s : split) {
			s = s.substring(0, 1).toUpperCase() + s.substring(1);
			name = name + s + " ";
		}
		return name.trim();
	}

	public PeopleAdsService getPeopleAdsService() {
		return adsService;
	}

	public void setPeopleAdsService(PeopleAdsService adsService) {
		this.adsService = adsService;
	}
}
