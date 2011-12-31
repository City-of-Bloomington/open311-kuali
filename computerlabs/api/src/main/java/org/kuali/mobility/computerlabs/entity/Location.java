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

package org.kuali.mobility.computerlabs.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * An object representing a location for a computer lab.  This most 
 * likely would be a building. Location contains the name of the building
 * and a <code>List&lt;Lab&gt;</code> of labs or rooms in the building
 * containing available computers.
 * 
 * @author Kuali Mobility Team
 */
public class Location implements Serializable, Comparable<Location> {

	private static final long serialVersionUID = -4991494626566555287L;
	
	private String name;
	private List<Lab> labs;
	
	public Location(String name) {
		this.name = name;
		this.labs = new ArrayList<Lab>();
	}

	public List<Lab> getLabs() {
		return labs;
	}

	public void setLabs(List<Lab> labs) {
		this.labs = labs;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int compareTo(Location that) {
        if (this == null || that == null) {
            return -1;
        }
        if (this.getName() == that.getName()) {
        	return 0;
        }
		return this.getName().compareTo(that.getName());
	}

}
