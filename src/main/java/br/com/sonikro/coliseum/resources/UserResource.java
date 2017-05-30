package br.com.sonikro.coliseum.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.dao.UserDAO;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.coliseum.security.Secure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("user") @Secure(authenticator="BASIC_AUTH") @Api
public class UserResource extends BaseResource{
	@ApiOperation(tags="user", value="List all users")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<User> getAllUsers()
	{
		return mUserDAO.list();
	}
	
	@ApiOperation(tags="user", value="Get specific user by SteamID")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{userSteamId}")
	public User getUserBySteamId(@PathParam("userSteamId")String steamId)
	{
		UserDAO typedUserDAO = new UserDAO(mUserDAO);
		return typedUserDAO.findBySteamId(steamId);
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@ApiOperation(tags="user", value="Create new user")
	public void createUser(User user)
	{
		mUserDAO.insert(user);
	}
	
	@ApiOperation(tags="user", value="Update User")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public User updateUser(User user)
	{
		mUserDAO.update(user);
		return user;
	}
}
