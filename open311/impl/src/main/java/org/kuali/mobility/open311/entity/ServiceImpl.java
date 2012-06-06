package org.kuali.mobility.open311.entity;

import java.io.Serializable;

public class ServiceImpl implements ServiceEntity, Serializable {

	private static final long serialVersionUID = 7822282214636842701L;
	private String serviceCode;
	private String serviceName;
	private String type;
	private String metadata;
	private String keywords;
	private String group;
	private String description;
	//private List<String> menu;
	
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMetadata() {
		return metadata;
	}
	public void setMetadata(String metadata) {
		this.metadata = metadata;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getGroup() {
		return group;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}

	public ServiceEntity compact () {
		ServiceEntity s = new ServiceImpl();
		s.setServiceCode(getServiceCode());
		return s;
	}
}
