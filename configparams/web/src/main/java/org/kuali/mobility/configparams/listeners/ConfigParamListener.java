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

package org.kuali.mobility.configparams.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.kuali.mobility.configparams.service.ConfigParamService;
import org.springframework.context.ApplicationContext;

public class ConfigParamListener implements ServletContextListener {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ConfigParamListener.class);

	private ConfigParamService configParamService;

	public void contextInitialized(final ServletContextEvent event) {
		ApplicationContext ctx = org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		configParamService = (ConfigParamService) ctx.getBean("ConfigParamService");
		
		LOG.info("Starting the ConfigParam cache thread");
		configParamService.startCache();
		LOG.info("ConfigParam cache thread started");
	}

	public void contextDestroyed(final ServletContextEvent event) {
		if (configParamService != null) {
			LOG.info("Stopping the ConfigParam cache thread");
			configParamService.stopCache();
			LOG.info("ConfigParam cache thread should be completely dead");
		}
	}

	public ConfigParamService getConfigParamService() {
		return configParamService;
	}

	public void setConfigParamService(ConfigParamService configParamService) {
		this.configParamService = configParamService;
	}

}
