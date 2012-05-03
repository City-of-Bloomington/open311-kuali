package org.kuali.mobility.bus.service;


/**
 * @author xinfeng
 *
 */
public class BusInitBean {
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(BusInitBean.class);
	private BusService busService;
	private int timePeriodInSec1; 
	private int timePeriodInSec2; 
	private static Thread backgroundThread1 = null;
	private static Thread backgroundThread2 = null;
	
	public void init() {
		backgroundThread1 = new Thread(new BackgroundThread1());
    	backgroundThread1.setDaemon(true);
    	backgroundThread1.start();    
    	//
    	backgroundThread2 = new Thread(new BackgroundThread2());
    	backgroundThread2.setDaemon(true);
    	backgroundThread2.start();    
	}

    public void cleanup() {
    	LOG.info("Cleaning up bus data.");
    }

	public BusService getBusService() {
		return busService;
	}

	public void setBusService(BusService busService) {
		this.busService = busService;
	}
    	
    public int getTimePeriodInSec1() {
		return timePeriodInSec1;
	}

	public void setTimePeriodInSec1(int timePeriodInSec1) {
		this.timePeriodInSec1 = timePeriodInSec1;
	}
	
	
	 public int getTimePeriodInSec2() {
			return timePeriodInSec2;
		}

		public void setTimePeriodInSec2(int timePeriodInSec2) {
			this.timePeriodInSec2 = timePeriodInSec2;
		}

	private class BackgroundThread1 implements Runnable {
    	
        public void run() {    
        	//LOG.info("Initializing bus location data...");
        	busService.getDao().loadBusLocations();
        	//busService.getDao().getBusRoutes();
        	LOG.info("Finished initializing bus location data ..");
        	while (true) {
        		try {
        			LOG.info("Bus sleeping...");
        			try {
        				Thread.sleep(1000 * timePeriodInSec1);
        			} catch (InterruptedException e) {
        				LOG.error(e.getMessage(), e);
        			}
        			
        			//LOG.info("Refreshing bus location data...");
        			busService.getDao().loadBusLocations();
        			LOG.info("Finished refreshing bus location data.");	
        		
        		} catch (Exception e) {
        			LOG.error(e.getMessage(), e);
        		}
        	}
        }
        
    }
	
	private class BackgroundThread2 implements Runnable {
        public void run() {    
        	//LOG.info("Initializing bus routes data...");
        	     busService.getDao().getBusRoutes();
        	LOG.info("Finished initializing bus routes ..");
        	while (true) {
        		try {
        			LOG.info("Bus sleeping...");
        			try {
						Thread.sleep(1000 * timePeriodInSec2);
        			} catch (InterruptedException e) {
        				LOG.error(e.getMessage(), e);
        			}
        			//LOG.info("Refreshing bus Routes data...");
        			 busService.getDao().getBusRoutes();                	
        			LOG.info("Finished refreshing bus Routes data.");	
        		
        		} catch (Exception e) {
        			LOG.error(e.getMessage(), e);
        		}
        	}
        }
        
    }
    
}
