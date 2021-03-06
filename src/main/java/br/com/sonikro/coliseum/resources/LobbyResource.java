package br.com.sonikro.coliseum.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.command.lobby.AddPossibleClassCMD;
import br.com.sonikro.coliseum.command.lobby.AddUserToLobbyCMD;
import br.com.sonikro.coliseum.command.lobby.CreateDefaultLobbyTeamsCMD;
import br.com.sonikro.coliseum.command.lobby.InitializeLobbyCMD;
import br.com.sonikro.coliseum.command.lobby.RemovePossibleClassCMD;
import br.com.sonikro.coliseum.command.lobby.RemoveUserFromLobbyCMD;
import br.com.sonikro.coliseum.command.lobby.SetLobbyMapCMD;
import br.com.sonikro.coliseum.command.lobby.StartLobbyCMD;
import br.com.sonikro.coliseum.command.server.GetAvailableServerCMD;
import br.com.sonikro.coliseum.dao.LobbyDAO;
import br.com.sonikro.coliseum.dao.ServerDAO;
import br.com.sonikro.coliseum.entity.GameClass;
import br.com.sonikro.coliseum.entity.GameType;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyUser;
import br.com.sonikro.coliseum.entity.Map;
import br.com.sonikro.coliseum.entity.ServerCFG;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.coliseum.entity.key.LobbyUserKey;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilder;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;
import br.com.sonikro.coliseum.lobbybuilder.SolvesLobbyStep;
import br.com.sonikro.coliseum.resources.model.RequestNewLobby;
import br.com.sonikro.coliseum.security.Secure;
import br.com.sonikro.command.BaseCommand;
import br.com.sonikro.command.ChainCommand;
import br.com.sonikro.command.ChainCommandBuilder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Authorization;

@Path("lobby") @Secure(authenticator="BASIC_AUTH") 
@Api(tags="lobby", authorizations = {
		@Authorization(value="basic")
})
public class LobbyResource extends BaseResource{
	
	@ApiOperation(value="Request a new lobby for the server")
	@Path("/requestNewLobby")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Lobby initializeLobby(RequestNewLobby request) throws Exception
	{
		
		GameType gameType = mGameTypeDAO.find(request.gameType_id);
		ServerCFG serverCFG = mServerCFGDAO.find(request.cfg_id);
		
		BaseCommand getAvailableServer = cmdBuilder.setCommandClass(GetAvailableServerCMD.class)
												   .initializeWith(new ServerDAO(mServerDAO), gameType.getNumber_of_players())
												   .build();
		
		BaseCommand initializeLobby = cmdBuilder.setCommandClass(InitializeLobbyCMD.class)
											    .initializeWith(mLobbyDAO, gameType, serverCFG)
											    .build();
		
		BaseCommand createLobyTeams = cmdBuilder.setCommandClass(CreateDefaultLobbyTeamsCMD.class)
												 .build();
		
		ChainCommandBuilder chainBuilder = new ChainCommandBuilder();
		
		ChainCommand chainCommand = chainBuilder.setListener(this)
												.add(getAvailableServer)
												.add(initializeLobby)
												.add(createLobyTeams)
												.build();

		chainCommand.dispatch();
		
		chainCommand.throwException();
		
		Lobby newLobby = (Lobby) chainCommand.getResult("lobby");

		return newLobby;
	}
	
