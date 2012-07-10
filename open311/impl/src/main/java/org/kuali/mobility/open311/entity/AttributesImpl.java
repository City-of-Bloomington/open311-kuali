package org.kuali.mobility.open311.entity;

import java.io.Serializable;
import java.util.List;
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
	
	
			
		
}
