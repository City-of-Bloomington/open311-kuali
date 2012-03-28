package org.kuali.mobility.dining.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.kuali.mobility.dining.entity.Place;
import org.kuali.mobility.dining.entity.PlaceByCampusByType;
import org.kuali.mobility.dining.entity.PlaceByType;

public class DiningUtil {

	public static List<PlaceByCampusByType> convertPlaceListForUI (List<Place> placeList) {
		List<PlaceByCampusByType> groups = new ArrayList<PlaceByCampusByType>();
		
		
		Map<String, Map<String, List<Place>> >map = new HashMap<String, Map<String, List<Place>>>();
		
		for(Place p: placeList) {
			String key = p.getCampus();
			
			if(map.get(key) == null) {
				map.put(key, new HashMap<String, List<Place>>());
			}
			
			String typeKey = p.getType();
			
			if(map.get(key).get(typeKey) == null) {
				map.get(key).put(typeKey, new ArrayList<Place>());
			}
			 
			map.get(key).get(typeKey).add(p.compact());			
		}
		
		for ( Map.Entry<String, Map<String, List<Place>>> entry : map.entrySet() ) {
			PlaceByCampusByType c = new PlaceByCampusByType();
			c.setCampus(entry.getKey());
			
			List<PlaceByType> tGroups = new ArrayList<PlaceByType>();
			for (Map.Entry<String, List<Place>> typeEntry : entry.getValue().entrySet() ) {
				PlaceByType t = new PlaceByType();
				t.setType(typeEntry.getKey());
				t.setPlaces(typeEntry.getValue());
				tGroups.add(t);
			}
			c.setPlaceByTypes(tGroups);
			groups.add(c);
		}
		
		return groups;
	}
}
