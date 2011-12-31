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

import java.util.Collection;
import java.util.List;

import org.kuali.mobility.configparams.entity.ConfigParam;

/**
 * Interface for a contract for interacting with configuration parameters
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
public interface ConfigParamService {

	/**
	 * @param configParam the ConfigParam to save
	 * @return the id of the saved ConfigParam
	 */
	public Long saveConfigParam(ConfigParam configParam);

	/**
	 * @return all configuration parameters
	 */
	public List<ConfigParam> findAllConfigParam();

	/**
	 * @param id the id of the ConfigParam to retrieve
	 * @return the ConfigParam matching the id
	 */
	public ConfigParam findConfigParamById(Long id);

	/**
     * @param name the name of the ConfigParam to retrieve
     * @return the ConfigParam matching the name
     */
	public ConfigParam findConfigParamByName(String name);

	/**
     * @param id the id of the ConfigParam to delete
     */
	public void deleteConfigParamById(Long id);

	/**
	 * @param name the name of the ConfigParam
	 * @return the value of the matched ConfigParam
	 */
    public String findValueByName(String name);

    /**
     * Convert a collection of ConfigParam objects to JSON notation
     * @param collection the colleciton of ConfigParam objects to convert
     * @return the JSON string representing the collection
     */
    public String toJson(Collection<ConfigParam> collection);
    
    /**
     * Parse a JSON string into a ConfigParam object
     * @param json
     * @return the parsed ConfigParam
     */
    public ConfigParam fromJsonToEntity(String json);
    
    /**
     * Parse a JSON string into a ConfigParam collection
     * @param json
     * @return the parsed ConfigParam collection
     */
    public Collection<ConfigParam> fromJsonToCollection(String json);

}
