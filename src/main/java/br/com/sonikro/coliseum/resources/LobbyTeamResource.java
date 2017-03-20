package br.com.sonikro.coliseum.resources;

import javax.inject.Inject;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.sonikro.coliseum.command.lobby.SetLobbyTeamLeaderCMD;
import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.coliseum.security.Secure;
import br.com.sonikro.command.BaseCommand;

@Path("lobbyTeam") @Secure(authenticator="BASIC_AUTH")
public class LobbyTeamResource extends BaseResource {
	
	@Inject
	private GenericDAO<LobbyTeam> mLobbyTeamDAO;
	
	@Path("/{lobbyTeamId}/setLeader")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public void setTeamLeader(@PathParam("lobbyTeamId") Long lobbyTeamId, User user) throws Exception	{
		LobbyTeam team = mLobbyTeamDAO.find(lobbyTeamId);
		BaseCommand setLeaderCMD = cmdBuilder.setCommandClass(SetLobbyTeamLeaderCMD.class)
										     .initializeWith(team, user)
										     .build();
		
		setLeaderCMD.dispatch();
		
		setLeaderCMD.throwException();
		
	}
}
