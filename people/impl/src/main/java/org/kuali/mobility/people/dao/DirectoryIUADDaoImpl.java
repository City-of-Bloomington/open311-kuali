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
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.naming.AuthenticationException;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.PartialResultException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.kuali.mobility.people.entity.DirectoryEntry;
import org.kuali.mobility.people.entity.Group;
import org.kuali.mobility.people.entity.Person;
import org.kuali.mobility.people.entity.PersonImpl;
import org.kuali.mobility.people.entity.SearchCriteria;
import org.kuali.mobility.shared.Constants;

import edu.iu.uis.sit.util.directory.AdsConnectionFactory;
import edu.iu.uis.sit.util.directory.AdsPerson;
import edu.iu.uis.sit.util.directory.IUEduCampusPersonAffiliation;
import edu.iu.uis.sit.util.directory.IUEduInstitution;
import edu.iu.uis.sit.util.directory.IUEduJob;

public class DirectoryIUADDaoImpl implements DirectoryDao {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(DirectoryIUADDaoImpl.class);

	private PeopleAdsService adsService;
	private String adsUsername;
	private String adsPassword;

	public DirectoryIUADDaoImpl(String adsUsername, String adsPassword) {
		adsService = new PeopleAdsService(adsUsername, adsPassword);
	}
 	
