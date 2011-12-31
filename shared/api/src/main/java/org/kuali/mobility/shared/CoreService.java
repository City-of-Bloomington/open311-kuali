package org.kuali.mobility.shared;

import java.util.Map;

public interface CoreService {
	public String findGoogleAnalyticsProfileId();
	public void setGoogleAnalyticsProfileId(String id);
	
	public void setCssCustomizations(Map<String, String> customizations);
	public Map<String, String> getCssCustomizations();
}
