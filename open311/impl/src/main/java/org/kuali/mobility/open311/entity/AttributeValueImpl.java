package org.kuali.mobility.open311.entity;

import java.io.Serializable;

public class AttributeValueImpl implements AttributeValue, Serializable {

	public AttributeValueImpl() {}
	
	private static final long serialVersionUID = 7822282214636842703L;
	
	private String key;
	private String name;
	
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString() {
		return ("Name: "+name+", Key: "+key);
	}
}
