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
import java.util.Collection;
import java.util.List;

import org.kuali.mobility.configparams.dao.ConfigParamDao;
import org.kuali.mobility.configparams.entity.ConfigParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

/**
 * Service to perform manipulation of configuration parameters
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
@Service(value = "ConfigParamService")
public class ConfigParamServiceImpl implements ConfigParamService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ConfigParamServiceImpl.class);

	@Autowired
	private ConfigParamDao configParamDao;

	@Transactional
	@CacheEvict(value = "configParams", key="#id", allEntries=false)
	public void deleteConfigParamById(Long id) {
		configParamDao.deleteConfigParamById(id);
	}

	@Transactional
	public List<ConfigParam> findAllConfigParam() {
		return configParamDao.findAllConfigParam();
	}

	@Transactional
	@Cacheable(value="configParams", key="#id")
	public ConfigParam findConfigParamById(Long id) {
		return configParamDao.findConfigParamById(id);
	}

	@Transactional
	@Cacheable(value="configParams", key="#name")
	public ConfigParam findConfigParamByName(String name) {
		return configParamDao.findConfigParamByName(name);
	}

	@Transactional(readOnly = true)
	@Cacheable(value="configParams", key="#name")
	public String findValueByName(String name) {
		ConfigParam configParam = findConfigParamByName(name);
		String value = configParam != null ? configParam.getValue() : null;
		return value != null ? value.trim() : "";
	}

	@Transactional
	@CacheEvict(value = "configParams", key="#configParam.configParamId", allEntries=false)
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

}
