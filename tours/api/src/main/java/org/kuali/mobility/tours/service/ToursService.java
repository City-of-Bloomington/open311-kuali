package org.kuali.mobility.tours.service;

import java.util.List;

import org.kuali.mobility.tours.entity.Tour;

public interface ToursService {
	public Tour findTourById(Long id);
    public Tour findTourByName(String name);
    public Long saveTour(Tour tour);
    public List<Tour> findAllTours();
    public void deleteTourById(Long id);
}