	public List<DirectoryEntry> findEntries(SearchCriteria search) {
		List<DirectoryEntry> de = new ArrayList<DirectoryEntry>();
			
		// Find the people
		if (search.getSearchText() == null || search.getSearchText().trim().isEmpty()) {
			for (Person p : findPeople(search)) {
				de.add(p);
			}
		} else {
			// Find a unique entry
			Person up = findUniquePerson(search);
			if (up != null) {
				de.add(up);
			}
			// Find the people
			for (Person p : findSimplePeople(search)) {
				de.add(p);
			}
			// Find the groups
			// TODO: Add this
		}

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

	public String getAdsUsername() {
		return adsUsername;
	}

	public void setAdsUsername(String adsUsername) {
		this.adsUsername = adsUsername;
	}

	public String getAdsPassword() {
		return adsPassword;
	}

	public void setAdsPassword(String adsPassword) {
		this.adsPassword = adsPassword;
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

	private Person findUniquePerson(SearchCriteria search) {
		List<Person> persons = null;

		try {
			List<AdsPerson> adsPersons = new ArrayList<AdsPerson>();
			if (search.getSearchText() != null && !search.getSearchText().trim().isEmpty()) {
				AdsPerson adsPerson = getPeopleAdsService().getAdsPerson(search.getSearchText());
				if (adsPerson != null) {
					if (ferpaRestricted(adsPerson)) {
						return null;
					}
					adsPersons.add(adsPerson);
				}
			}
			this.filterAdsPersons(adsPersons);
			persons = this.convertAdsPersons(adsPersons);
		} catch (Exception e) {
			LOG.warn("No unique result for search: " + search.getSearchText(), e);
		}
		
		if (persons.size() > 0) {
			return persons.get(0);
		} 
		
		return null;
	}

	private List<Person> findSimplePeople(SearchCriteria search) {
		List<Person> persons = null;
		int resultLimit = adsService.getResultLimit();
		try {
			List<AdsPerson> adsPersons = new ArrayList<AdsPerson>();
			adsPersons = getPeopleAdsService().getAdsPersons(search.getSearchText(), null, "Any", "Any", false, resultLimit, false);
			this.filterAdsPersons(adsPersons);
			persons = this.convertAdsPersons(adsPersons);
		} catch (Exception e) {
			LOG.error("Could not find users: " + search.getSearchText(), e);
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

		private String adsUsername;
		private String adsPassword;

		private static AddressBookAdsHelper adsHelper;
		private int defaultResultLimit;

		public PeopleAdsService(String adsUsername, String adsPassword) {
			this.defaultResultLimit = 75;
			this.adsUsername = adsUsername;
			this.adsPassword = adsPassword;
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
			return getAdsPersons(last, first, status, campus, isExactLastName, resultLimit, false);
		}
		
		public List<AdsPerson> getAdsPersons(String last, String first, String status, String campus, boolean isExactLastName, int resultLimit, boolean useFullWidcard) throws Exception {
			String wild = "";
			if (useFullWidcard) {
				wild = "*";
			}
			
			AddressBookAdsHelper helper = getAdsHelper();
			String[] returnedAttributes = { "cn", "iuEduPersonAffiliation", "displayName", "ou", "iuEduPSEMPLID", "iuEduCurrentlyEnrolled", "iuEduPrimaryStudentAffiliation", "iuEduFERPAMask", "givenName", "sn" };
			Map<String, String> keyValues = new HashMap<String, String>();
			if (last != null && last.length() > 0) {
				if (isExactLastName) {
					keyValues.put("sn", last);
				} else {
					keyValues.put("sn", wild + last + "*");
				}
			}
			if (first != null && first.length() > 0) {
				keyValues.put("givenName", wild + first + "*");
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
	
	private class PersonSort implements Comparator<Person> {

		private final String EMPTY_STRING = "";
		
		public int compare(Person o1, Person o2) {
		    int comparison = this.compareString(o1.getLastName(), o2.getLastName());
		    if (comparison == 0) {
		    	comparison = this.compareString(o1.getFirstName(), o2.getFirstName());
		    	if (comparison == 0) {
		    		comparison = this.compareString(o1.getDisplayName(), o2.getDisplayName());
			    	if (comparison == 0) {
			    		comparison = this.compareString(o1.getUserName(), o2.getUserName());
			    	}
		    	}
		    }
		    return comparison;
		}
		
		private int compareString(String s1, String s2) {
			if (s1 == null) {
				s1 = EMPTY_STRING;
			}
			if (s2 == null) {
				s2 = EMPTY_STRING;
			}
			return s1.compareTo(s2);
		}
	}

	private static class AddressBookAdsHelper {
		
		private org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AddressBookAdsHelper.class.getName());
		
		private String username = "";

		private String password = "";

		private int port = 0;

		public AddressBookAdsHelper(String username, String password) {
			setUsername(username);
			setPassword(password);
		}

		public AddressBookAdsHelper(String username, String password, int port) {
			setUsername(username);
			setPassword(password);
			setPort(port);
		}
		
		public List<AdsPerson> getAdsPersonsReturnedAttributes(Map<String, String> keyValues, String[] returnedAttributes, int resultLimit) throws AuthenticationException, NamingException {
			return getAdsPersons(keyValues, false, 0, returnedAttributes, resultLimit);
		}
		
		private List<AdsPerson> getAdsPersons(Map<String, String> keyValues, boolean retrieveGroups, int maximumGroupDepth, String[] returnedAttributes, int resultLimit) throws AuthenticationException, NamingException {
			DirContext ctx = AdsConnectionFactory.getDirContext(username, password, port);
			List<AdsPerson> adsPersons = new ArrayList<AdsPerson>();
			try {
				String[] attributesToGet = null;
				if (returnedAttributes != null && returnedAttributes.length > 0) {
					attributesToGet = returnedAttributes;
				} else {
					attributesToGet = new String[18];
					attributesToGet[0] = "mail";
					attributesToGet[1] = "sn";
					attributesToGet[2] = "givenName";
					attributesToGet[3] = "displayName";
					attributesToGet[4] = "department";
					attributesToGet[5] = "eduPersonAffiliation";
					attributesToGet[6] = "eduPersonNickname";
					attributesToGet[7] = "iuEduPSEMPLID";
					attributesToGet[8] = "iuEduCardID";
					attributesToGet[9] = "iuEduUUID";
					attributesToGet[10] = "ou";
					attributesToGet[11] = "sAMAccountName";
					attributesToGet[12] = "iuEduPersonAffiliation";
					attributesToGet[13] = "iuEduPrimaryStudentAffiliation";
					attributesToGet[14] = "iuEduJobs";
					attributesToGet[15] = "iuEduInstitutions";
					attributesToGet[16] = "iuEduPersonCampusAffiliation";
					attributesToGet[17] = "iuEduFERPAMask";
				}
				if (keyValues != null && keyValues.size() == 1 && keyValues.get(edu.iu.uis.sit.util.directory.Constants.SAM_ACCOUNT_NAME) != null) {
					Attributes userAttributes = ctx.getAttributes("cn=" + keyValues.get(edu.iu.uis.sit.util.directory.Constants.SAM_ACCOUNT_NAME) + ",ou=Accounts,dc=" + edu.iu.uis.sit.util.directory.Constants.AD_HOST_SHORT_NAME + ",dc=iu,dc=edu", attributesToGet);
					if (userAttributes != null) {
						AdsPerson adsPerson = new AdsPerson();
						adsPerson.copyAllAttributes(userAttributes);
						populate(adsPerson);
						adsPersons.add(adsPerson);
					}
				} else {
					SearchControls ctls = new SearchControls();
					ctls.setSearchScope(SearchControls.ONELEVEL_SCOPE);
					ctls.setReturningAttributes(attributesToGet);
					String filter = "(&(objectClass=user)";
					for (Iterator<Map.Entry<String, String>> iterator = keyValues.entrySet().iterator(); iterator.hasNext();) {
						Map.Entry<String, String> entry = iterator.next();
						filter += edu.iu.uis.sit.util.directory.Constants.LEFT_PAREN + entry.getKey() + edu.iu.uis.sit.util.directory.Constants.EQUAL + entry.getValue() + edu.iu.uis.sit.util.directory.Constants.RIGHT_PAREN;
					}
					filter += edu.iu.uis.sit.util.directory.Constants.RIGHT_PAREN;
					int count = 0;
					try {
						NamingEnumeration<SearchResult> answer = ctx.search("ou=Accounts,dc=" + edu.iu.uis.sit.util.directory.Constants.AD_HOST_SHORT_NAME + ",dc=iu,dc=edu", filter, ctls);
						if (answer != null && answer.hasMore()) {
							while (answer.hasMore() && count < resultLimit) {
								count++;
								AdsPerson adsPerson = null;
								SearchResult sr = answer.next();
								Attributes userAttributes = sr.getAttributes();
								if (userAttributes != null) {
									adsPerson = new AdsPerson();
									adsPerson.copyAllAttributes(userAttributes);
									populate(adsPerson);
									adsPersons.add(adsPerson);
								}
							}
						}
					} catch (PartialResultException pre) {
						LOG.info("PartialResultException at count: " + count);
					} catch (NamingException e) {
						LOG.info("NamingException at count: " + count);
						throw e;
					}
				}
			} finally {
				ctx.close();
				
			}

			return adsPersons;
		}
		
		@SuppressWarnings("unchecked")
		private void populate(AdsPerson adsPerson) {
			adsPerson.setDisplayName(convertSingleValue(adsPerson.getAttribute("displayName")));
			adsPerson.setEduPersonAffiliation(convertMultiValue(adsPerson.getAttribute("eduPersonAffiliation")));
			adsPerson.setEduPersonNickname(convertSingleValue(adsPerson.getAttribute("eduPersonNickname")));
			adsPerson.setGivenName(convertSingleValue(adsPerson.getAttribute("givenName")));
			adsPerson.setIuEduPSEMPLID(convertSingleValue(adsPerson.getAttribute("iuEduPSEMPLID")));
			adsPerson.setIuEduCardID(convertMultiValue(adsPerson.getAttribute("iuEduCardID")));
			adsPerson.setIuEduUUID(convertSingleValue(adsPerson.getAttribute("iuEduUUID")));
			adsPerson.setMail(convertSingleValue(adsPerson.getAttribute("mail")));
			adsPerson.setOu(convertSingleValue(adsPerson.getAttribute("ou")));
			adsPerson.setSn(convertSingleValue(adsPerson.getAttribute("sn")));
			adsPerson.setUid(convertSingleValue(adsPerson.getAttribute("sAMAccountName")));
			adsPerson.setIuEduPersonAffiliation(convertMultiValue(adsPerson.getAttribute("iuEduPersonAffiliation")));
			adsPerson.setIuEduPrimaryStudentAffiliation(convertSingleValue(adsPerson.getAttribute("iuEduPrimaryStudentAffiliation")));

			List<IUEduCampusPersonAffiliation> iuEduCampusPersonAffiliations = new ArrayList<IUEduCampusPersonAffiliation>();
			if (adsPerson.getAttribute("iuEduPersonCampusAffiliation") != null) {
				for (Iterator<String> iterator2 = convertMultiValue(adsPerson.getAttribute("iuEduPersonCampusAffiliation")).iterator(); iterator2.hasNext();) {
					String campusPersonAffiliation = (String) iterator2.next();
					String[] campusPerson = campusPersonAffiliation.split(":");
					iuEduCampusPersonAffiliations.add(new IUEduCampusPersonAffiliation(campusPerson[0], campusPerson[1]));
				}
			}
			adsPerson.setIuEduCampusPersonAffiliations(iuEduCampusPersonAffiliations);

			List<IUEduInstitution> iuEduInstitutions = new ArrayList<IUEduInstitution>();
			if (adsPerson.getAttribute("iuEduInstitutions") != null) {
				for (Iterator<String> iterator2 = convertMultiValue(adsPerson.getAttribute("iuEduInstitutions")).iterator(); iterator2.hasNext();) {
					String institution = iterator2.next();
					iuEduInstitutions.add(new IUEduInstitution(institution));
				}
			}
			adsPerson.setIuEduInstitutions(iuEduInstitutions);

			List<IUEduJob> iuEduJobs = new ArrayList<IUEduJob>();
			if (adsPerson.getAttribute("iuEduJobs") != null) {
				for (Iterator<String> iterator2 = convertMultiValue(adsPerson.getAttribute("iuEduJobs")).iterator(); iterator2.hasNext();) {
					String eduJob = iterator2.next();
					IUEduJob iuEduJob = new IUEduJob(eduJob);
					if (iuEduJob.isPrimary()) {
						adsPerson.setIuEduEmployeePrimaryAppointmentType(iuEduJob.getAppointmentType());
						adsPerson.setIuEduEmployeePrimaryChart(iuEduJob.getChart());
						adsPerson.setIuEduEmployeePrimaryOrg(iuEduJob.getOrg());
					}
					iuEduJobs.add(iuEduJob);
				}
			}
			adsPerson.setIuEduJobs(iuEduJobs);

			Integer ferpa = convertSingleValueInt(adsPerson.getAttribute("iuEduFERPAMask"));
			if (ferpa != null) {
				adsPerson.setIuEduFERPAMask(ferpa.intValue());
			}
		}
		
		private static Integer convertSingleValueInt(Object attribute) {
			if (attribute != null && attribute instanceof List && ((List) attribute).size() == 1) {
				return new Integer((String) (((List) attribute).get(0)));
			}
			return null;
		}

		private static String convertSingleValue(Object attribute) {
			if (attribute != null && attribute instanceof List && ((List) attribute).size() == 1) {
				return (String) ((List) attribute).get(0);
			}
			return null;
		}

		private static List convertMultiValue(Object attribute) {
			if (attribute != null && attribute instanceof List) {
				return (List) attribute;
			}
			return null;
		}
		
		// standard attribute getters and setters

		private void setPassword(String password) {
			this.password = password;
		}

		private void setUsername(String username) {
			this.username = username;
		}

		private void setPort(int port) {
			this.port = port;
		}
		
	}

}
