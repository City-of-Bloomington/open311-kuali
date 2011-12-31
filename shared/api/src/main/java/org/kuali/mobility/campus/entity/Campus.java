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
package org.kuali.mobility.campus.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Defines a campus
 * @author Kuali Mobility Team (moblitiy.collab@kuali.org)
 */
public class Campus implements Serializable {

	private static final long serialVersionUID = -8000615503651726243L;

	private String name;

	private String code;

	private List<String> tools;

	public Campus() {
		tools = new ArrayList<String>();
	}

	public List<String> getTools() {
		return tools;
	}

	public void setTools(List<String> tools) {
		this.tools = tools;
	}

	/**
	 * @return the name of the campus
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name of the campus
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the code that uniquely identifies this campus
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code that uniquely identifies this campus
	 */
	public void setCode(String code) {
		this.code = code;
	}
}
