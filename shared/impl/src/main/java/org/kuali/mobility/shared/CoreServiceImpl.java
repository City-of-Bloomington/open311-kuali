package org.kuali.mobility.shared;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class CoreServiceImpl implements CoreService {

	private String googleAnalyticsProfileId;
	private final ConcurrentMap<String, String> cssCustomizations = new ConcurrentHashMap<String, String>();
	
	@Override
	public String findGoogleAnalyticsProfileId() {
		return googleAnalyticsProfileId;
	}

	@Override
	public void setGoogleAnalyticsProfileId(String id) {
		googleAnalyticsProfileId = id;
	}

}
