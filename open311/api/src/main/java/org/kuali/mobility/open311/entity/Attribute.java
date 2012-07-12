package org.kuali.mobility.open311.entity;

import java.util.List;

public interface Attribute {
		
		public String getVariable();
		public void setVariable(String variable);
		
		public String getCode();
		public void setCode(String code);
		
		public String getOrder();
		public void setOrder(String order);
		
		public String getDatatype();
		public void setDatatype(String datatype);
		
		public String getDatatypeDescription();
		public void setDatatypeDescription(String datatypeDescription);
		
		public String getRequired();
		public void setRequired(String required);
		
		public String getDescription();
		public void setDescription(String description);

		public void setValue(List<AttributeValue> value);
		public List<AttributeValue> getValue();
		
				
}
