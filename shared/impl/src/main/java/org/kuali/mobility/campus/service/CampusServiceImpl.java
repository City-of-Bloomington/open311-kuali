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
package org.kuali.mobility.campus.service;

import java.util.ArrayList;
import java.util.List;

import org.kuali.mobility.campus.entity.Campus;
import org.springframework.stereotype.Service;

@Service
public class CampusServiceImpl implements CampusService {

	@Override
	public List<Campus> getCampuses() {
		List<Campus> campuses = new ArrayList<Campus>();

		Campus campus = new Campus("BL", "IU Bloomington");
		campuses.add(campus);
		campus = new Campus("IN", "IUPUI");
		campuses.add(campus);
		campus = new Campus("CO", "IUPUC");
		campuses.add(campus);
		campus = new Campus("EA", "IU East");
		campuses.add(campus);
		campus = new Campus("KO", "IU Kokomo");
		campuses.add(campus);
		campus = new Campus("NW", "IU Northwest");
		campuses.add(campus);
		campus = new Campus("SB", "IU South Bend");
		campuses.add(campus);
		campus = new Campus("SE", "IU Southeast");
		campuses.add(campus);

		return campuses;
	}

}
