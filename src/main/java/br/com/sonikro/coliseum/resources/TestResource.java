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
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.entity.Tier;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.coliseum.enumerators.LobbyStatus;
import br.com.sonikro.coliseum.enumerators.ServerStatus;

	
@Path("test")
public class TestResource {
	@Inject
	private GenericDAO<APIClient> apiClientDAO;
	@Inject
	private GenericDAO<Server> serverDAO;
	@Inject
	private GenericDAO<GameType> gameTypeDAO;
	@Inject
	private GenericDAO<User> userDAO;
	@Inject
	private GenericDAO<Tier> tierDAO;
	@Inject
	private GenericDAO<Lobby> lobbyDAO;
	
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
    
    @Path("/createTier")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public Tier createTier()
    {
    	Tier tier = new Tier();
    	tier.setName("Elite");
    	tierDAO.insert(tier);
    	return tier;
    	
    }
    @Path("/createUser")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Transactional
    public User createUser()
    {
    	User user = new User();
    	user.setAvatar_url("http://cdn.edgecast.steamstatic.com/steamcommunity/public/images/avatars/bf/bf84cd82efc8f88003d07076a1b71d8e6a08c124_full.jpg");
    	user.setBanned(false);
    	user.setDemos_page("https://demos.tf/profiles/76561197989428692");
    	user.setLogs_page("http://logs.tf/profile/76561197989428692");
    	user.setNickname("Sonikro");
    	user.setSteam_id("STEAM_0:0:14581482");
    	
    	user.setTier(tierDAO.find(1));
    	userDAO.insert(user);
    	return user;
    }
    
    @Path("/initializeEntities")
    @POST
    @Transactional
    public void initializeEntities()
    {
    	createTier();
    	createUser();
    	createClient();
    	createGameType();
    	createServer();
    }
    
    @Path("/createNewLobby")
    @POST
    
    public Lobby createNewLobby()
    {
    	Server server = createServer();
    	GameType gameType = createGameType();
    	Tier tier = createTier();
    	
    	Lobby lobby = new Lobby();
    	lobby.setServer(server);
    	lobby.setGameType(gameType);
    	lobby.setStatus(LobbyStatus.BUILDING);
    	
    	LobbyTeam teamRED = new LobbyTeam();
    	teamRED.setName("RED");
    	
    	LobbyTeam teamBLU = new LobbyTeam();
    	teamBLU.setName("BLU");
    	
    	lobby.addTeam(teamRED);
    	lobby.addTeam(teamBLU);
    	
    	lobbyDAO.insert(lobby);
    	return lobby;
    }
}