	@ApiOperation(value="Get specific lobby data")
	@Path("/{lobbyId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Lobby getLobby(@PathParam("lobbyId") Long lobbyId)
	{
		Lobby lobby = mLobbyDAO.find(lobbyId);
		lobby.getUsers(); //Lazy
		lobby.getTeams();
		return lobby;
	}
	
	@ApiOperation(value="Add user to Lobby")
	@Path("/{lobbyId}/addUser/{userId}")
	@POST
	@SolvesLobbyStep(step_code="NOT_ENOUGH_PLAYERS")
	public void addUserToLobby(@PathParam("lobbyId") Long lobbyId, @PathParam("userId") Long userId) throws Exception
	{
		Lobby lobby = mLobbyDAO.find(lobbyId);
		User managedUser = mUserDAO.find(userId);
		
		
		BaseCommand command = cmdBuilder.setCommandClass(AddUserToLobbyCMD.class)
			      						.initializeWith(mLobbyUserDAO,lobby,managedUser)
			      						.build();
		command.dispatch();
		command.throwException();
	}
	
	@ApiOperation(value="Remove user from lobby")
	@Path("/{lobbyId}/removeUser/{userId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void removeUserFromLobby(@PathParam("lobbyId") Long lobbyId, @PathParam("userId") Long userId) throws Exception
	{
		Lobby lobby = mLobbyDAO.find(lobbyId);
		User managedUser = mUserDAO.find(userId);
		
		BaseCommand command = cmdBuilder.setCommandClass(RemoveUserFromLobbyCMD.class)
										.initializeWith(mLobbyUserDAO,lobby,managedUser)
										.build();
		command.dispatch();
		command.throwException();
		
	}
	
	@ApiOperation(value="Find out in which step the Lobby construction is")
	@Path("/{lobbyId}/getBuildingStep")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LobbyBuilderStep getLobbyBuildingStep(@PathParam("lobbyId") Long lobbyId) throws Exception
	{
		Lobby lobby = mLobbyDAO.find(lobbyId);
		LobbyBuilder builder = new LobbyBuilder(lobby);
		return builder.getStep();
		
	}
	
	@ApiOperation(value="Inform the server that the specific user is willing to play a specific class")
	@Path("/{lobbyId}/user/{userId}/addPossibleClass/{gameClassId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public LobbyUser addUserPossibleClass(@PathParam("lobbyId") Long lobbyId,
										  @PathParam("userId") Long userId,
										  @PathParam("gameClassId") Long gameClassId) throws Exception
	{
		Lobby lobby = mLobbyDAO.find(lobbyId);
		User user = mUserDAO.find(userId);
		LobbyUserKey key = new LobbyUserKey(lobby, user);
		LobbyUser lobbyUser = mLobbyUserDAO.find(key);
		GameClass gameClass = mGameClassDAO.find(gameClassId);
		
		BaseCommand addPossibleClass = cmdBuilder.setCommandClass(AddPossibleClassCMD.class)
												 .initializeWith(lobbyUser, gameClass)
												 .build();
		
		addPossibleClass.dispatch();
		
		addPossibleClass.throwException();
		
		return (LobbyUser) addPossibleClass.getResult("lobbyUser");
	}
	
	@ApiOperation(value="Says that the user is unwilling to play that class")
	@Path("/{lobbyId}/user/{userId}/removePossibleClass/{gameClassId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public LobbyUser removePossibleClass(@PathParam("lobbyId") Long lobbyId,
										  @PathParam("userId") Long userId,
										  @PathParam("gameClassId") Long gameClassId) throws Exception
	{
		Lobby lobby = mLobbyDAO.find(lobbyId);
		User user = mUserDAO.find(userId);
		LobbyUserKey key = new LobbyUserKey(lobby, user);
		LobbyUser lobbyUser = mLobbyUserDAO.find(key);
		GameClass gameClass = mGameClassDAO.find(gameClassId);
		
		BaseCommand addPossibleClass = cmdBuilder.setCommandClass(RemovePossibleClassCMD.class)
												 .initializeWith(lobbyUser, gameClass)
												 .build();
		
		addPossibleClass.dispatch();
		
		addPossibleClass.throwException();
		
		return (LobbyUser) addPossibleClass.getResult("lobbyUser");
	}
	
	@ApiOperation(value="Set Map to be played at the lobby")
	@SolvesLobbyStep(step_code="SELECT_MAP")
	@Path("/{lobbyId}/setMap/{mapId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Lobby setLobbyMap(@PathParam("lobbyId") Long lobbyId,
							 @PathParam("mapId") Long mapId) throws Exception
	{
		Lobby lobby = mLobbyDAO.find(lobbyId);
		Map map = mMapDAO.find(mapId);
		
		BaseCommand setLobbyMap = cmdBuilder.setCommandClass(SetLobbyMapCMD.class)
											.initializeWith(lobby,map)
											.build();
		
		setLobbyMap.dispatch();
		
		setLobbyMap.throwException();
		
		return lobby;
	}
	
	@ApiOperation(value="Start Lobby (Setup Server and stuf)")
	@SolvesLobbyStep(step_code="READY")
	@Path("/{lobbyId}/start")
	@POST
	public void startLobby(@PathParam("lobbyId") Long lobbyId) throws Exception
	{
		Lobby lobby = mLobbyDAO.find(lobbyId);
		
		
		BaseCommand startLobby = cmdBuilder.setCommandClass(StartLobbyCMD.class)
				   .initializeWith(mLobbyDAO,lobby)
				   .build();
		startLobby.dispatch();
	
		startLobby.throwException();
		
	}
	
	
	@ApiOperation(value="Get user from lobby")
	@Path("/{lobbyId}/user/{userId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public LobbyUser getLobbyUser(@PathParam("lobbyId") Long lobbyId, @PathParam("userId") Long userId) throws Exception
	{
		Lobby lobby = mLobbyDAO.find(lobbyId);
		User user = mUserDAO.find(userId);
		LobbyUserKey key = new LobbyUserKey(lobby, user);
		LobbyUser lobbyUser = mLobbyUserDAO.find(key);
		return lobbyUser;
	}
	
	@ApiOperation(value="List all open lobbies (Available to Join)")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Lobby> getAllLobby()
	{
		LobbyDAO dao = new LobbyDAO(mLobbyDAO);
		return dao.getListOpenLobbies();
	}
	

}
