/*
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

import java.util.Map;

/**
 *
 * @author Joe Swanson <joseswan@umich.edu>
 */
public interface BusStop {

    void addSchedule();

    /**
     * @return the id
     */
    long getId();

    /**
     * @return the latitude
     */
    String getLatitude();

    /**
     * @return the longitude
     */
    String getLongitude();

    /**
     * @return the name
     */
    String getName();

    /**
     * @return the schedule
     */
    Map getSchedule();

    /**
     * @param id the id to set
     */
    void setId(long id);

    /**
     * @param latitude the latitude to set
     */
    void setLatitude(String latitude);

    /**
     * @param longitude the longitude to set
     */
    void setLongitude(String longitude);

    /**
     * @param name the name to set
     */
    void setName(String name);

    /**
     * @param schedule the schedule to set
     */
    void setSchedule(Map schedule);
    
}
