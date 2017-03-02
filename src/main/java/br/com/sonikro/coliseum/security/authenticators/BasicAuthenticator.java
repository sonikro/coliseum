package br.com.sonikro.coliseum.security.authenticators;

import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.xml.bind.DatatypeConverter;

import org.glassfish.jersey.internal.util.Base64;

import br.com.sonikro.coliseum.dao.APIClientDAO;
import br.com.sonikro.coliseum.model.APIClient;
import br.com.sonikro.coliseum.util.JPAUtil;

public class BasicAuthenticator implements IRequestAuthenticator{
	public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	public static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
	public static final String HOST_KEY = "Host";
	@Override
	public void authenticateRequest(ContainerRequestContext requestContext) throws RequestAuthenticationException {
		
		  List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		  if(authHeader != null && authHeader.size() > 0)
		  {
			  String authToken = authHeader.get(0);	
			  authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			  System.out.println(authToken);
			  byte[] decodedBytes = DatatypeConverter.parseBase64Binary(authToken);
			  String decodedString = new String(decodedBytes);
			  
			  String authArray[] = decodedString.split(":", 2);
			 
			  String username = authArray[0];
			  String password = authArray[1];
			  String hostname = requestContext.getHeaderString(HOST_KEY);
			  
			  APIClientDAO dao = new APIClientDAO(new JPAUtil().getEntityManager());
			  
			  APIClient client = dao.findByHostname(hostname);
			  
			  if(client.validate(username, password))
			  {
				  return;
			  }
		  }
		  
		  throw new RequestAuthenticationException("Basic Authentication Failed");
		
	}

}
