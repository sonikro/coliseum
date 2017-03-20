package br.com.sonikro.coliseum.command.lobby;

import org.jboss.logging.Logger;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class SetLobbyTeamLeaderCMD extends BaseResourceCMD<LobbyTeam>{
	private static Logger logger = Logger.getLogger(SetLobbyTeamLeaderCMD.class);
	
	@CmdStarterVar @CmdResultVar(name="lobbyTeam")
	private LobbyTeam mLobbyTeam;
	
	@CmdStarterVar
	private User mNewLeader;
	
	@Override
	public void execute() throws Exception {
		for (LobbyTeam lobbyTeam : mLobbyTeam.getLobby().getTeams()) {
			if(lobbyTeam.getLeader()!= null && lobbyTeam.getLeader().equals(mNewLeader))
			{
				logger.info("User "+mNewLeader.getNickname()+" already a leader. Removing him");
				lobbyTeam.setLeader(null); //If the user is the leader of another team, removes it
			}
		}
		mLobbyTeam.setLeader(mNewLeader);
	}

}
