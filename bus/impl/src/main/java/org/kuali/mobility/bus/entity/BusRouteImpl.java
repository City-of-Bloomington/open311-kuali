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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joseswan
 */
public class BusRouteImpl implements BusRoute {
    
    private long id;

    private String name;
    
    private List<BusStop> stops;

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
     * @return the stops
     */
    public List<BusStop> getStops() {
        return stops;
    }

    /**
     * @param stops the stops to set
     */
    public void setStops(List<BusStop> stops) {
        this.stops = stops;
    }
    
    public void addStop(BusStop stop)
    {
        if( null == getStops() ) {
            setStops( new ArrayList<BusStop>() );
        }
        getStops().add(stop);
    }
}
