package br.com.sonikro.coliseum.command.lobby;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class RemoveLobbyTeamLeaderCMD extends BaseResourceCMD<LobbyTeam>{
	@CmdStarterVar @CmdResultVar(name="lobbyTeam")
	private LobbyTeam mLobbyTeam;
	

	@Override
	public void execute() throws Exception {
		mLobbyTeam.setLeader(null);
	}

}
