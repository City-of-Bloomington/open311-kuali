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

import java.util.List;

/**
 *
 * @author Joe Swanson <joseswan@umich.edu>
 */
public interface BusRoute {

    void addStop(BusStop stop);

    /**
     * @return the id
     */
    long getId();

    /**
     * @return the name
     */
    String getName();

    /**
     * @return the stops
     */
    List<BusStop> getStops();

    /**
     * @param id the id to set
     */
    void setId(long id);

    /**
     * @param name the name to set
     */
    void setName(String name);

    /**
     * @param stops the stops to set
     */
    void setStops(List<BusStop> stops);
    
}
