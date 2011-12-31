package org.kuali.mobility.security.authn.util;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;

public class AuthenticationMapper {
	Logger LOG = Logger.getLogger( AuthenticationMapper.class );

	private String loginURL;
	private String logoutURL;
	
	private List<String> urlPatterns;
	
	public AuthenticationMapper( final String configFilePath )
	{
		urlPatterns = new ArrayList<String>();
		try {
			readProperties( configFilePath );
		} catch (Exception e) {
			LOG.error( "Unable to load configuration file: "+configFilePath+"\n"+e.getLocalizedMessage() );
		}
	}
	
	public boolean requiresAuthentication( final String url )
	{
		boolean requiresAuthN = false;
		for( String pattern : getUrlPatterns() )
		{
			if( url.startsWith(pattern) )
			{
				requiresAuthN = true;
			}
		}
		return requiresAuthN;
	}

	public String getLoginURL() {
		return loginURL;
	}

	public void setLoginURL(String loginURL) {
		this.loginURL = loginURL;
	}

	public String getLogoutURL() {
		return logoutURL;
	}

	public void setLogoutURL(String logoutURL) {
		this.logoutURL = logoutURL;
	}

	public List<String> getUrlPatterns() {
		return urlPatterns;
	}

	public void setUrlPatterns(List<String> urlPatterns) {
		this.urlPatterns = urlPatterns;
	}
	
	public void readProperties(final String configFilePath) throws Exception {
		Properties properties = new Properties();
		InputStream is = this.getClass().getResourceAsStream( configFilePath );
		properties.loadFromXML(is);
		
		for( Object s : properties.keySet() )
		{
			LOG.debug( "Loading property "+(String)s+" = "+properties.getProperty( (String)s ) );
			if( ((String)s).startsWith( AuthenticationConstants.AUTH_PATH_PREFIX ) )
			{
				this.urlPatterns.add( properties.getProperty( (String)s ) );
			}
			else if( "loginURL".equalsIgnoreCase( (String)s ) )
			{
				this.setLoginURL( properties.getProperty((String) s) );
			}
			else if( "logoutURL".equalsIgnoreCase( (String)s ) )
			{
				this.setLogoutURL( properties.getProperty((String) s) );
			}
		}
	}
}
