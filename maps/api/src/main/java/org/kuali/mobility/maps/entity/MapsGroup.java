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
 
package org.kuali.mobility.maps.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import flexjson.JSONSerializer;

public class MapsGroup implements Serializable {

	private static final long serialVersionUID = -4775149005202188253L;

	private String id;
	
	private String name;
    
	private boolean active;
    
    private Set<Location> mapsLocations;

    private Set<MapsGroup> mapsGroupChildren;
    
    public MapsGroup() {
    	mapsLocations = new HashSet<Location>();
    	mapsGroupChildren = new HashSet<MapsGroup>();
    }
    
    public String toJson() {
        return new JSONSerializer().exclude("*.class").include("mapsLocations").serialize(this);
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Set<Location> getMapsLocations() {
		return mapsLocations;
	}

	public void setMapsLocations(Set<Location> mapsLocations) {
		this.mapsLocations = mapsLocations;
	}

	public Set<MapsGroup> getMapsGroupChildren() {
		return mapsGroupChildren;
	}

	public void setMapsGroupChildren(Set<MapsGroup> mapsGroupChildren) {
		this.mapsGroupChildren = mapsGroupChildren;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
