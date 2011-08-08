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
