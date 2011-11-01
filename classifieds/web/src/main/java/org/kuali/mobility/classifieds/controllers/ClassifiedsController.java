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

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.kuali.mobility.campus.service.CampusService;
import org.kuali.mobility.classifieds.entity.Ad;
import org.kuali.mobility.classifieds.entity.ClassifiedsPageException;
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
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("Error getting categories.", e);
			uiModel.addAttribute("messages", "Error getting categories");
			return "classifieds/message";
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
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("Error getting ads.", e);
			uiModel.addAttribute("messages", "Error getting ads");
			return "classifieds/message";
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
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("Error getting my ads.", e);
			uiModel.addAttribute("messages", "Error getting my ads");
			return "classifieds/message";
		}
		return "classifieds/myAds";
	}

	@RequestMapping(value = "/maintainAd", method = RequestMethod.GET)
	public String myAd(HttpServletRequest request, Model uiModel, @RequestParam(required = false) Long adId) {
		String policyViewed = (String) request.getSession().getAttribute("classifieds.policy.viewed");
		if (policyViewed != null && "true".equals(policyViewed)) {
			User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
			try {
				uiModel.addAttribute("campus", user.getViewCampus());
				uiModel.addAttribute("campuses", campusService.getCampuses());
				Ad ad = classifiedsService.getAd(user.getPrincipalName(), adId);
				if (adId == null) {
					ad.setCampus(user.getViewCampus());
				}
				uiModel.addAttribute("ad", ad);
			} catch (ClassifiedsPageException e) {
				uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
				return "classifieds/message";
			} catch (Exception e) {
				LOG.error("Error getting my ad.", e);
				uiModel.addAttribute("messages", "Error getting my ad");
				return "classifieds/message";
			}
			return "classifieds/ad";
		} else {
			return policy(request, uiModel, adId);
		}
	}

	@RequestMapping(value = "/saveAd", method = RequestMethod.POST)
	public String saveAd(HttpServletRequest request, Model uiModel, @ModelAttribute("ad") Ad ad, BindingResult result) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			ad.setIpAddress(user.getIpAddress());
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
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("error saving ad", e);
			uiModel.addAttribute("messages", "Error saving ad");
			return "classifieds/message";
		}
		return "redirect:/classifieds/myAds";
	}

	@RequestMapping(value = "/deleteAd", method = RequestMethod.GET)
	public String deleteAd(HttpServletRequest request, Model uiModel, @RequestParam(required = true) Long adId) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			Ad returnedAd = classifiedsService.deleteAd(user.getPrincipalName(), adId, user.getIpAddress());
			if (returnedAd.getResponseCode() == HttpStatus.UNPROCESSABLE_ENTITY.value()) {
				uiModel.addAttribute("campuses", campusService.getCampuses());
				List<String> errors = new ArrayList<String>();
				for (Iterator iterator = returnedAd.getErrors().entrySet().iterator(); iterator.hasNext();) {
					Map.Entry<String, List<String>> entry = (Map.Entry<String, List<String>>) iterator.next();
					for (String error : entry.getValue()) {
						errors.add(error);
					}
				}
				uiModel.addAttribute("messages", errors);
				return "classifieds/message";
			}
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("error deleting ad", e);
			uiModel.addAttribute("messages", "Error deleting ad");
			return "classifieds/message";
		}
		return "redirect:/classifieds/myAds";
	}

	@RequestMapping(value = "/watchAd", method = RequestMethod.GET)
	public String watchAd(HttpServletRequest request, Model uiModel, @RequestParam(required = true) Long adId, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) String searched, @RequestParam(required = false) Long pageNumber) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			classifiedsService.watchAd(user.getPrincipalName(), adId);
			uiModel.addAttribute("confirmation", "Added to your watch list.");
			uiModel.addAttribute("pageNumber", pageNumber);
			uiModel.addAttribute("categoryId", categoryId);
			uiModel.addAttribute("searched", searched);
			uiModel.addAttribute("ad", classifiedsService.getAdById(user.getPrincipalName(), user.getViewCampus(), adId));
			return "classifieds/viewAd";
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("error watching ad", e);
			uiModel.addAttribute("messages", "Error watching ad");
			return "classifieds/message";
		}
	}

	@RequestMapping(value = "/deleteWatchAd", method = RequestMethod.GET)
	public String deleteWatchAd(HttpServletRequest request, Model uiModel, @RequestParam(required = true) Long adId) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			classifiedsService.deleteWatchAd(user.getPrincipalName(), adId);
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("error removing watch from ad", e);
			uiModel.addAttribute("messages", "Error removing watch from ad");
			return "classifieds/message";
		}
		return "redirect:/classifieds/adWatchList";
	}

	@RequestMapping(value = "/ad", method = RequestMethod.GET)
	public String ad(HttpServletRequest request, Model uiModel, @RequestParam(required = true) Long adId, @RequestParam(required = false) Long categoryId, @RequestParam(required = false) String searched, @RequestParam(required = false) Long pageNumber, @RequestParam(required = false) String watch) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("pageNumber", pageNumber);
			uiModel.addAttribute("categoryId", categoryId);
			uiModel.addAttribute("searched", searched);

			if (watch != null) {
				uiModel.addAttribute("watch", watch);
			}
			uiModel.addAttribute("ad", classifiedsService.getAdById(user.getPrincipalName(), user.getViewCampus(), adId));
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("Error getting ad.", e);
			uiModel.addAttribute("messages", "Error getting ad");
			return "classifieds/message";
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
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("Error searching ads.", e);
			uiModel.addAttribute("messages", "Error searching ads");
			return "classifieds/message";
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
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("error saving campus", e);
			uiModel.addAttribute("messages", "Error saving campus");
			return "classifieds/message";
		}
		return "redirect:/classifieds/ads";
	}

	@RequestMapping(value = "/options", method = RequestMethod.GET)
	public String options(HttpServletRequest request, Model uiModel) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		uiModel.addAttribute("campus", user.getViewCampus());
		return "classifieds/options";
	}

	@RequestMapping(value = "/adWatchList", method = RequestMethod.GET)
	public String adWatchList(HttpServletRequest request, Model uiModel) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("campus", user.getViewCampus());
			uiModel.addAttribute("adWatchList", classifiedsService.getWatchList(user.getPrincipalName()));
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("Error adding watch to ad.", e);
			uiModel.addAttribute("messages", "Error adding watch to ad.");
			return "classifieds/message";
		}
		return "classifieds/adWatchList";
	}

	@RequestMapping(value = "/policy", method = RequestMethod.GET)
	public String policy(HttpServletRequest request, Model uiModel, @RequestParam(required = false) Long adId) {
		User user = (User) request.getSession().getAttribute(Constants.KME_USER_KEY);
		try {
			uiModel.addAttribute("campus", user.getViewCampus());
			uiModel.addAttribute("policy", classifiedsService.getPolicy(user.getPrincipalName()));
			request.getSession().setAttribute("classifieds.policy.viewed", "true");
			if (adId != null) {
				uiModel.addAttribute("adId", adId);
			}
		} catch (ClassifiedsPageException e) {
			uiModel.addAttribute("messages", e.getPageErrors().get("pageError"));
			return "classifieds/message";
		} catch (Exception e) {
			LOG.error("Error getting policy.", e);
			uiModel.addAttribute("messages", "Error getting policy.");
			return "classifieds/message";
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