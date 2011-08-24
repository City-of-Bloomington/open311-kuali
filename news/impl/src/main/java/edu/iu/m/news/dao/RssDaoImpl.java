package edu.iu.m.news.dao;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import edu.iu.m.news.entity.Rss;

@Repository 
public class RssDaoImpl implements RssDao {
	
	private static final org.apache.log4j.Logger LOG = org.apache.log4j.Logger.getLogger(RssDaoImpl.class);

	@PersistenceContext
    private EntityManager entityManager;

    @SuppressWarnings("unchecked")
    public List<Rss> findAllRss() {
        Query query = entityManager.createQuery("select r from Rss r"); 
        try { 
        	return query.getResultList();
        } catch (Exception e) {        	
        	return null;
        }
    }

    public Rss findRssById(Long id) {
        Query query = entityManager.createQuery("select r from Rss r where r.rssId = :id");
        query.setParameter("id", id);
        try { 
        	return (Rss) query.getSingleResult();
        } catch (Exception e) {        	
        	return null;
        }
    }

    public Rss findRssByUrl(String url) {
        Query query = entityManager.createQuery("select r from Rss r where r.url = :url");
        query.setParameter("url", url);
        try { 
        	return (Rss) query.getSingleResult();
        } catch (Exception e) {        	
        	return null;
        }
    }
    
    public Rss findRssByMaintRssId(Long maintRssId) {
        Query query = entityManager.createQuery("select r from Rss r where r.rssMaintId = :rssMaintId");
        query.setParameter("rssMaintId", maintRssId);
        try { 
        	return (Rss) query.getSingleResult();
        } catch (Exception e) {        	
        	return null;
        }
    }

    public void saveRss(Rss rss) {
        if (rss == null) {
            return;
        }
        if (rss.getRssId() == null) {
            entityManager.persist(rss);
        } else {
            entityManager.merge(rss);
        }
    }

    /*
    public void deleteRssByMaintRssId(Long maintRssId) {
        Query query = entityManager.createQuery("select r from Rss r where r.rssMaintId = :rssMaintId");
        query.setParameter("rssMaintId", maintRssId);
        List<Rss> rssList = null;
        try { 
        	rssList =  (List<Rss>) query.getResultList();
        	Iterator<Rss> iter = rssList.iterator();
        	while (iter.hasNext()) {
        		this.deleteRss(iter.next());
        	}
        } catch (Exception e) {        	
        	LOG.error("Error deleting RSS by MaintRssId", e);
        }        
    }
    */
    
    public void deleteRssByMaintRssId(Long maintRssId) {
        Query query = entityManager.createQuery("select r from Rss r where r.rssMaintId = :rssMaintId");
        query.setParameter("rssMaintId", maintRssId);
        List<Rss> rssList = null;
        try { 
        	rssList =  (List<Rss>) query.getResultList();
        	Iterator<Rss> iter = rssList.iterator();
        	while (iter.hasNext()) {
        		this.deleteRss(iter.next());
        	}
        } catch (Exception e) {        	
        	LOG.error("Error deleting RSS by MaintRssId", e);
        }        
    }
    
    public void deleteRssById(Long id) {
    	this.deleteRss(this.findRssById(id));
    }
    
    public void deleteRss(Rss rss) {
    	if (rss != null) {
    		entityManager.remove(rss);	
    	}
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

}
