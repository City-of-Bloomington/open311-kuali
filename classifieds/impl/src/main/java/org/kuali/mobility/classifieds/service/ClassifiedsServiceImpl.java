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
package org.kuali.mobility.classifieds.service;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.kuali.mobility.classifieds.entity.Ad;
import org.kuali.mobility.classifieds.entity.Category;
import org.kuali.mobility.classifieds.entity.ClassifiedsPageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import edu.iu.es.espd.oauth.OAuth2LegService;
import edu.iu.es.espd.oauth.OAuthException;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Service
public class ClassifiedsServiceImpl implements ClassifiedsService {

	@Autowired
	private OAuth2LegService classifiedsOAuthService;

	private String classifiedsURL;

	public List<Category> getCategories(String userId, String campus) throws Exception {
		try {
			ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/categoryView/" + campus, "application/json");
			String json = IOUtils.toString(entity.getBody(), "UTF-8");

			List<Category> categories = new JSONDeserializer<List<Category>>().use(null, ArrayList.class).use("values", Category.class).deserialize(json);
			return categories;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public List<Ad> getAdsByCategoryId(String userId, String campus, Long categoryId) throws Exception {
		try {
			ResponseEntity<InputStream> entity = null;
			if (categoryId != null) {
				entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/category/" + categoryId, "application/json");
			} else {
				entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/ads", "application/json");
			}
			String json = IOUtils.toString(entity.getBody(), "UTF-8");
			List<Ad> ads = new JSONDeserializer<List<Ad>>().use(null, ArrayList.class).use("values", Ad.class).deserialize(json);
			return ads;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public List<Ad> getUsersAds(String userId) throws Exception {
		try {
			ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/usersAds", "application/json");
			String json = IOUtils.toString(entity.getBody(), "UTF-8");
			List<Ad> ads = new JSONDeserializer<List<Ad>>().use(null, ArrayList.class).use("values", Ad.class).deserialize(json);
			return ads;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public Ad getAd(String userId, Long adId) throws Exception {
		try {
			ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/ad" + (adId != null ? "/" + adId : ""), "application/json");
			String json = IOUtils.toString(entity.getBody(), "UTF-8");
			Ad ad = new JSONDeserializer<Ad>().deserialize(json, Ad.class);
			return ad;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public String getPolicy(String userId) throws Exception {
		try {
			ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/policy", "application/json");
			String json = IOUtils.toString(entity.getBody(), "UTF-8");
			return json;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public List<Ad> getWatchList(String userId) throws Exception {
		try {
			ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/watchAds", "application/json");
			String json = IOUtils.toString(entity.getBody(), "UTF-8");
			List<Ad> ads = new JSONDeserializer<List<Ad>>().use(null, ArrayList.class).use("values", Ad.class).deserialize(json);
			return ads;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public Ad saveAd(String userId, Ad ad) throws Exception {
		try {
			String sendJson = new JSONSerializer().exclude("class").deepSerialize(ad);
			ResponseEntity<InputStream> entity = null;
			if (ad.getAdId() != null && ad.getLockingNumber() != null) {
				entity = classifiedsOAuthService.oAuthPostRequest(userId, classifiedsURL + "/ad/" + ad.getAdId(), "application/json", sendJson);
			} else {
				entity = classifiedsOAuthService.oAuthPutRequest(userId, classifiedsURL + "/ad", "application/json", sendJson);
			}
			String json = IOUtils.toString(entity.getBody(), "UTF-8");

			int responseCode = entity.getStatusCode().value();
			Ad returnedAd = null;
			if (responseCode >= 200 && responseCode < 300) {
				returnedAd = new Ad();
			} else if (responseCode >= 400) {
				returnedAd = new JSONDeserializer<Ad>().deserialize(json, Ad.class);
			}
			returnedAd.setResponseCode(responseCode);
			return returnedAd;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public Ad deleteAd(String userId, Long adId, String ipAddress) throws Exception {
		try {
			Ad ad = new Ad();
			ad.setAdId(adId);
			ad.setIpAddress(ipAddress);
			String sendJson = new JSONSerializer().exclude("class").deepSerialize(ad);
			ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthPostRequest(userId, classifiedsURL + "/deactivateAd/" + ad.getAdId(), "application/json", sendJson);
			String json = IOUtils.toString(entity.getBody(), "UTF-8");

			int responseCode = entity.getStatusCode().value();
			Ad returnedAd = null;
			if (responseCode >= 200 && responseCode < 300) {
				returnedAd = new Ad();
			} else if (responseCode >= 400) {
				returnedAd = new JSONDeserializer<Ad>().deserialize(json, Ad.class);
			}
			returnedAd.setResponseCode(responseCode);
			return returnedAd;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public void watchAd(String userId, Long adId) throws Exception {
		try {
			classifiedsOAuthService.oAuthPostRequest(userId, classifiedsURL + "/watchAd/" + adId, "application/json", "");
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public void deleteWatchAd(String userId, Long adId) throws Exception {
		try {
			classifiedsOAuthService.oAuthDeleteRequest(userId, classifiedsURL + "/watchAd/" + adId, "application/json");
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public Ad getAdById(String userId, String campus, Long adId) throws Exception {
		try {
			ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthGetRequest(userId, classifiedsURL + "/adView/" + adId, "application/json");
			String json = IOUtils.toString(entity.getBody(), "UTF-8");

			Ad ad = new JSONDeserializer<Ad>().deserialize(json, Ad.class);
			return ad;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public List<Ad> getSearchResults(String userId, String campus, String search) throws Exception {
		try {
			ResponseEntity<InputStream> entity = classifiedsOAuthService.oAuthPostRequest(userId, classifiedsURL + "/search", "application/json", search);
			String json = IOUtils.toString(entity.getBody(), "UTF-8");
			List<Ad> ads = new JSONDeserializer<List<Ad>>().use(null, ArrayList.class).use("values", Ad.class).deserialize(json);
			return ads;
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public void saveCampus(String userId, String campus) throws Exception {
		try {
			classifiedsOAuthService.oAuthPostRequest(userId, classifiedsURL + "/saveCampus", "application/json", campus);
		} catch (OAuthException e) {
			int responseCode = e.getResponseCode();
			if (responseCode == HttpStatus.UNAUTHORIZED.value()) {
				String json = IOUtils.toString(e.getResponseBody(), "UTF-8");
				Map<String, List<String>> pageErrors = (Map<String, List<String>>) new JSONDeserializer().deserialize(json);
				throw new ClassifiedsPageException(pageErrors);
			}
			throw e;
		}
	}

	public String getClassifiedsURL() {
		return classifiedsURL;
	}

	public void setClassifiedsURL(String classifiedsURL) {
		this.classifiedsURL = classifiedsURL;
	}
}
