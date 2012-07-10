package org.kuali.mobility.open311.entity;

import java.util.List;

public class AttributesCollectionImpl implements AttributesCollection{

	private List<Attribute> attribute;
	
	public AttributesCollectionImpl() {}
	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
	}

	public List<Attribute> getAttribute() {
		return attribute;
	}
	
	public AttributesCollection createAttributesCollection() {
		return new AttributesCollectionImpl();
	}
	
}
