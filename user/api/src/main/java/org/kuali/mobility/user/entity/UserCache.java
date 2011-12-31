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
