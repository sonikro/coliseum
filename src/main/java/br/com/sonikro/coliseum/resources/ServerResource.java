package br.com.sonikro.coliseum.resources;

import java.util.List;

import javax.inject.Inject;
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
import br.com.sonikro.coliseum.entity.Map;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.security.Secure;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Path("server") @Secure(authenticator="BASIC_AUTH") @Api
public class ServerResource {	
	@Inject
	private GenericDAO<Server> mServerDAO;;
	
	@ApiOperation(tags="server", value="Get specific server data")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{serverId}")
	public Server getServer(@PathParam("serverId") Long serverId)
	{
		Server server = mServerDAO.find(serverId);
		return server;
	}
	
	@ApiOperation(tags="server", value="Get server Staus (RCON)")
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
	
	@ApiOperation(tags="server", value="Add new server")
	@POST 
	@Produces(MediaType.APPLICATION_JSON)
	public Server createServer(Server server)
	{
		mServerDAO.insert(server);
		return server;
	}
	
	@ApiOperation(tags="server", value="Delete Server")
	@DELETE 
	@Path("/{serverId}")
	public void deleteServer(@PathParam("serverId") Long serverId)
	{
		Server server = mServerDAO.find(serverId);
		
		mServerDAO.delete(server);
	}
	
	@ApiOperation(tags="server", value="Execute RCON Command at Server")
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
	
	@ApiOperation(tags="server", value="Update Server")
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	public Server updateServer(Server server)
	{
		mServerDAO.update(server);
		return server;
	}
	
	@GET
	@Path("/{serverId}/maps")
	@ApiOperation(tags="server", value="List server's maps")
	public List<Map> getServerMaps(@PathParam("serverId") Long serverId)
	{
		Server server = mServerDAO.find(serverId);
		return server.getMaps();
	}
	
}
