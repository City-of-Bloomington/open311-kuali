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
 
package edu.iu.m.auth;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import edu.iu.uis.sit.util.directory.AdsHelper;
import edu.iu.uis.sit.util.directory.AdsPerson;

public class AdsServiceImpl implements AdsService {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(AdsServiceImpl.class);
	
	private String adsUsername;
	private String adsPassword;
	private static AdsHelper adsHelper;
	
	private AdsHelper getAdsHelper() {
		if (adsHelper == null) {
			try {
				AdsHelper helper = new AdsHelper(this.adsUsername, this.adsPassword);
				adsHelper = helper;
			} catch (Exception e) {
				LOG.error("error creating adsHelper: ", e);
			}
		}
		return adsHelper;
	}
	
	@SuppressWarnings("unchecked")
	public AdsPerson getAdsPerson(String username) throws Exception {
		AdsHelper helper = getAdsHelper();
		AdsPerson person = helper.getAdsPerson(username);
		
		List<String> groups = person.getGroups();
		if (groups.isEmpty()) {
			for (Iterator<String> iterator = helper.getGroups(username, AdsHelper.INFINITE_GROUP_DEPTH).iterator(); iterator.hasNext();) {
				String group = (String) iterator.next();
				groups.add(group);
			}
		}
		return person;
	}

	@SuppressWarnings("unchecked")
	public List<AdsPerson> getAdsPersons(String last, String first, String status, String campus, boolean isExactLastName, int resultLimit) throws Exception {
		AdsHelper helper = getAdsHelper();
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
				keyValues.put("|(iuEduPersonAffiliation=undergraduate)(iuEduPersonAffiliation=graduate)(iuEduPersonAffiliation","professional)");
			} else if (status.equals("Employee")) {
				keyValues.put("|(iuEduPersonAffiliation=regular hourly)(iuEduPersonAffiliation=student hourly)(iuEduPersonAffiliation=retired staff)(iuEduPersonAffiliation", "staff)");
			}
		}
		keyValues.put("iuEduPSEMPLID", "*");
		keyValues.put("msExchHideFromAddressLists", "FALSE");
				
		return helper.getAdsPersonsReturnedAttributes(keyValues, returnedAttributes);
	}

	public void setAdsUsername(String adsUsername) {
		this.adsUsername = adsUsername;
	}

	public void setAdsPassword(String adsPassword) {
		this.adsPassword = adsPassword;
	}
}
