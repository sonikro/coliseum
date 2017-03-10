package br.com.sonikro.coliseum.security.authenticators;

import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.security.Secure;

public class RequestAuthenticatorFactory {
	
	public IRequestAuthenticator factory(RequestAuthenticatorType type, GenericDAO dao) throws RequestAuthenticationException
	{
		switch (type) {
			case BASIC_AUTH:
				return (IRequestAuthenticator) new BasicAuthenticator(dao);
			default:
				throw new RequestAuthenticationException("Invalid Authenticator Method");
		}
	}
	
	public IRequestAuthenticator factory(Secure secure, GenericDAO dao) throws RequestAuthenticationException
	{
		switch(secure.authenticator())
		{
			case "BASIC_AUTH":
				return (IRequestAuthenticator) new BasicAuthenticator(dao);
			default:
				throw new RequestAuthenticationException("Invalid Authenticator Method : "+secure.authenticator());
		}
	}
}
