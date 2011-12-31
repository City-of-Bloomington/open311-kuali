package org.kuali.mobility.security.authn.entity;

public interface User {
	boolean isPublicUser();
	
	void invalidateUser();
	
	void setPrincipalName(String userId);
	String getPrincipalName();

	void setRequestURL(String url);
	String getRequestURL();
	
    boolean isMember(String groupName);
    
    void setViewCampus(String viewCampus);
    String getViewCampus();
    
    void setEmail(String email);
    String getEmail();
}
