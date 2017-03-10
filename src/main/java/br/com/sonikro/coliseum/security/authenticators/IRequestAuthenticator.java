package br.com.sonikro.coliseum.security.authenticators;

import javax.enterprise.inject.Model;
import javax.ws.rs.container.ContainerRequestContext;

import br.com.sonikro.coliseum.dao.GenericDAO;

public interface IRequestAuthenticator {
	public void authenticateRequest(ContainerRequestContext requestContext) throws RequestAuthenticationException;
}
