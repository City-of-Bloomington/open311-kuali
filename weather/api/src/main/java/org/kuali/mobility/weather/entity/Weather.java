package org.kuali.mobility.weather.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Weather {

	private List<Map<String, String>> forecasts;

	private String currentCondition;
	private String temperature;
	private String humidity;
	private String windDirection;
	private String windSpeed;
	private String pressure;
	
	public Weather() {
		forecasts = new ArrayList<Map<String, String>>();
	}

	public List<Map<String, String>> getForecasts() {
		return forecasts;
	}

	public String getPressure() {
		return pressure;
	}

	public void setPressure(String pressure) {
		this.pressure = pressure;
	}

	public void setForecasts(List<Map<String, String>> forecasts) {
		this.forecasts = forecasts;
	}

	public String getCurrentCondition() {
		return currentCondition;
	}

	public void setCurrentCondition(String currentCondition) {
		this.currentCondition = currentCondition;
	}

	public String getTemperature() {
		return temperature;
	}

	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}

	public String getHumidity() {
		return humidity;
	}

	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}

	public String getWind() {
		String speed = getWindSpeed();
		if ("0".equals(speed)) {
			return "0 MPH";
		}
		return speed + " MPH from the " + getWindDirection();
	}
	
	public String getWindDirection() {
		String text = "";
		try {
			int direction = Integer.parseInt(windDirection);
			if (direction > 348.75 && direction <= 11.25) {
				text = "North";
			}
			if (direction > 11.25 && direction <= 33.75) {
				text = "North Northeast";
			}
			if (direction > 33.75 && direction <= 56.25) {
				text = "Northeast";
			}
			if (direction > 56.25 && direction <= 78.75) {
				text = "East Northeast";
			}
			if (direction > 78.75 && direction <= 101.25) {
				text = "East";
			}
			if (direction > 101.25 && direction <= 123.75) {
				text = "East Southeast";
			}
			if (direction > 123.75 && direction <= 146.25) {
				text = "Southeast";
			}
			if (direction > 146.25 && direction <= 168.75) {
				text = "South Southeast";
			}
			if (direction > 168.75 && direction <= 191.25 ) {
				text = "South";
			}
			if (direction > 191.25 && direction <= 213.75) {
				text = "South Southwest";
			}
			if (direction > 213.75 && direction <= 236.25) {
				text = "Southwest";
			}
			if (direction > 236.25 && direction <= 258.75) {
				text = "West Southwest";
			}
			if (direction > 258.75 && direction <= 281.25) {
				text = "West";
			}
			if (direction > 281.25 && direction <= 303.75) {
				text = "North Northwest";
			}
			if (direction > 303.75 && direction <= 326.25) {
				text = "Northwest";
			}
			if (direction > 326.25 && direction <= 348.75) {
				text = "Northwest";
			}			
		} catch (Exception e) {
		}
		return text;
	}

	public void setWindDirection(String windDirection) {
		this.windDirection = windDirection;
	}

	public String getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(String windSpeed) {
		this.windSpeed = windSpeed;
	}

}
