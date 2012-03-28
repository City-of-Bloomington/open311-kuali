package org.kuali.mobility.dining.service;

import java.util.List;

import org.kuali.mobility.dining.dao.DiningDao;
import org.kuali.mobility.dining.entity.Menu;
import org.kuali.mobility.dining.entity.Place;

public class DiningServiceUMImpl implements DiningService {
	private DiningDao dao;    // DiningDaoUMImpl
	
	public void setDao(DiningDao dao) {
		this.dao = dao;
	}

	@Override
	public List<Place> getPlaces() {
		
		return dao.getPlaceList();
	}
	
	@Override
	public String getMenusJson( final String name, final String location ) {
		
		return dao.getMenusJson(name, location);
	}
	
	@Override
	public List<Menu> getMenus(String location) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
