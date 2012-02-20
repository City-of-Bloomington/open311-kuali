package org.kuali.mobility.news.service;

import org.kuali.mobility.news.entity.NewsSource;

public class NewsInitBean {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(NewsInitBean.class);
	   
	private NewsService newsService;
	
	private static Thread backgroundThread = null;
	
	public void init() {
		backgroundThread = new Thread(new BackgroundThread());
    	backgroundThread.setDaemon(true);
    	backgroundThread.start();    	
	}

    public void cleanup() {
    	LOG.info("Cleaning up news.");
    }

	public NewsService getNewsService() {
		return newsService;
	}

	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}
	
    private class BackgroundThread implements Runnable {
	
        public void run() {    
			LOG.info("Initializing news...");
			newsService.getAllActiveNewsFeeds();
			LOG.info("Finished initializing news.");
        	while (true) {
        		try {
	    			LOG.info("News sleeping...");
	                try {
	                    Thread.sleep(1000 * 60 * 15);
	                } catch (InterruptedException e) {
	                    LOG.error(e.getMessage(), e);
	                }
	    			LOG.info("Refreshing news...");
	    			for (NewsSource newsSource : newsService.getAllActiveNewsSources()) {
	    				newsService.getCache().updateCache(newsSource);
	    			}
	    			LOG.info("Finished refreshing news.");	                
        		} catch (Exception e) {
                    LOG.error(e.getMessage(), e);
        		}
        	}
        }
        
    }
    
}



