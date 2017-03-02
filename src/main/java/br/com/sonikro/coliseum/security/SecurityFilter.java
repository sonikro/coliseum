package br.com.sonikro.coliseum.security;

import java.io.IOException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.ext.Provider;


import br.com.sonikro.coliseum.security.authenticators.IRequestAuthenticator;
import br.com.sonikro.coliseum.security.authenticators.RequestAuthenticationException;
import br.com.sonikro.coliseum.security.authenticators.RequestAuthenticatorFactory;
import br.com.sonikro.coliseum.security.authenticators.RequestAuthenticatorType;

@Provider
public class SecurityFilter implements ContainerRequestFilter{

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
			
			  try {
				IRequestAuthenticator authenticator = new RequestAuthenticatorFactory().factory(RequestAuthenticatorType.BASIC_AUTH);
				authenticator.authenticateRequest(requestContext);
			} catch (RequestAuthenticationException e) {
				requestContext.abortWith(e.getResponse());
			}
		
	}

}
