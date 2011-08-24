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

package org.kuali.mobility.configparams.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.kuali.mobility.configparams.dao.ConfigParamDao;
import org.kuali.mobility.configparams.entity.ConfigParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

@Service(value = "ConfigParamService")
public class ConfigParamServiceImpl implements ConfigParamService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ConfigParamServiceImpl.class);

	private static final int CONFIG_PARAM_UPDATE_INTERVAL = 5; // 5 min

	private static ConcurrentMap<String, ConfigParam> configParams;

	private static Thread homeScreenReloaderThread = null;

	static {
		configParams = new ConcurrentHashMap<String, ConfigParam>();
	}

	@Override
	public void startCache() {
		homeScreenReloaderThread = new Thread(new ConfigParamReloader());
		homeScreenReloaderThread.setDaemon(true);
		homeScreenReloaderThread.start();
	}

	@Override
	public void stopCache() {
		homeScreenReloaderThread.interrupt();
		homeScreenReloaderThread = null;
	}

	@Autowired
	private ConfigParamDao configParamDao;

	public void setConfigParamDao(ConfigParamDao configParamDao) {
		this.configParamDao = configParamDao;
	}

	@Transactional
	public void deleteConfigParamById(Long id) {
		configParamDao.deleteConfigParamById(id);
	}

	@Transactional
	public List<ConfigParam> findAllConfigParam() {
		return configParamDao.findAllConfigParam();
	}

	@Transactional
	public ConfigParam findConfigParamById(Long id) {
		return configParamDao.findConfigParamById(id);
	}

	@Transactional
	public ConfigParam findConfigParamByName(String name) {
		return configParamDao.findConfigParamByName(name);
	}

	@Transactional(readOnly = true)
	public String findValueByName(String name) {
		ConfigParam configParam = configParams.get(name);
		if (configParam == null) {
			LOG.warn("Cannot find config param with name: " + name + " in the cache. Fetching from database");
			ConfigParam configParam2 = findConfigParamByName(name);
			if (configParam2 != null) {
				return configParam2.getValue();
			} else {
				return "";
			}
		}
		String value = configParam.getValue();
		return value != null ? value.trim() : "";
	}
	
	@Transactional(readOnly = true)
	public List<ConfigParam> findAllByNameStartsWith(String prefix) {
		List<ConfigParam> matches = new ArrayList<ConfigParam>();
		
		if (configParams == null || configParams.size() == 0) {
			return matches;
		}
		
		for (ConfigParam configParam : configParams.values()) {
			if (configParam.getName() != null && configParam.getName().startsWith(prefix)) {
				matches.add(configParam);
			}
		}
		return matches;
	}

	@Transactional
	public Long saveConfigParam(ConfigParam configParam) {
		return configParamDao.saveConfigParam(configParam);
	}

	public ConfigParam fromJsonToEntity(String json) {
		return new JSONDeserializer<ConfigParam>().use(null, ConfigParam.class).deserialize(json);
	}

	public String toJson(Collection<ConfigParam> collection) {
		return new JSONSerializer().exclude("*.class").serialize(collection);
	}

	public Collection<ConfigParam> fromJsonToCollection(String json) {
		return new JSONDeserializer<List<ConfigParam>>().use(null, ArrayList.class).use("values", ConfigParam.class).deserialize(json);
	}

	private class ConfigParamReloader implements Runnable {

		public void run() {
			Calendar updateCalendar = Calendar.getInstance();
			Date nextCacheUpdate = new Date();

			// Cache loop
			while (true) {
				Date now = new Date();
				if (now.after(nextCacheUpdate)) {
					try {
						reloadConfigParams();
					} catch (Exception e) {
						LOG.error("Error reloading home screen cache.", e);
					}
					updateCalendar.add(Calendar.MINUTE, CONFIG_PARAM_UPDATE_INTERVAL);
					nextCacheUpdate = new Date(updateCalendar.getTimeInMillis());
				}
				try {
					Thread.sleep(1000 * 60);
				} catch (InterruptedException e) {
					LOG.error("Error:", e);
				}
			}
		}

		private void reloadConfigParams() {
			List<ConfigParam> params = configParamDao.findAllConfigParam();
			for (ConfigParam configParam : params) {
				configParams.put(configParam.getName(), configParam);
			}
		}

	}

}
