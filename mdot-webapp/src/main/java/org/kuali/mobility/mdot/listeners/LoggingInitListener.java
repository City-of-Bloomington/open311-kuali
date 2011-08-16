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

package org.kuali.mobility.mdot.listeners;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class LoggingInitListener implements ServletContextListener {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(LoggingInitListener.class);

	public void contextInitialized(ServletContextEvent event) {
		org.apache.log4j.PropertyConfigurator.configureAndWatch(event.getServletContext().getInitParameter("FullPathToLog4jProperties"), 5 * 60 * 1000);
		LOG.info("LoggingInitListener finished initializing log4j");
	}

	public void contextDestroyed(ServletContextEvent event) {
	}

}
