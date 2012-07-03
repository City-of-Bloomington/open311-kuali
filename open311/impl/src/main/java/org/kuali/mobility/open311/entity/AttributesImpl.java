package org.kuali.mobility.open311.entity;

import java.io.Serializable;
import java.util.List;

public class AttributesImpl implements Attributes, Serializable {

	private static final long serialVersionUID = 7822282214636842702L;
	
	private String servicecode;
	private List<Attribute> attributes;
	
	public String getServiceCode() {
		return servicecode;
	}
	public void setServiceCode(String servicecode) {
		this.servicecode = servicecode;
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
		
		
}
