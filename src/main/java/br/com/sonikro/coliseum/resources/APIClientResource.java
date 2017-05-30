package br.com.sonikro.coliseum.resources;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

import br.com.sonikro.coliseum.entity.APIClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;

@Path("/APIClient") @Api
public class APIClientResource extends BaseResource{
	
	@POST @ApiOperation(tags="APIClient", value = "Authenticate API Client object")
	@ApiResponse(code = 200, message = "Authenticated")
	@Path("/authenticate")
	public boolean authenticateClient(APIClient client) throws Exception
	{
		APIClient dbClient = mAPIClientDAO.find(client.getUsername());
		if(dbClient == null)
		{
			return false;
		}
		if(dbClient.validate(client.getUsername(), client.getPassword()))
		{
			return true;
		}
		
		return false;
	}
}
