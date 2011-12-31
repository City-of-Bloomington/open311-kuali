package org.kuali.mobility.security.authn.util;

import static org.junit.Assert.*;

import org.apache.log4j.Logger;
import org.junit.Test;

public class AuthenticationMapperTest {
	private static Logger LOG = Logger.getLogger( AuthenticationMapperTest.class);
	
	private static final String TEST_PROPERTIES_FILE = "/authentication.xml";
	
	@Test
	public void testRequiresAuthentication() 
	{
		AuthenticationMapper am = new AuthenticationMapper( TEST_PROPERTIES_FILE );
		
		assertTrue( "Failed to load properties.", am.getUrlPatterns() != null && am.getUrlPatterns().size() > 0 );
		
		for( String s : am.getUrlPatterns() )
		{
			LOG.debug( "Configured to protect: "+s );
		}
		assertTrue( "Failed to find URL pattern /oauth in url patterns.", am.requiresAuthentication( "/oauth" ) );
		assertTrue( "Improperly found URL pattern /fluffybunnies in url patterns.", !am.requiresAuthentication( "/fluffybunnies" ) ); 
	}

}
