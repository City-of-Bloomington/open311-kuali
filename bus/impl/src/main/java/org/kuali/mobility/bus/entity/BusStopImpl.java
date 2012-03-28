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
package org.kuali.mobility.bus.entity;

import java.util.Comparator;
import java.util.Map;

import java.util.TreeMap;

/**
 *
 * @author joseswan
 */
public class BusStopImpl implements BusStop {

    private long id;
    
    private String name;
    
    private String latitude;
    private String longitude;
    
    private Map<Double, String> schedule;

    /**
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the latitude
     */
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the schedule
     */
    public Map getSchedule() {
        return schedule;
    }

    /**
     * @param schedule the schedule to set
     */
    public void setSchedule(Map map) {
        this.schedule = map;
    }
    
    public void addSchedule() {
        
    }
    
    public boolean equals( Object o )
    {
        boolean isEqual = false;
        
        if( o != null && o instanceof BusStop )
        {
            if( getName() != null && getName().equalsIgnoreCase( ((BusStop)o).getName() ) )
            {
                isEqual = true;
            }
        }
        
        return isEqual;
    }
    
    public int hashCode()
    {
        return 41 + ( getName() == null ? 0 : getName().hashCode() );
    }
}
