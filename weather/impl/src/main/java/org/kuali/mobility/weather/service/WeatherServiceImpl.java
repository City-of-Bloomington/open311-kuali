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

package org.kuali.mobility.weather.service;

import org.kuali.mobility.weather.dao.WeatherDao;
import org.kuali.mobility.weather.entity.Weather;

public class WeatherServiceImpl implements WeatherService {
	
	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(WeatherServiceImpl.class);
	
	private WeatherDao weatherDao;
    
    private Weather weatherData;
    
	public Weather getWeatherForecast() {
		if (weatherData == null) {
			weatherData = weatherDao.parseWeather();
		}
		return weatherData;
	}	
	
	public void refreshWeather() {
		LOG.info("Refershing weather cache...");
		Weather cache = weatherDao.parseWeather();
		setWeatherData(cache);
		LOG.info("Finished refreshing weather cache.");
	}

	public Weather getWeatherData() {
		return weatherData;
	}

	public void setWeatherData(Weather weatherData) {
		this.weatherData = weatherData;
	}
	
    public WeatherDao getWeatherDao() {
		return weatherDao;
	}

	public void setWeatherDao(WeatherDao weatherDao) {
		this.weatherDao = weatherDao;
	}
	
}


