package br.com.sonikro.coliseum.resources;

import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.connections.RCONConnection;
import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.model.APIResponse;
import br.com.sonikro.coliseum.security.Secure;
import br.com.sonikro.coliseum.entity.Map;
import br.com.sonikro.coliseum.entity.Server;

@Path("server") @Secure(authenticator="BASIC_AUTH")
public class ServerResource {	
	@Inject
	private GenericDAO<Server> mServerDAO;;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{serverId}")
	public Server getServer(@PathParam("serverId") Long serverId)
	{
		Server server = mServerDAO.find(serverId);
		return server;
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/status/{serverId}")
	public String getServerStatus(@PathParam("serverId") Long serverId) throws Exception
	{
		Server server = mServerDAO.find(serverId);
		
		RCONConnection rconConnection = server.getRCONConnection();
		
		rconConnection.open();
		
		String result = rconConnection.executeCmd("status");
		
		rconConnection.close();
		
		return result;
		
	}
	
	@POST 
	@Produces(MediaType.APPLICATION_JSON)
	public Server createServer(Server server)
	{
		mServerDAO.insert(server);
		return server;
	}
	
	@DELETE 
	@Path("/{serverId}")
	public void deleteServer(@PathParam("serverId") Long serverId)
	{
		Server server = mServerDAO.find(serverId);
		
		mServerDAO.delete(server);
	}
	
	@GET
	@Path("{serverId}/rcon_command/{rconCommand}")
	@Produces(MediaType.TEXT_PLAIN)
	public String executeRconCommand(@PathParam("serverId") Long serverId,
										@PathParam("rconCommand") String command) throws Exception
	{
		Server server = mServerDAO.find(serverId);
		
		RCONConnection connection = server.getRCONConnection();
		
		connection.open();
		
		String result = connection.executeCmd(command);
		
		connection.close();
		
		return result;
	}
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Server updateServer(Server server)
	{
		mServerDAO.update(server);
		return server;
	}
	
	@GET
	@Path("/{serverId}/maps")
	public List<Map> getServerMaps(@PathParam("serverId") Long serverId)
	{
		Server server = mServerDAO.find(serverId);
		return server.getMaps();
	}
	
}
