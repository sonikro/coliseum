package br.com.sonikro.coliseum.resources;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.command.lobby.AddPlayerToTeamCMD;
import br.com.sonikro.coliseum.command.lobby.RemoveLobbyTeamLeaderCMD;
import br.com.sonikro.coliseum.command.lobby.RemovePlayerFromTeamCMD;
import br.com.sonikro.coliseum.command.lobby.SetLobbyTeamLeaderCMD;
import br.com.sonikro.coliseum.command.lobby.SetPlayerReadyStatusCMD;
import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.entity.GameClass;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.LobbyTeamPlayer;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.coliseum.entity.key.LobbyTeamPlayerKey;
import br.com.sonikro.coliseum.lobbybuilder.SolvesLobbyStep;
import br.com.sonikro.coliseum.security.Secure;
import br.com.sonikro.command.BaseCommand;

@Path("lobbyTeam") @Secure(authenticator="BASIC_AUTH")
public class LobbyTeamResource extends BaseResource {
	
	@Inject
	private GenericDAO<LobbyTeam> mLobbyTeamDAO;
	
	@Inject
	private GenericDAO<LobbyTeamPlayer> mLobbyTeamPlayerDAO;
	
	@Path("/{lobbyTeamId}/setLeader/{leaderUserId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@SolvesLobbyStep(step_code="SET_TEAM_LEADER")
	public void setTeamLeader(@PathParam("lobbyTeamId") Long lobbyTeamId, @PathParam("leaderUserId") Long userId) 
			throws Exception	{
		LobbyTeam team = mLobbyTeamDAO.find(lobbyTeamId);
		User leader = mUserDAO.find(userId);
		BaseCommand setLeaderCMD = cmdBuilder.setCommandClass(SetLobbyTeamLeaderCMD.class)
										     .initializeWith(team, leader)
										     .build();
		
		setLeaderCMD.dispatch();
		
		setLeaderCMD.throwException();
		
	}
	
	@Path("/{lobbyTeamId}/removeLeader")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void removeTeamLeader(@PathParam("lobbyTeamId") Long lobbyTeamId)
		throws Exception
	{
		LobbyTeam team = mLobbyTeamDAO.find(lobbyTeamId);
		
		BaseCommand removeLeaderCMD = cmdBuilder.setCommandClass(RemoveLobbyTeamLeaderCMD.class)
										     	.initializeWith(team)
										     	.build();
		
		removeLeaderCMD.dispatch();
		
		removeLeaderCMD.throwException();
		
	}
	
	@SolvesLobbyStep(step_code="TEAM_NOT_FULL")
	@Path("/{lobbyTeamId}/addPlayer/{userId}/class/{gameClassId}")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public LobbyTeamPlayer addPlayerToTeam(@PathParam("lobbyTeamId") Long lobbyTeamId,
										   @PathParam("userId") Long userId,
										   @PathParam("gameClassId") Long gameClassId) throws Exception
	{
		LobbyTeam team = mLobbyTeamDAO.find(lobbyTeamId);
		User user = mUserDAO.find(userId);
		GameClass gameClass = mGameClassDAO.find(gameClassId);
		
		BaseCommand addPlayerToTeam = cmdBuilder.setCommandClass(AddPlayerToTeamCMD.class)
												.initializeWith(mLobbyTeamPlayerDAO, team, user, gameClass)
												.build();
		
		addPlayerToTeam.dispatch();
		
		addPlayerToTeam.throwException();
		
		return (LobbyTeamPlayer) addPlayerToTeam.getResult("lobbyTeamPlayer");
	}
	
	@Path("/{lobbyTeamId}/removePlayer/{userId}")
	@POST
	public void removePlayerFromTeam(@PathParam("lobbyTeamId") Long lobbyTeamId,
									 @PathParam("userId") Long userId) throws Exception
	{
		LobbyTeam team = mLobbyTeamDAO.find(lobbyTeamId);
		User user = mUserDAO.find(userId);
		LobbyTeamPlayerKey key = new LobbyTeamPlayerKey(team,user);
		
		LobbyTeamPlayer teamPlayer = mLobbyTeamPlayerDAO.find(key);
		
		BaseCommand removePlayerFromTeam = cmdBuilder.setCommandClass(RemovePlayerFromTeamCMD.class)
													 .initializeWith(mLobbyTeamPlayerDAO, team, teamPlayer)
													 .build();
		
		removePlayerFromTeam.dispatch();
		
		removePlayerFromTeam.throwException();
	
	}
	
	@SolvesLobbyStep(step_code="PLAYER_READY")
	@Path("/{lobbyTeamId}/player/{userId}/ready")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public LobbyTeamPlayer setPlayerStatus(@PathParam("lobbyTeamId")Long lobbyTeamId,
										   @PathParam("userId") Long userId,
										   @QueryParam("status") Boolean status) throws Exception
	{
		LobbyTeam team = mLobbyTeamDAO.find(lobbyTeamId);
		User user = mUserDAO.find(userId);
		LobbyTeamPlayerKey key = new LobbyTeamPlayerKey(team,user);
		
		LobbyTeamPlayer teamPlayer = mLobbyTeamPlayerDAO.find(key);
		
		BaseCommand setPlayerStatus = cmdBuilder.setCommandClass(SetPlayerReadyStatusCMD.class)
												.initializeWith(teamPlayer, status)
												.build();
		
		setPlayerStatus.dispatch();
		
		setPlayerStatus.throwException();
		
		return teamPlayer;
	}
}
