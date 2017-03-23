package br.com.sonikro.coliseum.command.lobby;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class StartLobbyCMD extends BaseResourceCMD<Lobby>{
	@CmdStarterVar @CmdResultVar(name="lobby")
	private Lobby mLobby;
	
	@Override
	public void execute() throws Exception {
		
		
	}

}
