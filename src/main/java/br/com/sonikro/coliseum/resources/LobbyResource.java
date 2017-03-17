package br.com.sonikro.coliseum.resources;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.command.lobby.AddUserToLobbyCMD;
import br.com.sonikro.coliseum.command.lobby.CreateDefaultLobbyTeamsCMD;
import br.com.sonikro.coliseum.command.lobby.InitializeLobbyCMD;
import br.com.sonikro.coliseum.command.lobby.RemoveUserFromLobbyCMD;
import br.com.sonikro.coliseum.command.server.GetAvailableServerCMD;
import br.com.sonikro.coliseum.dao.ServerDAO;
import br.com.sonikro.coliseum.entity.GameType;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.coliseum.resources.model.RequestNewLobby;
import br.com.sonikro.coliseum.security.Secure;
import br.com.sonikro.command.BaseCommand;
import br.com.sonikro.command.ChainCommand;
import br.com.sonikro.command.ChainCommandBuilder;

@Path("lobby") @Secure(authenticator="BASIC_AUTH")
public class LobbyResource extends BaseResource{
	

	@Path("/requestNewLobby")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Lobby initializeLobby(RequestNewLobby request) throws Exception
	{
		
		GameType gameType = gameTypeDAO.find(request.gameType_id);
		
		BaseCommand getAvailableServer = cmdBuilder.setCommandClass(GetAvailableServerCMD.class)
												   .initializeWith(new ServerDAO(serverDAO), gameType.getNumber_of_players())
												   .build();
		
		BaseCommand initializeLobby = cmdBuilder.setCommandClass(InitializeLobbyCMD.class)
											    .initializeWith(lobbyDAO, gameType)
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
	
	@Path("/{lobbyId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Lobby getLobby(@PathParam("lobbyId") Long lobbyId)
	{
		Lobby lobby = lobbyDAO.find(lobbyId);
		lobby.getUsers(); //Lazy
		lobby.getTeams();
		return lobby;
	}
	
	@Path("/{lobbyId}/addUser")
	@POST
	public void addUserToLobby(@PathParam("lobbyId") Long lobbyId, User user) throws Exception
	{
		Lobby lobby = lobbyDAO.find(lobbyId);
		User managedUser = userDAO.find(user.getId());
		
		
		BaseCommand command = cmdBuilder.setCommandClass(AddUserToLobbyCMD.class)
			      						.initializeWith(lobbyUserDAO,lobby,managedUser)
			      						.build();
		command.dispatch();
		command.throwException();
		
	}
	
	@Path("/{lobbyId}/removeUser")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void removeUserFromLobby(@PathParam("lobbyId") Long lobbyId, User user) throws Exception
	{
		Lobby lobby = lobbyDAO.find(lobbyId);
		User managedUser = userDAO.find(user.getId());
		
		BaseCommand command = cmdBuilder.setCommandClass(RemoveUserFromLobbyCMD.class)
										.initializeWith(lobbyUserDAO,lobby,managedUser)
										.build();
		command.dispatch();
		command.throwException();
		
	}
	
	@Path("/{lobbyId}/team/{lobbyTeamId}/addPlayer")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void addPlayerToTeam(@PathParam("lobbyId") Long lobbyId, @PathParam("lobbyTeamId") Long lobbyTeamId)
	{
		
	}
	
	
}
