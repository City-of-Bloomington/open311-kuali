/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.kuali.mobility.events.util;

import org.apache.commons.collections.Predicate;
import org.kuali.mobility.events.entity.Category;

/**
 *
 * @author joseswan
 */
public final class CategoryPredicate implements Predicate {

    private String campus;
    private String category;
    
    public CategoryPredicate( final String campus, final String category )
    {
        this.setCampus( campus );
        this.setCategory( category );
    }
    
    @Override
    public boolean evaluate(Object obj) {
        boolean campusMatch = false;
        boolean categoryMatch = false;
        
        if( obj instanceof Category )
        {
            if( getCampus() == null )
            {
                campusMatch = true;
            }
            if( getCampus() != null && getCampus().equalsIgnoreCase( ((Category)obj).getCampus() ))
            {
                campusMatch = true;
            }
            if( getCategory() == null )
            {
                categoryMatch = true;
            }
            if( getCategory() != null && getCategory().equalsIgnoreCase( ((Category)obj).getCategoryId() ) )
            {
                categoryMatch = true;
            }
        }
        
        return campusMatch && categoryMatch;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the campus
     */
    public String getCampus() {
        return campus;
    }

    /**
     * @param campus the campus to set
     */
    public void setCampus(String campus) {
        this.campus = campus;
    }
    
}
