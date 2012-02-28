package org.kuali.mobility.weather.service;

public class WeatherInitBean {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(WeatherInitBean.class);

	private WeatherService weatherService;
	private int minutesToRefresh;
	
	private static Thread backgroundThread = null;

	public void init() {
		backgroundThread = new Thread(new BackgroundThread());
		backgroundThread.setDaemon(true);
		backgroundThread.start();
	}

	public void cleanup() {
		LOG.info("Cleaning up news.");
	}

	public WeatherService getWeatherService() {
		return weatherService;
	}

	public void setWeatherService(WeatherService weatherService) {
		this.weatherService = weatherService;
	}

	public int getMinutesToRefresh() {
		return minutesToRefresh;
	}

	public void setMinutesToRefresh(int minutesToRefresh) {
		this.minutesToRefresh = minutesToRefresh;
	}

	private class BackgroundThread implements Runnable {

		public void run() {
			while (true) {
				try {
					weatherService.refreshWeather();
					try {
						Thread.sleep(1000 * 60 * getMinutesToRefresh());
					} catch (InterruptedException e) {
						LOG.error(e.getMessage(), e);
					}
				} catch (Exception e) {
					LOG.error(e.getMessage(), e);
				}
			}
		}

	}

}
