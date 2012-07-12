package org.kuali.mobility.open311.entity;

import java.util.List;

public interface Attributes {
		
		public String getServicecode();
		public void setServicecode(String servicecode);
	
		public void setAttribute(List<Attribute> attribute);
		public List<Attribute> getAttribute();		
		
		public Object getObjectFactory();
		
}
