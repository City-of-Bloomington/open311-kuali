package org.kuali.mobility.tours.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.kuali.mobility.tours.entity.POI;
import org.kuali.mobility.tours.entity.Tour;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class ToursDaoImpl implements ToursDao {

	@PersistenceContext
    private EntityManager entityManager;
	
	@Override
	public Tour findTourById(Long id) {
		Query query = entityManager.createQuery("select t from Tour t where t.tourId = :id");
        query.setParameter("id", id);
        try {
        	return (Tour) query.getSingleResult();
        } catch (Exception e) {
        	return null;
        }
	}

	@Override
	public Tour findTourByName(String name) {
		Query query = entityManager.createQuery("select t from Tour t where t.name like :name");
        query.setParameter("name", name);
        try {
        	return (Tour) query.getSingleResult();
        } catch (Exception e) {
        	return null;
        }
	}

	@Override
	@Transactional
	public Long saveTour(Tour tour) {
		if (tour == null) {
            return null;
        }
        if (tour.getName() != null) {
        	tour.setName(tour.getName().trim());
        }
        if (tour.getDescription() != null) {
        	tour.setDescription(tour.getDescription().trim());
        }
        try {
	        if (tour.getTourId() == null) {
	            entityManager.persist(tour);
	        } else {
	        	deletePoisByTourId(tour.getTourId());
	            entityManager.merge(tour);
	        }
        } catch (OptimisticLockException oe) {
            return null;
        }
        return tour.getTourId();
	}
	
	private void deletePoisByTourId(long tourId) {
		Query query = entityManager.createQuery("delete from POI poi where poi.tourId = :id");
        query.setParameter("id", tourId);
        query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Tour> findAllTours() {
		Query query = entityManager.createQuery("select t from Tour t order by t.name");
        return query.getResultList();
	}

	@Override
	@Transactional
	public void deleteTourById(Long id) {
		deletePoisByTourId(id);
		Query query = entityManager.createQuery("delete from Tour t where t.tourId = :id");
        query.setParameter("id", id);
        query.executeUpdate();
	}
	
	@Override
	public POI findPoiById(Long id) {
		Query query = entityManager.createQuery("select t from POI t where t.poiId = :id");
        query.setParameter("id", id);
        try {
        	return (POI) query.getSingleResult();
        } catch (Exception e) {
        	return null;
        }
	}
	
	@Override
	@Transactional
	public Long savePoi(POI poi) {
		if (poi == null) {
            return null;
        }
        if (poi.getName() != null) {
        	poi.setName(poi.getName().trim());
        }
        if (poi.getDescription() != null) {
        	poi.setDescription(poi.getDescription().trim());
        }
        try {
	        if (poi.getPoiId() == null) {
	            entityManager.persist(poi);
	        } else {
	            entityManager.merge(poi);
	        }
        } catch (OptimisticLockException oe) {
            return null;
        }
        return poi.getPoiId();
	}
	
	@Override
	@Transactional
	public void deletePoiById(Long poiId) {
		Query query = entityManager.createQuery("delete from POI t where t.poiId = :id");
        query.setParameter("id", poiId);
        query.executeUpdate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<POI> findAllCommonPOI() {
		Query query = entityManager.createQuery("select t from POI t where t.tourId is null");
        return query.getResultList();
	}

	public EntityManager getEntityManager() {
		return entityManager;
	}

	public void setEntityManager(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
}
