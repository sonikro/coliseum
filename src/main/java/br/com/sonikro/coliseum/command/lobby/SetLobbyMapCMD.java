package br.com.sonikro.coliseum.command.lobby;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.Map;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class SetLobbyMapCMD extends BaseResourceCMD<Lobby> {
	@CmdStarterVar @CmdResultVar(name="lobby")
	private Lobby mLobby;
	@CmdStarterVar
	private Map mMap;
	
	
	@Override
	public void execute() throws Exception {
		mLobby.setMap(mMap);
	}

}
