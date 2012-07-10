package org.kuali.mobility.open311.entity;

import java.util.List;

public class AttributeValuesImpl implements AttributeValues {
	
	private List<AttributeValue> value;

	public void setValue(List<AttributeValue> value) {
		this.value = value;
	}

	public List<AttributeValue> getValue() {
		return value;
	}
	
	

}
