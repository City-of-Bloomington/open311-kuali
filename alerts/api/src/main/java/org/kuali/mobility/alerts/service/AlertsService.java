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

package org.kuali.mobility.alerts.service;

import java.util.List;

import org.kuali.mobility.alerts.entity.Alert;

/**
 * Provides service methods for retrieving <code>Alert</code> instances.
 * 
 * @author Kuali Mobility Team 
 */
public interface AlertsService {

	/**
	 * A finder that returns all <code>Alert</code> instances for the campus passed.
	 * .
	 * 
	 * @param campus 
	 * 			- <code>String</code> campus code.
	 * @return <code>List&lt;Alert&gt;</code> filtered by the campus or an empty 
	 * 			<code>List</code>. 
	 */
	public List<Alert> findAlertsByCampus(String campus);

	int findAlertCountByCampus(String campus);
	
}
