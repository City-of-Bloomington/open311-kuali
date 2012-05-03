package org.kuali.mobility.news.dao;

import org.kuali.mobility.news.entity.NewsSource;
import org.kuali.mobility.news.service.NewsService;

public class NewsInitBean {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(NewsInitBean.class);
	   
    private NewsDao dao;
    private NewsCache cache;
    
	private static Thread backgroundThread = null;
	
	public void init() {
		backgroundThread = new Thread(new BackgroundThread());
    	backgroundThread.setDaemon(true);
    	backgroundThread.start();    	
	}

    public void cleanup() {
    	LOG.info("Cleaning up news.");
    }

    /**
     * @return the dao
     */
    public NewsDao getDao() {
        return dao;
    }

    /**
     * @param dao the dao to set
     */
    public void setDao(NewsDao dao) {
        this.dao = dao;
    }

    /**
     * @return the cache
     */
    public NewsCache getCache() {
        return cache;
    }

    /**
     * @param cache the cache to set
     */
    public void setCache(NewsCache cache) {
        this.cache = cache;
    }
	
    private class BackgroundThread implements Runnable {
	
        public void run() {    
			LOG.info("Initializing news...");
			getDao().findAllActiveNewsSources();
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
	    			for (NewsSource newsSource : getDao().findAllActiveNewsSources()) {
	    				getCache().updateCache(newsSource);
	    			}
	    			LOG.info("Finished refreshing news.");	                
        		} catch (Exception e) {
                    LOG.error(e.getMessage(), e);
        		}
        	}
        }
        
    }
    
}



