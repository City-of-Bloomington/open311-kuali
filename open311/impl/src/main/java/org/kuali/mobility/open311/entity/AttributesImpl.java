package org.kuali.mobility.open311.entity;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlRegistry;;


public class AttributesImpl implements Attributes, Serializable {

	private static final long serialVersionUID = 7822282214636842702L;
	
	private String service_code;
	
    private List<Attribute> attribute;
	
	public void setAttribute(List<Attribute> attribute) {
		this.attribute = attribute;
	}

	public List<Attribute> getAttribute() {
		return attribute;
	}
    
   	public String getServicecode() {
		return service_code;
	}
	public void setServicecode(String service_code) {
		this.service_code = service_code;
	}
	
	public ObjectFactory getObjectFactory(){
		return new ObjectFactory();
	}
	@XmlRegistry
	public static class ObjectFactory {
	
		
		public AttributeValue createAttributeValue() {
			return new AttributeValueImpl();
		}
	  
		public Attribute createAttribute() {
			return new AttributeImpl();
		}
		
	  
	}
}
