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
 
package org.kuali.mobility.people.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.kuali.mobility.people.entity.DirectoryEntry;
import org.kuali.mobility.people.entity.Group;
import org.kuali.mobility.people.entity.Person;
import org.kuali.mobility.people.entity.PersonImpl;
import org.kuali.mobility.people.entity.SearchCriteria;
import org.kuali.mobility.people.service.AddressBookAdsHelper;
import org.kuali.mobility.people.service.PersonSort;
import org.kuali.mobility.shared.Constants;
import org.springframework.stereotype.Repository;

import edu.iu.uis.sit.util.directory.AdsPerson;
import edu.iu.uis.sit.util.directory.IUEduJob;

public class DirectoryIUADDaoImpl implements DirectoryDao {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DirectoryIUADDaoImpl.class);

	private PeopleAdsService adsService;

	public DirectoryIUADDaoImpl() {
		adsService = new PeopleAdsService();
	}
 	
	public List<DirectoryEntry> findEntries(SearchCriteria search) {
		List<DirectoryEntry> de = new ArrayList<DirectoryEntry>();
		
		// Find a unique entry
		
		// Find the people
		for (Person p : findPeople(search)) {
			de.add(p);
		}
		
		// Find the groups
	
		return de;
	}

	public Person lookupPerson(String personId) {
		AdsPerson adsPerson;
		try {
			adsPerson = adsService.getAdsPerson(personId);
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

	public Group lookupGroup(String groupId) {
		throw new UnsupportedOperationException();
	}

	
	private PeopleAdsService getPeopleAdsService() {
		return adsService;
	}

	private List<Person> findPeople(SearchCriteria search) {
		List<Person> persons = null;
		int resultLimit = adsService.getResultLimit();
		try {
			List<AdsPerson> adsPersons = new ArrayList<AdsPerson>();
			if (search.getUserName() != null && !search.getUserName().trim().isEmpty()) {
				AdsPerson adsPerson = getPeopleAdsService().getAdsPerson(search.getUserName());
				if (adsPerson != null) {
					if (ferpaRestricted(adsPerson)) {
						return persons;
					}
					adsPersons.add(adsPerson);
				}
			} else {
				adsPersons = getPeopleAdsService().getAdsPersons(search.getLastName(), search.getFirstName(), search.getStatus(), search.getLocation(), search.isExactLastName(), resultLimit);
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

	private static class PeopleAdsService {

		private String adsUsername = "";
		private String adsPassword = "";

		private static AddressBookAdsHelper adsHelper;
		private int defaultResultLimit;

		public PeopleAdsService() {
			this.defaultResultLimit = 75;
		}

		private AddressBookAdsHelper getAdsHelper() {
			if (adsHelper == null) {
				try {
					AddressBookAdsHelper addressBookAdsHelper = new AddressBookAdsHelper(this.adsUsername, this.adsPassword);
					adsHelper = addressBookAdsHelper;
				} catch (Exception e) {
					LOG.error("error creating adsHelper: ", e);
				}
			}
			return adsHelper;
		}

		private int getCachedResultLimit() {
			/*
			Integer limit = new Integer(this.defaultResultLimit);
			try {
				String configParam = configParamService.findValueByName("PEOPLE_RESULT_LIMIT_ADS");
				if (configParam != null && !"".equals(configParam)) {
					limit = new Integer(configParam);
				}
			} catch (Exception e) {
				LOG.error("Configuration Parameter: PEOPLE_RESULT_LIMIT_ADS must exist and be a number. Using: " + limit, e);
			}
			return limit;
			*/
			return 75;
		}

		public AdsPerson getAdsPerson(String username) throws Exception {
			/*
			 * Any filtering done in getAdsPersons needs to be done here as well
			 */
			AddressBookAdsHelper helper = getAdsHelper();

			String[] returnedAttributes = { "cn", "givenName", "sn", "telephoneNumber", "iuEduPersonAffiliation", "mail", "ou", "physicalDeliveryOfficeName", "iuEduJobs", "iuEduCurrentlyEnrolled", "iuEduPrimaryStudentAffiliation", "iuEduFERPAMask" };
			Map<String, String> keyValues = new HashMap<String, String>();
			keyValues.put("cn", username);
			keyValues.put("msExchHideFromAddressLists", "FALSE");
			List<AdsPerson> adsPersons = helper.getAdsPersonsReturnedAttributes(keyValues, returnedAttributes, 1);
			AdsPerson adsPerson = null;
			if (adsPersons.size() > 0) {
				adsPerson = adsPersons.get(0);
			}

			return adsPerson;
		}

		public List<AdsPerson> getAdsPersons(String last, String first, String status, String campus, boolean isExactLastName, int resultLimit) throws Exception {
			AddressBookAdsHelper helper = getAdsHelper();
			String[] returnedAttributes = { "cn", "iuEduPersonAffiliation", "displayName", "ou", "iuEduPSEMPLID", "iuEduCurrentlyEnrolled", "iuEduPrimaryStudentAffiliation", "iuEduFERPAMask", "givenName", "sn" };
			Map<String, String> keyValues = new HashMap<String, String>();
			if (last != null && last.length() > 0) {
				if (isExactLastName) {
					keyValues.put("sn", last);
				} else {
					keyValues.put("sn", last + "*");
				}
			}
			if (first != null && first.length() > 0) {
				keyValues.put("givenName", first + "*");
			}
			if (campus != null && !campus.equals("Any")) {
				keyValues.put("ou", campus);
			}
			if (status != null && !status.equals("Any")) {
				if (status.equals("Faculty")) {
					keyValues.put("iuEduPersonAffiliation", "faculty");
				} else if (status.equals("Student")) {
					keyValues.put("|(iuEduPersonAffiliation=undergraduate)(iuEduPersonAffiliation=graduate)(iuEduPersonAffiliation", "professional)");
				} else if (status.equals("Employee")) {
					keyValues.put("|(iuEduPersonAffiliation=regular hourly)(iuEduPersonAffiliation=student hourly)(iuEduPersonAffiliation=retired staff)(iuEduPersonAffiliation", "staff)");
				}
			}
			keyValues.put("iuEduPSEMPLID", "*");
			keyValues.put("msExchHideFromAddressLists", "FALSE");

			return helper.getAdsPersonsReturnedAttributes(keyValues, returnedAttributes, resultLimit);
		}

		public int getResultLimit() {
			return this.getCachedResultLimit();
		}
		
	}
	
}
