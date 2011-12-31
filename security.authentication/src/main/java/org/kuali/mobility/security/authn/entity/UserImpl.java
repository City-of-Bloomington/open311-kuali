package org.kuali.mobility.security.authn.entity;

import java.io.Serializable;

import org.kuali.mobility.security.authn.util.AuthenticationConstants;

public class UserImpl implements User, Serializable {

	private static final long serialVersionUID = -1437870897314265069L;

	private String principalName;
	private String requestURL;
	
	private String viewCampus;
	
	private String email;
	
	@Override
	public boolean isPublicUser() {
		if( this.getPrincipalName() == null
			|| "".equalsIgnoreCase(this.getPrincipalName())
			|| this.getPrincipalName().startsWith(AuthenticationConstants.PUBLIC_USER) )
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void setPrincipalName(String userId) {
		this.principalName = userId;
	}

	@Override
	public String getPrincipalName() {
		return this.principalName;
	}

	@Override
	public void invalidateUser() {
		this.principalName = null;
	}

	@Override
	public void setRequestURL(String url) {
		this.requestURL = url;
	}

	@Override
	public String getRequestURL() {
		return this.requestURL;
	}

	@Override
	public boolean isMember(String groupName) {
		return false;
	}

	public String getViewCampus() {
		return viewCampus;
	}

	public void setViewCampus(String viewCampus) {
		this.viewCampus = viewCampus;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
