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
 
package org.kuali.mobility.mdot.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.kuali.mobility.configparams.service.ConfigParamService;
import org.kuali.mobility.shared.Constants;
import org.kuali.mobility.user.entity.User;
import org.springframework.context.ApplicationContext;

public class AccessFilter implements Filter {

	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain) throws IOException, ServletException {
		final HttpServletRequest hrequest = (HttpServletRequest) request;

		ApplicationContext ctx = org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(hrequest.getSession().getServletContext());
		ConfigParamService configParamService = (ConfigParamService) ctx.getBean("ConfigParamService");

		User user = (User) hrequest.getSession().getAttribute(Constants.KME_USER_KEY);
		String path = hrequest.getServletPath();

		if (path.startsWith("/admin") || path.startsWith("/publishing")) {
	    	if (!user.isMember(configParamService.findValueByName("Admin.Group.Name"))) {
				final HttpServletResponse hresponse = (HttpServletResponse) response;
				hresponse.sendError(401, "Admin/Publishing access is not allowed.");
				return;
	    	}
		}

		if (path.startsWith("/backdoor")) {
	    	if (!user.isMember(configParamService.findValueByName("Backdoor.Group.Name"))) {
				final HttpServletResponse hresponse = (HttpServletResponse) response;
				hresponse.sendError(401, "Backdoor access is not allowed.");
				return;
	    	}
		}

		chain.doFilter(request, response);
	}

	public void init(FilterConfig config) throws ServletException {}

	public void destroy() {}

}
