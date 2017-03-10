package br.com.sonikro.coliseum.providers;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.sonikro.coliseum.exceptions.ExceptionResponseModel;

@Provider
public class ExceptionFilter implements ExceptionMapper<Throwable>{

	@Override
	public Response toResponse(Throwable exception) {
		ExceptionResponseModel response = new ExceptionResponseModel();
		response.setError_code(500);
		response.setError_message(exception.getMessage());
		response.setException_class(exception.getClass().getName());
		exception.printStackTrace();
		return Response.status(500).entity(response).type(MediaType.APPLICATION_JSON).build();
	}

}
