package br.com.sonikro.coliseum.command.lobby;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.LobbyTeamPlayer;
import br.com.sonikro.command.CmdStarterVar;

public class SetPlayerReadyStatusCMD extends BaseResourceCMD<LobbyTeamPlayer>{
	
	@CmdStarterVar
	private LobbyTeamPlayer mLobbyTeamPlayer;
	
	@CmdStarterVar
	private Boolean mIsReady;
	
	@Override
	public void execute() throws Exception {
		mLobbyTeamPlayer.setIs_ready(mIsReady);
	}

}
