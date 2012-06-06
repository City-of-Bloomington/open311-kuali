package org.kuali.mobility.open311.entity;

public interface ServiceEntity {
		
		public String getServiceCode();
		public void setServiceCode(String serviceCode);
		
		public String getServiceName() ;
		public void setServiceName(String serviceName);
		
		public String getType();
		public void setType(String type);
		
		public String getMetadata();
		public void setMetadata(String metadata);
		
		public String getKeywords();
		public void setKeywords(String keywords);
		
		public String getGroup();
		public void setGroup(String group);
		
		public String getDescription();
		public void setDescription(String description);
		
		public ServiceEntity compact ();
}
