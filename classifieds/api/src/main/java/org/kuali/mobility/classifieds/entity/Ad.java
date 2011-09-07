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
package org.kuali.mobility.classifieds.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Ad {

	private Long adId;

	private String title;

	private String personId;

	private String campus;

	private String contact;

	private String email;

	private String ipAddress;

	private String price;

	private String postDate;

	private String description;

	private String active;

	private Long lockingNumber;

	private String expires;

	private Long categoryId;

	private String categoryName;

	private Map<Long, String> categories;

	private Map<Long, String> expireDays;

	private Map<String, List<String>> errors;

	private int responseCode;

	public Map<Long, String> getCategories() {
		Map<Long, String> linkedMap = new LinkedHashMap<Long, String>();
		if (categories != null) {
			List<String> valueList = new ArrayList<String>(categories.values());
			Collections.sort(valueList);
			for (String value : valueList) {
				for (Iterator iterator = categories.entrySet().iterator(); iterator.hasNext();) {
					Map.Entry<Long, String> entry = (Map.Entry<Long, String>) iterator.next();
					if (entry.getValue().equals(value)) {
						linkedMap.put(entry.getKey(), value);
						break;
					}
				}
			}
		}
		return linkedMap;
	}

	public void setCategories(Map<Long, String> categories) {
		this.categories = categories;
	}

	public Map<Long, String> getExpireDays() {
		Map<Long, String> linkedMap = new LinkedHashMap<Long, String>();
		if (expireDays != null) {
			List<Long> keyList = new ArrayList<Long>(expireDays.keySet());
			Collections.sort(keyList);
			for (Long key : keyList) {
				linkedMap.put(key, expireDays.get(key));
			}
		}
		return linkedMap;
	}

	public void setExpireDays(Map<Long, String> expireDays) {
		this.expireDays = expireDays;
	}

	public Long getAdId() {
		return adId;
	}

	public void setAdId(Long adId) {
		this.adId = adId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getCampus() {
		return campus;
	}

	public void setCampus(String campus) {
		this.campus = campus;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getPostDate() {
		return postDate;
	}

	public void setPostDate(String postDate) {
		this.postDate = postDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getActive() {
		return active;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public Long getLockingNumber() {
		return lockingNumber;
	}

	public void setLockingNumber(Long lockingNumber) {
		this.lockingNumber = lockingNumber;
	}

	public String getExpires() {
		return expires;
	}

	public void setExpires(String expires) {
		this.expires = expires;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public Map<String, List<String>> getErrors() {
		return errors;
	}

	public void setErrors(Map<String, List<String>> errors) {
		this.errors = errors;
	}

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}
}
