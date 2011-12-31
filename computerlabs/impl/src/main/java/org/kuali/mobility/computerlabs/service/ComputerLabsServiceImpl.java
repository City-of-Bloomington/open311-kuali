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

package org.kuali.mobility.computerlabs.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.mobility.computerlabs.entity.Lab;
import org.kuali.mobility.computerlabs.entity.Location;
import org.kuali.mobility.util.mapper.DataMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class ComputerLabsServiceImpl implements ComputerLabsService {

	private static org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(ComputerLabsServiceImpl.class);

	private Map<String, List<String>> labUrls;

	@Autowired
	private DataMapper dataMapper;

	private String dataMappingUrl;

	public Collection<Location> findAllLabsByCampus(String campus) {
		Map<String, Location> locations = new HashMap<String, Location>();
		for (String sourceUrl : labUrls.get(campus)) {
			try {
				URL url = new URL(sourceUrl);
				List<Lab> labs = new ArrayList<Lab>();
				if (dataMappingUrl != null && !"".equals(dataMappingUrl.trim())) {
					labs = dataMapper.mapData(labs, url, new URL(dataMappingUrl));
				} else {
					labs = dataMapper.mapData(labs, url, "labMapping.xml");
				}
				for (Lab lab : labs) {
					Location location = null;
					if (locations.get(lab.getBuildingCode()) != null) {
						location = locations.get(lab.getBuildingCode());
					} else {
						location = new Location(lab.getBuilding());
						locations.put(lab.getBuildingCode(), location);
					}
					location.getLabs().add(lab);
				}
			} catch (Exception e) {
				LOG.error("errors", e);
			}
		}
		return locations.values();
	}

	public String getDataMappingUrl() {
		return dataMappingUrl;
	}

	public void setDataMappingUrl(String dataMappingUrl) {
		this.dataMappingUrl = dataMappingUrl;
	}

	public Map<String, List<String>> getLabUrls() {
		return labUrls;
	}

	public void setLabUrls(Map<String, List<String>> labUrls) {
		this.labUrls = labUrls;
	}

}
