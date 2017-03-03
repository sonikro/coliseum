package br.com.sonikro.coliseum.producers;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;


import br.com.sonikro.coliseum.security.authenticators.IRequestAuthenticator;
import br.com.sonikro.coliseum.security.authenticators.RequestAuthenticationException;
import br.com.sonikro.coliseum.security.authenticators.RequestAuthenticatorFactory;
import br.com.sonikro.coliseum.security.authenticators.RequestAuthenticatorType;

public class RequestAuthenticatorProducer {
	
	public IRequestAuthenticator produceRequestAuthenticator() throws RequestAuthenticationException
	{
		return new RequestAuthenticatorFactory().factory(RequestAuthenticatorType.BASIC_AUTH);
	}
}