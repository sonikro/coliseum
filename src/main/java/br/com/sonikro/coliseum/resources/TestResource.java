package br.com.sonikro.coliseum.resources;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.entity.APIClient;
import br.com.sonikro.coliseum.entity.GameType;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.enumerators.ServerStatus;

	
@Path("test")
public class TestResource {
	@Inject
	private GenericDAO<APIClient> apiClientDAO;
	@Inject
	private GenericDAO<Server> serverDAO;
	@Inject
	private GenericDAO<GameType> gameTypeDAO;
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String test() {
    	
		return "Success";
    }
    
    @GET
    @Path("/exceptionTest")
    public void exceptionTest() throws Exception
    {
    	throw new Exception("This is a random exception");
    }
    
    @Path("/createAPIClient")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public APIClient createClient()
    {
    	APIClient client = new APIClient();
    	client.setUsername("sonikro");
    	client.setHostname("sonikro.com");
    	client.setPassword("ronaldo");
    	apiClientDAO.insert(client);
    	return client;
    }
    
    @Path("/createServer")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Server createServer()
    {
    	Server server = new Server();
    	server.setIp("192.168.1.130");
    	server.setHostname("Community Highlander @ Sonikro Solutions");
    	server.setPort(27015);
    	server.setRcon_password("#sshl#");
    	server.setNumber_of_slots(20);
    	server.setStatus(ServerStatus.AVAILABLE);
    	serverDAO.insert(server);
    	return server;
    }
    
    @Path("/createGameType")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public GameType createGameType()
    {

    	GameType gameType = new GameType();
    	gameType.setMax_rost_number(10);
    	gameType.setName("6v6");
    	gameType.setNumber_of_players(12);
    	gameTypeDAO.insert(gameType);
    	return gameType;
    }
    
    @Path("/initializeEntities")
    @POST
    @Transactional
    public void initializeEntities()
    {
    	Server server = new Server();
    	server.setIp("192.168.1.130");
    	server.setHostname("Community Highlander @ Sonikro Solutions");
    	server.setPort(27015);
    	server.setRcon_password("#sshl#");
    	server.setNumber_of_slots(20);
    	server.setStatus(ServerStatus.AVAILABLE);
    	serverDAO.insert(server);
    	
    	APIClient client = new APIClient();
    	client.setUsername("sonikro");
    	client.setHostname("sonikro.com");
    	client.setPassword("ronaldo");
    	apiClientDAO.insert(client);
    	
    	GameType gameType = new GameType();
    	gameType.setMax_rost_number(10);
    	gameType.setName("6v6");
    	gameType.setNumber_of_players(12);
    	gameTypeDAO.insert(gameType);
    	
    	
    }
}
