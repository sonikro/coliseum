package br.com.sonikro.coliseum.security.authenticators;

public class RequestAuthenticatorFactory {
	
	public IRequestAuthenticator factory(RequestAuthenticatorType type) throws RequestAuthenticationException
	{
		switch (type) {
			case BASIC_AUTH:
				return (IRequestAuthenticator) new BasicAuthenticator();
			default:
				throw new RequestAuthenticationException("Invalid Authenticator Method");
		}
	}
}
