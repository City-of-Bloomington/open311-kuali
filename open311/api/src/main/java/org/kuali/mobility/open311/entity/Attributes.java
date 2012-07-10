package org.kuali.mobility.open311.entity;

import java.util.List;
import org.kuali.mobility.open311.entity.Attribute;

public interface Attributes {
		
		public String getServicecode();
		public void setServicecode(String servicecode);
		
		public AttributesCollection getAttributes();
		public void setAttributes(AttributesCollection attributes);
		
}
