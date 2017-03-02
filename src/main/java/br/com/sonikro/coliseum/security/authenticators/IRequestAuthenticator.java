package br.com.sonikro.coliseum.security.authenticators;

import javax.ws.rs.container.ContainerRequestContext;

public interface IRequestAuthenticator {
	public void authenticateRequest(ContainerRequestContext requestContext) throws RequestAuthenticationException;
}
