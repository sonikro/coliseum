package br.com.sonikro.coliseum.providers;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;

import br.com.sonikro.coliseum.exceptions.ExceptionResponseModel;

@Provider
public class ExceptionFilter implements ExceptionMapper<Throwable>{
	private static Logger logger = Logger.getLogger(ExceptionFilter.class);
	@Inject
	private EntityManager entityManager;
	
	@Override
	public Response toResponse(Throwable exception) {
		rollbackTransaction();
		return responseError(exception);
		
	}

	private Response responseError(Throwable exception) {
		ExceptionResponseModel response = new ExceptionResponseModel();
		response.setError_code(500);
		response.setError_message(exception.getMessage());
		response.setException_class(exception.getClass().getName());
		exception.printStackTrace();
		return Response.status(500).entity(response).type(MediaType.APPLICATION_JSON).build();
	}

	private void rollbackTransaction() {
		try {
			entityManager.getTransaction().rollback();
		} catch (Exception e) {
			logger.error("Error Rolling Back Transaction",e);
		}
		
	}

}
