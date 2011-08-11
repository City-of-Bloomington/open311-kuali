package org.kuali.mobility.socialmedia.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.kuali.mobility.socialmedia.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

public class SocialMediaListener implements ServletContextListener {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(SocialMediaListener.class);

	@Autowired
	private TwitterService twitterService;

	public void contextInitialized(final ServletContextEvent event) {
		ApplicationContext ctx = org.springframework.web.context.support.WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		twitterService = (TwitterService) ctx.getBean("twitterService");
		
		LOG.info("Starting the Twitter cache thread");
		twitterService.startCache();
		LOG.info("Twitter cache thread started");
	}

	public void contextDestroyed(final ServletContextEvent event) {
		if (twitterService != null) {
			LOG.info("Stopping the Twitter cache thread");
			twitterService.stopCache();
			LOG.info("Twitter cache thread should be completely dead");
		}
	}

	public TwitterService getTwitterService() {
		return twitterService;
	}

	public void setTwitterService(TwitterService twitterService) {
		this.twitterService = twitterService;
	}
}
