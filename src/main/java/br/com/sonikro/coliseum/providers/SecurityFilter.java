package br.com.sonikro.coliseum.providers;

import java.io.IOException;
import java.util.List;

import javax.enterprise.inject.Instance;
import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.security.Secure;
import br.com.sonikro.coliseum.security.authenticators.IRequestAuthenticator;
import br.com.sonikro.coliseum.security.authenticators.RequestAuthenticationException;
import br.com.sonikro.coliseum.security.authenticators.RequestAuthenticatorFactory;
import br.com.sonikro.coliseum.util.ReflectionTool;

@Provider
public class SecurityFilter implements ContainerRequestFilter{
	
	@SuppressWarnings("rawtypes")
	@Inject
	private GenericDAO genericDAO;
	
	private IRequestAuthenticator authenticator;
	
	private static Logger logger = Logger.getLogger(SecurityFilter.class);
	
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		logger.info("Executing Security Filter");
		
		Object matchedResource = requestContext.getUriInfo().getMatchedResources().get(0); //Get Resource Class
		
		Secure secure = (Secure) ReflectionTool.getRecursiveAnnotation(matchedResource.getClass(), Secure.class);
		
		if(secure != null) //It's secure
		{
			try {
				logger.info("Authenticating with "+secure.authenticator());
				authenticator = new RequestAuthenticatorFactory().factory(secure,genericDAO);
				authenticator.authenticateRequest(requestContext);
			} catch (RequestAuthenticationException e) {
				requestContext.abortWith(e.getResponse());
			}
		}
		else
		{
			logger.info("No authentication required for "+matchedResource);
		}
		
	}

}
