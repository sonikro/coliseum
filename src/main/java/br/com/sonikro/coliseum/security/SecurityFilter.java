package br.com.sonikro.coliseum.security;

import java.io.IOException;

import javax.inject.Inject;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import br.com.sonikro.coliseum.security.authenticators.IRequestAuthenticator;
import br.com.sonikro.coliseum.security.authenticators.RequestAuthenticationException;

@Provider
public class SecurityFilter implements ContainerRequestFilter{
	@Inject
	private IRequestAuthenticator authenticator;
	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
			
		Logger logger = Logger.getLogger(SecurityFilter.class);
		logger.info("Executing Security Filter");
		
			  try {
				authenticator.authenticateRequest(requestContext);
			} catch (RequestAuthenticationException e) {
				requestContext.abortWith(e.getResponse());
			}
		
	}

}
