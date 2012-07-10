package org.kuali.mobility.open311.entity;

import java.util.List;

public interface AttributesCollection {
		
	public void setAttribute(List<Attribute> attribute);
	public List<Attribute> getAttribute();
		
}
