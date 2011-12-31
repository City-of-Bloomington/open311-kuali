package org.kuali.mobility.security.authn.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.kuali.mobility.security.authn.entity.User;
import org.kuali.mobility.security.authn.util.AuthenticationConstants;
import org.kuali.mobility.security.authn.util.AuthenticationMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthenticationInterceptor implements HandlerInterceptor {

	private static final Logger LOG = Logger.getLogger( AuthenticationInterceptor.class);
	
	private AuthenticationMapper authenticationMapper;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		User user = (User)request.getSession(true).getAttribute( AuthenticationConstants.KME_USER_KEY );

		boolean passThrough = true;
		if( getAuthenticationMapper().requiresAuthentication( request.getServletPath() ) )
		{
			if( user == null )
			{
				LOG.info( "User object not found in session.  This should not happen." );
				doLogin( request, response );
				passThrough=false;
			}
			else if( user.isPublicUser() )
			{
				user.setRequestURL(request.getServletPath());
				doLogin( request, response );
				passThrough=false;
			}
		}
		return passThrough;
	}

	@Override
	public void postHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	private void doLogin(HttpServletRequest request, HttpServletResponse response) throws Exception
	{
		if( getAuthenticationMapper().getLoginURL().startsWith( "http:" ) )
		{
			response.sendRedirect( getAuthenticationMapper().getLoginURL() );
		}
		else
		{
			response.sendRedirect( request.getContextPath() + getAuthenticationMapper().getLoginURL() );
		}
	}

	public AuthenticationMapper getAuthenticationMapper() {
		return authenticationMapper;
	}

	public void setAuthenticationMapper(AuthenticationMapper authenticationMapper) {
		this.authenticationMapper = authenticationMapper;
	}
}
