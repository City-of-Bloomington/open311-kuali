package org.kuali.mobility.open311.entity;

import java.io.Serializable;
import java.util.List;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import javax.xml.bind.annotation.*;

import org.eclipse.persistence.oxm.annotations.XmlPath;


@XmlRootElement(name="service_definition")
public class AttributesImpl implements Attributes, Serializable {

	private static final long serialVersionUID = 7822282214636842702L;
	
	private String service_code;
	
	private AttributesCollection attributes;

	public String getServicecode() {
		return service_code;
	}
	public void setServicecode(String service_code) {
		this.service_code = service_code;
	}
	
	public void setAttributes(AttributesCollection attributes) {
		this.attributes = attributes;
	}
	public AttributesCollection getAttributes() {
		return attributes;
	}
	
	public ObjectFactory getObjectFactory(){
		return new ObjectFactory();
	}
	
	@XmlRegistry
	public static class ObjectFactory {
	
		public AttributesCollection createAttributesCollection() {
			return new AttributesCollectionImpl();
		}
	  
		public AttributeValue createAttributeValue() {
			return new AttributeValueImpl();
		}
	  
		public Attribute createAttribute() {
			return new AttributeImpl();
		}
		
		public AttributeValues createAttributeValues() {
			return new AttributeValuesImpl();
		}
	  
	}	
}
