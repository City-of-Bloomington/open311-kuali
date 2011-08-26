package org.kuali.mobility.tours.dao;

import java.util.List;

import org.kuali.mobility.tours.entity.Tour;

public interface ToursDao {
	public Tour findTourById(Long id);
    public Tour findTourByName(String name);
    public Long saveTour(Tour tour);
    public List<Tour> findAllTours();
    public void deleteTourById(Long id);
}
