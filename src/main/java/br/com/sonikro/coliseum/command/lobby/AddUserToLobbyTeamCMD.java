package br.com.sonikro.coliseum.command.lobby;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.GameClass;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.LobbyTeamPlayer;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class AddUserToLobbyTeamCMD extends BaseResourceCMD<LobbyTeam>{
	@CmdStarterVar
	private LobbyTeam mLobbyTeam;
	@CmdStarterVar
	private User mUser;
	@CmdStarterVar
	private GameClass mGameClass;
	
	@CmdResultVar(name="teamPlayer")
	private LobbyTeamPlayer mLobbyTeamPlayer;
	@Override
	public void execute() throws Exception {
		mLobbyTeamPlayer = new LobbyTeamPlayer();
		mLobbyTeamPlayer.setUser(mUser);
		mLobbyTeamPlayer.setGameClass(mGameClass);
		mLobbyTeamPlayer.setIs_ready(false);
		
		mLobbyTeam.addTeamPlayer(mLobbyTeamPlayer);
		
	}

}
