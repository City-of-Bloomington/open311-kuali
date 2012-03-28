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

import com.thoughtworks.xstream.annotations.XStreamAlias;
import java.util.List;

/**
 *
 * @author Joe Swanson <joseswan@umich.edu>
 */
@XStreamAlias("route")
public class UMBusRoute {
    
    @XStreamAlias("name")
    private String name;

    @XStreamAlias("id")
    private String id;
    
    @XStreamAlias("topofloop")
    private String topofloop;

    @XStreamAlias("busroutecolor")
    private String color;
    
    private List<UMBusStop> stops;
    
    @XStreamAlias("stopcount")
    private String stopcount;

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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the topofloop
     */
    public String getTopofloop() {
        return topofloop;
    }

    /**
     * @param topofloop the topofloop to set
     */
    public void setTopofloop(String topofloop) {
        this.topofloop = topofloop;
    }

    /**
     * @return the color
     */
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * @return the stops
     */
    public List<UMBusStop> getStops() {
        return stops;
    }

    /**
     * @param stops the stops to set
     */
    public void setStops(List<UMBusStop> stops) {
        this.stops = stops;
    }
}
