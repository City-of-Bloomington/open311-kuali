package org.kuali.mobility.tours.service;

import java.util.List;

import org.kuali.mobility.tours.dao.ToursDao;
import org.kuali.mobility.tours.entity.POI;
import org.kuali.mobility.tours.entity.Tour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToursServiceImpl implements ToursService {

	@Autowired
	private ToursDao toursDao;
	public void setConfigParamDao(ToursDao toursDao) {
		this.toursDao = toursDao;
	}
	
	@Override
	public Tour findTourById(Long id) {
		return toursDao.findTourById(id);
	}

	@Override
	public Tour findTourByName(String name) {
		return toursDao.findTourByName(name);
	}

	@Override
	public Long saveTour(Tour tour) {
		return toursDao.saveTour(tour);
	}

	@Override
	public List<Tour> findAllTours() {
		return toursDao.findAllTours();
	}

	@Override
	public void deleteTourById(Long id) {
		toursDao.deleteTourById(id);
	}

	@Override
	public POI findPoiById(Long id) {
		return toursDao.findPoiById(id);
	}

	@Override
	public Long savePoi(POI poi) {
		return toursDao.savePoi(poi);
	}

	@Override
	public void deletePoiById(Long poiId) {
		toursDao.deletePoiById(poiId);
	}

	@Override
	public List<POI> findAllCommonPOI() {
		return toursDao.findAllCommonPOI();
	}
}
