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
package org.kuali.mobility.events.util;

import org.apache.commons.collections.Predicate;
import org.kuali.mobility.events.entity.Category;

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
