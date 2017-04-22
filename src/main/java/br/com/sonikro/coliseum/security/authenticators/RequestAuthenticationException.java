package br.com.sonikro.coliseum.security.authenticators;

import javax.ws.rs.core.Response;

public class RequestAuthenticationException extends Exception {
	
	public RequestAuthenticationException()
	{
		
	}
	
	public RequestAuthenticationException(String message)
	{
		super(message);
	}
	
	public Response getResponse()
	{
		return Response.status(Response.Status.UNAUTHORIZED)
				//.e(getMessage())
				.build();
	}
}
