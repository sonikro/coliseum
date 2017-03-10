package br.com.sonikro.coliseum.security.authenticators;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.container.ContainerRequestContext;
import javax.xml.bind.DatatypeConverter;


import org.jboss.logging.Logger;

import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.entity.APIClient;

@Model
public class BasicAuthenticator implements IRequestAuthenticator{
	public static final String AUTHORIZATION_HEADER_KEY = "Authorization";
	public static final String AUTHORIZATION_HEADER_PREFIX = "Basic";
	public static final String HOST_KEY = "Host";
	
	private static Logger logger = Logger.getLogger(BasicAuthenticator.class);
	
	private GenericDAO<APIClient> dao;
	
	public  BasicAuthenticator(GenericDAO dao) {
		this.dao = dao;
		this.dao.setObjectClass(APIClient.class);
	}

	@Override
	public void authenticateRequest(ContainerRequestContext requestContext) throws RequestAuthenticationException {
			logger.info("Executing Basic Authentication");
		  List<String> authHeader = requestContext.getHeaders().get(AUTHORIZATION_HEADER_KEY);
		  if(authHeader != null && authHeader.size() > 0)
		  {
			  String authToken = authHeader.get(0);	
			  authToken = authToken.replaceFirst(AUTHORIZATION_HEADER_PREFIX, "");
			  
			  byte[] decodedBytes = DatatypeConverter.parseBase64Binary(authToken);
			  String decodedString = new String(decodedBytes);
			  
			  String authArray[] = decodedString.split(":", 2);
			 
			  String username = authArray[0];
			  String password = authArray[1];
			  //String hostname = requestContext.getHeaderString(HOST_KEY);
			  
			  //GenericDAO<APIClient> dao = new GenericDAO<>(new JPAUtil().getEntityManager(), APIClient.class);
			  try{
				  APIClient client = dao.find(username);  
				
				  if(client != null && client.validate(username, password))
				  {
					  logger.info("Basic Authentication Success");
					  return;
				  }
			  }catch(Exception e)
			  {
				  logger.error(e);
				  e.printStackTrace();
				  throw new RequestAuthenticationException(e.getMessage());
			  }
			  
		  }
		  
		  throw new RequestAuthenticationException("Basic Authentication Failed");
		
	}

}
