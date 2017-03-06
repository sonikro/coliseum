package br.com.sonikro.coliseum.resources;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.connections.RCONConnection;
import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.model.APIResponse;
import br.com.sonikro.coliseum.entity.Server;

@Path("server")
public class ServerResource {
	@Inject
	private GenericDAO<Server> serverDAO;;
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{serverId}")
	public Server getServer(@PathParam("serverId") Integer serverId)
	{
		return serverDAO.find(serverId);
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/status/{serverId}")
	public String getServerStatus(@PathParam("serverId") Integer serverId) throws Exception
	{
		Server server = serverDAO.find(serverId);
		
		RCONConnection rconConnection = server.getRCONConnection();
		
		rconConnection.open();
		
		String result = rconConnection.executeCmd("status");
		
		rconConnection.close();
		
		return result;
		
	}
	
	@POST @Transactional
	@Produces(MediaType.APPLICATION_JSON)
	public Server createServer(Server server)
	{
		serverDAO.insert(server);
		return server;
	}
	
	@DELETE @Transactional
	@Path("/{serverId}")
	public void deleteServer(@PathParam("serverId") Integer serverId)
	{
		Server server = serverDAO.find(serverId);
		
		serverDAO.delete(server);
	}
}
