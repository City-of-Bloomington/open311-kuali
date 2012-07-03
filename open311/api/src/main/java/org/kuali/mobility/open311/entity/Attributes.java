package org.kuali.mobility.open311.entity;

import java.util.List;
import org.kuali.mobility.open311.entity.Attribute;

public interface Attributes {
		
		public String getServiceCode();
		public void setServiceCode(String servicecode);
		
		public List<Attribute> getAttributes();
		public void setAttributes(List<Attribute> attributes);
		
}
