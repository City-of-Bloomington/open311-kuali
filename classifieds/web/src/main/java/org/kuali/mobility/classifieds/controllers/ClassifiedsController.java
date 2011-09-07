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

package org.kuali.mobility.classifieds.controllers;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.campus.service.CampusService;
import org.kuali.mobility.classifieds.entity.Ad;
import org.kuali.mobility.classifieds.entity.Search;
import org.kuali.mobility.classifieds.service.ClassifiedsService;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/classifieds")
public class ClassifiedsController {
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ClassifiedsController.class);
	@Autowired
	private ClassifiedsService classifiedsService;

	@Autowired
	private CampusService campusService;

	@RequestMapping(method = RequestMethod.GET)
	public String index(HttpServletRequest request, Model uiModel, @RequestParam(required = false) String selectedTab) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		String campus = null;
		if (user.getViewCampus() == null) {
			return "redirect:/campus?toolName=classifieds";
		} else {
			campus = user.getViewCampus();
		}
		try {
			uiModel.addAttribute("search", new Search());
			uiModel.addAttribute("campus", campus);
			if (selectedTab != null) {
				uiModel.addAttribute("selectedTab", selectedTab);
			}
			uiModel.addAttribute("categories", classifiedsService.getCategories(user.getPrincipalName(), campus));
		} catch (Exception e) {
			LOG.error("Error in getting categories.", e);
		}
		return "classifieds/index";
	}

	@RequestMapping(value = "/ads", method = RequestMethod.GET)
	public String ads(HttpServletRequest request, Model uiModel, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) String searched) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("campus", user.getViewCampus());
			List<Ad> ads = null;
			if (searched != null && !"".equals(searched.trim())) {
				ads = classifiedsService.getSearchResults(user.getPrincipalName(), user.getViewCampus(), searched);
			} else {
				ads = classifiedsService.getAdsByCategoryId(user.getPrincipalName(), user.getViewCampus(), categoryId);
			}
			ads = setupPages(request, uiModel, ads);
			uiModel.addAttribute("searched", searched);
			uiModel.addAttribute("categoryId", categoryId);
			uiModel.addAttribute("ads", ads);
		} catch (Exception e) {
			LOG.error("Error in getting ads.", e);
		}
		return "classifieds/ads";
	}

	@RequestMapping(value = "/myAds", method = RequestMethod.GET)
	public String myAds(HttpServletRequest request, Model uiModel) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("campus", user.getViewCampus());
			uiModel.addAttribute("campuses", campusService.getCampuses());
			List<Ad> ads = classifiedsService.getUsersAds(user.getPrincipalName());
			uiModel.addAttribute("ads", ads);
		} catch (Exception e) {
			LOG.error("Error in getting ads.", e);
		}
		return "classifieds/myAds";
	}

	@RequestMapping(value = "/maintainAd", method = RequestMethod.GET)
	public String myAd(HttpServletRequest request, Model uiModel, @RequestParam(required = false) Long adId) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("campus", user.getViewCampus());
			uiModel.addAttribute("campuses", campusService.getCampuses());
			Ad ad = classifiedsService.getAd(user.getPrincipalName(), adId);
			if (adId == null) {
				ad.setCampus(user.getViewCampus());
			}
			uiModel.addAttribute("ad", ad);
		} catch (Exception e) {
			LOG.error("Error in getting ads.", e);
		}
		return "classifieds/ad";
	}

	@RequestMapping(value = "/saveAd", method = RequestMethod.POST)
	public String saveAd(HttpServletRequest request, Model uiModel, @ModelAttribute("ad") Ad ad, BindingResult result) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			Ad returnedAd = classifiedsService.saveAd(user.getPrincipalName(), ad);
			if (returnedAd.getResponseCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
				Errors errors = ((Errors) result);
				for (Iterator iterator = returnedAd.getErrors().entrySet().iterator(); iterator.hasNext();) {
					Map.Entry<String, List<String>> entry = (Map.Entry<String, List<String>>) iterator.next();
					for (String error : entry.getValue()) {
						errors.rejectValue(entry.getKey(), "", error);
					}
				}
				ad.setCategories(returnedAd.getCategories());
				ad.setExpireDays(returnedAd.getExpireDays());
				uiModel.addAttribute("campuses", campusService.getCampuses());
				return "classifieds/ad";
			}
		} catch (Exception e) {
			LOG.error("error saving campus", e);
		}
		return "redirect:/classifieds/myAds";
	}

	@RequestMapping(value = "/ad", method = RequestMethod.GET)
	public String ad(HttpServletRequest request, Model uiModel, @RequestParam(required = true) Long adId, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) String searched, @RequestParam(required = false) Long pageNumber) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("pageNumber", pageNumber);
			uiModel.addAttribute("categoryId", categoryId);
			uiModel.addAttribute("searched", searched);
			uiModel.addAttribute("ad", classifiedsService.getAdById(user.getPrincipalName(), user.getViewCampus(), adId));
		} catch (Exception e) {
			LOG.error("Error in getting ads.", e);
		}
		return "classifieds/viewAd";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(HttpServletRequest request, Model uiModel, @ModelAttribute("search") Search search, BindingResult result, SessionStatus status) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			Errors errors = ((Errors) result);
			if (search.getText() == null || "".equals(search.getText().trim())) {
				errors.rejectValue("text", "", "Please enter search criteria.");
			} else if (search.getText().length() > 40) {
				errors.rejectValue("text", "", "Please search using 40 characters or less.");
			} else if (search.getText().length() < 3) {
				errors.rejectValue("text", "", "Please search using three characters or more.");
			}
			if (errors.hasErrors()) {
				uiModel.addAttribute("campus", user.getViewCampus());
				uiModel.addAttribute("selectedTab", "tab2");
				return "classifieds/index";
			}
			List<Ad> ads = classifiedsService.getSearchResults(user.getPrincipalName(), user.getViewCampus(), search.getText());
			ads = setupPages(request, uiModel, ads);
			uiModel.addAttribute("searched", search.getText());
			uiModel.addAttribute("selectedTab", "tab2");
			uiModel.addAttribute("ads", ads);
		} catch (Exception e) {
			LOG.error("Error in getting ads.", e);
		}
		return "classifieds/ads";
	}

	@RequestMapping(value = "/campus", method = RequestMethod.GET)
	public String campus(HttpServletRequest request, Model uiModel) {
		return "redirect:/campus?toolName=/classifieds/saveCampus";
	}

	@RequestMapping(value = "/saveCampus", method = RequestMethod.GET)
	public String saveCampus(HttpServletRequest request, Model uiModel) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			classifiedsService.saveCampus(user.getPrincipalName(), user.getViewCampus());
		} catch (Exception e) {
			LOG.error("error saving campus", e);
		}
		return "redirect:/classifieds/ads";
	}

	@RequestMapping(value = "/options", method = RequestMethod.GET)
	public String options(HttpServletRequest request, Model uiModel) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("campus", user.getViewCampus());
		} catch (Exception e) {
			LOG.error("Error in getting ads.", e);
		}
		return "classifieds/options";
	}

	@RequestMapping(value = "/adWatchList", method = RequestMethod.GET)
	public String adWatchList(HttpServletRequest request, Model uiModel) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("campus", user.getViewCampus());
			uiModel.addAttribute("adWatchList", classifiedsService.getWatchList(user.getPrincipalName()));
		} catch (Exception e) {
			LOG.error("Error in getting ads.", e);
		}
		return "classifieds/adWatchList";
	}
	
	
	@RequestMapping(value = "/policy", method = RequestMethod.GET)
	public String policy(HttpServletRequest request, Model uiModel) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("campus", user.getViewCampus());
			uiModel.addAttribute("policy", classifiedsService.getPolicy(user.getPrincipalName()));
		} catch (Exception e) {
			LOG.error("Error in getting ads.", e);
		}
		return "classifieds/policy";
	}

	private List<Ad> setupPages(HttpServletRequest request, Model uiModel, List<Ad> ads) {
		if (ads != null && !ads.isEmpty()) {
			Integer maxPageNumber = ads.size() / 30;
			if (ads.size() % 30 != 0) {
				maxPageNumber += 1;
			}
			uiModel.addAttribute("maxPageNumber", maxPageNumber);
			String page = request.getParameter("pageNumber");
			if (page != null) {
				try {
					int pageNumber = Integer.parseInt(page);
					uiModel.addAttribute("pageNumber", pageNumber);
					int adsNumber = (pageNumber - 1) * 30;
					if (ads.size() > adsNumber) {
						if (ads.size() > adsNumber + 30) {
							ads = ads.subList(adsNumber, adsNumber + 30);
							uiModel.addAttribute("nextPage", pageNumber + 1);
							if (pageNumber - 1 > 0) {
								uiModel.addAttribute("previousPage", pageNumber - 1);
							}
						} else {
							ads = ads.subList(adsNumber, ads.size());
							if (pageNumber - 1 > 0) {
								uiModel.addAttribute("previousPage", pageNumber - 1);
							}
						}
					}
				} catch (Exception e) {
					if (ads.size() > 30) {
						ads = ads.subList(0, 30);
						uiModel.addAttribute("nextPage", 2);
					}
				}
			} else {
				if (ads.size() > 30) {
					ads = ads.subList(0, 30);
					uiModel.addAttribute("nextPage", 2);
				}
			}
		}
		return ads;
	}
}