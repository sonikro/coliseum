package br.com.sonikro.coliseum.command.lobby;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.GameClass;
import br.com.sonikro.coliseum.entity.LobbyUser;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class AddPossibleClassCMD extends BaseResourceCMD<LobbyUser>{

	@CmdStarterVar @CmdResultVar(name="lobbyUser")
	private LobbyUser mLobbyUser;
	
	@CmdStarterVar
	private GameClass mGameClass;
	
	@Override
	public void execute() throws Exception {
		mLobbyUser.addPossibleClass(mGameClass);
	}

}
