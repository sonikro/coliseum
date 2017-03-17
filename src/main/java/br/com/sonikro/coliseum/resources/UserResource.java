package br.com.sonikro.coliseum.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.dao.UserDAO;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.coliseum.security.Secure;

@Path("user") @Secure(authenticator="BASIC_AUTH")
public class UserResource extends BaseResource{
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userSteamId}")
	public User getUserBySteamId(@PathParam("userSteamId")String steamId)
	{
		UserDAO typedUserDAO = new UserDAO(userDAO);
		return typedUserDAO.findBySteamId(steamId);
	}
}
