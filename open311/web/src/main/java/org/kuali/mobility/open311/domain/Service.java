/**
 * Copyright 2011-2012 The Kuali Foundation Licensed under the
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

package org.kuali.mobility.open311.domain;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import org.apache.commons.collections.list.LazyList;
import org.apache.commons.collections.FactoryUtils;


public class Service implements Serializable {

	private static final long serialVersionUID = 1844528404920336947L;
    
	private String fname;
	private String lname;
	private String email;
	private String phone;
	private String description;
	private String latitude;
	private String longitude;
	private String addressString;
	private String responseServiceCode;
    private List<Attribute> attributes = LazyList.decorate(new ArrayList<Attribute>(),FactoryUtils.instantiateFactory(Attribute.class));
	
	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
    
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
    
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

		public String getAddressString() {
		return addressString;
	}

	public void setAddressString(String addressString) {
		this.addressString = addressString;
	}
    
	public String getResponseServiceCode() {
		return responseServiceCode;
	}

	public void setResponseServiceCode(String responseServiceCode) {
		this.responseServiceCode = responseServiceCode;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}
	
	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	public void toStr() {
		
		System.out.println(getFname());
		System.out.println(getLname());
		System.out.println(getEmail());
		System.out.println(getPhone());
		System.out.println(getDescription());
		System.out.println(getLatitude());
		System.out.println(getLongitude());
		System.out.println(getAddressString());
		System.out.println(getResponseServiceCode());
		for(Attribute a : attributes)
		{
			System.out.println(a.getKey()+" "+a.getValue());
		}
	}
}
