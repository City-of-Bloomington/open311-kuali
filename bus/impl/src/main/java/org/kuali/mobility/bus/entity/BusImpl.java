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

/**
 *
 * @author Joe Swanson <joseswan@umich.edu>
 */
public class BusImpl implements Bus {
    
    private long id;
	private String name;
    
    private long routeId;
    private String routeName;
	
    private String latitude;
    private String longitude;
    private String heading;
    
    private String color;

    /**
     * @return the id
     */
    @Override
    public long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @Override
    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the routeId
     */
    @Override
    public long getRouteId() {
        return routeId;
    }

    /**
     * @param routeId the routeId to set
     */
    @Override
    public void setRouteId(long routeId) {
        this.routeId = routeId;
    }

    /**
     * @return the routeName
     */
    @Override
    public String getRouteName() {
        return routeName;
    }

    /**
     * @param routeName the routeName to set
     */
    @Override
    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    /**
     * @return the latitude
     */
    @Override
    public String getLatitude() {
        return latitude;
    }

    /**
     * @param latitude the latitude to set
     */
    @Override
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    /**
     * @return the longitude
     */
    @Override
    public String getLongitude() {
        return longitude;
    }

    /**
     * @param longitude the longitude to set
     */
    @Override
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    /**
     * @return the heading
     */
    @Override
    public String getHeading() {
        return heading;
    }

    /**
     * @param heading the heading to set
     */
    @Override
    public void setHeading(String heading) {
        this.heading = heading;
    }

    /**
     * @return the color
     */
    @Override
    public String getColor() {
        return color;
    }

    /**
     * @param color the color to set
     */
    @Override
    public void setColor(String color) {
        this.color = color;
    }
    
}
