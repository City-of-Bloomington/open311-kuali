package org.kuali.mobility.user.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class UserCache implements Serializable {

	private static final long serialVersionUID = 3304161870808847308L;
	
	private Map<String, UserCacheObject> cache;
	
	public UserCache() {
		cache = new HashMap<String, UserCacheObject>();
	}
	
	public UserCacheObject get(String key) {
		return cache.get(key);
	}
	
	public void put(String key, Object item) {
		if (cache.containsKey(key)) {
			UserCacheObject cacheObject = cache.get(key);
			cacheObject.setItem(item);
		} else {
			cache.put(key, new UserCacheObject(item));
		}
	}
	
	public void remove(String key) {
		cache.remove(key);
	}
}
