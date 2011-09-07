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

import java.util.List;

import org.kuali.mobility.classifieds.entity.Ad;
import org.kuali.mobility.classifieds.entity.Category;

public interface ClassifiedsService {

	public List<Category> getCategories(String userId, String campus) throws Exception;

	public List<Ad> getAdsByCategoryId(String userId, String campus, Long categoryId) throws Exception;

	public Ad getAdById(String userId, String campus, Long adId) throws Exception;

	public List<Ad> getSearchResults(String userId, String campus, String search) throws Exception;

	public void saveCampus(String userId, String campus) throws Exception;

	public List<Ad> getUsersAds(String userId) throws Exception;

	public Ad getAd(String userId, Long adId) throws Exception;

	public Ad saveAd(String userId, Ad ad) throws Exception;

	public String getPolicy(String userId) throws Exception;

	public List<Ad> getWatchList(String userId) throws Exception;
}
