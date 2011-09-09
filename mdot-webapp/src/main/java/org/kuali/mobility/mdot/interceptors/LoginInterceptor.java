package org.kuali.mobility.mdot.interceptors;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.kuali.mobility.user.entity.UserImpl;
import org.kuali.mobility.user.entity.UserPreference;
import org.kuali.mobility.user.service.UserService;
import org.kuali.mobility.util.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import edu.iu.m.auth.AdsService;
import edu.iu.uis.cas.filter.CASFilter;
import edu.iu.uis.sit.util.directory.AdsPerson;

public class LoginInterceptor implements HandlerInterceptor {

	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LoginInterceptor.class);

	@Autowired
	private AdsService adsService;

	public void setAdsService(AdsService adsService) {
		this.adsService = adsService;
	}

	@Autowired
	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (HttpUtil.needsAuthenticated(request.getServletPath()) || "yes".equals(request.getParameter("login"))) {
			login(request);
		} else {
			publicLogin(request);
		}
		User user = (User) request.getSession(true).getAttribute(Constants.KME_USER_KEY);

		if (user != null && user.getUserAttribute("acked") == null && (HttpUtil.needsAuthenticated(request.getServletPath()) || "yes".equals(request.getParameter("login")))) {
			try {
				user.setUserAttribute("service", request.getServletPath());
				response.sendRedirect(request.getContextPath() + "/mobileCasAck");
			} catch (IOException e) {
				LOG.error(e.getMessage(), e);
			}
		}

		return true;
	}

	private void publicLogin(HttpServletRequest request) {
		User user = (User) request.getSession(true).getAttribute(Constants.KME_USER_KEY);
		if (user == null) {
			user = new UserImpl(true);
			user.setPrincipalName("public_" + Math.random());
			request.getSession().setAttribute(Constants.KME_USER_KEY, user);
		}
	}

	@SuppressWarnings("unchecked")
	private User login(HttpServletRequest request) {
		User user = (User) request.getSession(true).getAttribute(Constants.KME_USER_KEY);
		if (user == null || user.isPublicUser()) {
			Timestamp now = new Timestamp(new Date().getTime());

			user = userService.findUserByPrincipalName(CASFilter.getRemoteUser(request));
			if (user == null) {
				user = new UserImpl(false);
				user.setFirstLogin(now);
			}
			user.setPrincipalName(CASFilter.getRemoteUser(request));
			user.setIpAddress(request.getHeader("X-CLUSTER-CLIENT-IP"));
			user.setLastLogin(now);
			userService.saveUser(user);

			List<UserPreference> prefs = userService.findAllUserPreferencesByPrincipalId(user.getPrincipalId());
			for (UserPreference pref : prefs) {
				try {
					user.setUserAttribute(pref.getPreferenceName(), pref.getPreferenceValues().get(0).getValue());
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}

			try {
				AdsPerson adsPerson = adsService.getAdsPerson(user.getPrincipalName());
				List<String> affiliations = adsPerson.getIuEduPersonAffiliation();
				if (affiliations != null) {
					user.setAffiliations(affiliations);
				}
				List<String> groups = adsPerson.getGroups();
				if (affiliations != null) {
					user.setGroups(groups);
				}
				user.setPrimaryCampus(adsPerson.getOu());
				user.setViewCampus(adsPerson.getOu());
				user.setEmail(adsPerson.getMail());
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}

			request.getSession().setAttribute(Constants.KME_USER_KEY, user);
			LOG.info("User id: " + user.getPrincipalName() + " logging in.");
		}
		return user;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView e) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e) throws Exception {
	}

}
