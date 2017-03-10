package br.com.sonikro.coliseum.resources;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.command.lobby.InitializeLobbyCMD;
import br.com.sonikro.coliseum.command.server.GetAvailableServerCMD;
import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.dao.ServerDAO;
import br.com.sonikro.coliseum.entity.GameType;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.resources.request.RequestNewLobby;
import br.com.sonikro.coliseum.security.Secure;
import br.com.sonikro.command.BaseCommand;
import br.com.sonikro.command.ChainCommand;

@Path("lobby") @Secure(authenticator="BASIC_AUTH")
public class LobbyResource extends BaseResource{
	@Inject
	private GenericDAO<GameType> gameTypeDAO;
	@Inject
	private GenericDAO<Server> serverDAO;
	@Inject
	private GenericDAO<Lobby> lobbyDAO;
	
	@Path("/requestNewLobby")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Transactional 
	public Lobby initializeLobby(RequestNewLobby request) throws Exception
	{
		
		GameType gameType = gameTypeDAO.find(request.gameType_id);
		
		BaseResourceCMD<Server> getServercommand = new GetAvailableServerCMD(this,new ServerDAO(serverDAO),gameType.getNumber_of_players());
		BaseResourceCMD<Lobby> initializeLobbyCommand = new InitializeLobbyCMD(this, lobbyDAO, gameType);
		
		List<BaseCommand> commandList = new ArrayList<BaseCommand>();
		commandList.add(getServercommand);
		commandList.add(initializeLobbyCommand);
		
		ChainCommand chainCommand = new ChainCommand(this, commandList);
		
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
		return lobbyDAO.find(lobbyId);
	}
	
}
