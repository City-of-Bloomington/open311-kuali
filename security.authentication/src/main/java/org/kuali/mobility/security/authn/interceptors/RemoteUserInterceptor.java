package org.kuali.mobility.security.authn.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.security.authn.entity.UserImpl;
import org.kuali.mobility.security.authn.util.AuthenticationConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class RemoteUserInterceptor implements HandlerInterceptor {
	private static final Logger LOG = Logger.getLogger( RemoteUserInterceptor.class );

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = (User)request.getSession(true).getAttribute( AuthenticationConstants.KME_USER_KEY );
		if( user == null )
		{
			user = new UserImpl();
			request.getSession().setAttribute( AuthenticationConstants.KME_USER_KEY, user );
		}
		if( request.getRemoteUser() != null && !request.getRemoteUser().isEmpty() )
		{
			if( user.isPublicUser() )
			{
				user.setPrincipalName( request.getRemoteUser() );
			}
			else if( !request.getRemoteUser().equalsIgnoreCase( user.getPrincipalName() ) )
			{
				LOG.info( "Identify mismatch. Expected ["+user.getPrincipalName()+"] recieved ["+request.getRemoteUser()+"]" );
				user.invalidateUser();
				request.getSession().invalidate();
				request.getSession(true);
				response.sendError(401, "Identity Mismatch.  Attempting to override existing user with a new one." );
			}
		}
		else
		{
			if( user.getPrincipalName() != null )
			{
				if( !user.getPrincipalName().startsWith( AuthenticationConstants.PUBLIC_USER ) )
				{
					LOG.info( "Identity mismatch. Session user populated when no REMOTE_USER provided. User removed from session." );
					user.setPrincipalName( AuthenticationConstants.PUBLIC_USER + request.getSession().getId() );
				}
				else if( !user.getPrincipalName().equalsIgnoreCase( AuthenticationConstants.PUBLIC_USER + request.getSession().getId() ) )
				{
					LOG.info( "Identity mismatch. Public user key does not match expected id. User updated in session.");
					user.invalidateUser();
					user.setPrincipalName( AuthenticationConstants.PUBLIC_USER + request.getSession().getId() );
				}
			}
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {}

}
