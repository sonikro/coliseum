package br.com.sonikro.coliseum.command.lobby;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.GameType;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.entity.ServerCFG;
import br.com.sonikro.coliseum.enumerators.LobbyStatus;
import br.com.sonikro.coliseum.enumerators.ServerStatus;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class InitializeLobbyCMD extends BaseResourceCMD<Lobby>{


	@CmdStarterVar
	protected Server mServer;
	@CmdStarterVar
	protected GameType mGameType;
	@CmdStarterVar
	protected ServerCFG mServerCFG;
	
	@CmdResultVar(name="lobby")
	protected Lobby mLobby;
	

	@Override
	public void execute() throws Exception {
		mLobby = new Lobby();
		mLobby.setGameType(mGameType);
		mLobby.setServer(mServer);
		mLobby.setServerCFG(mServerCFG);
		mLobby.setStatus(LobbyStatus.BUILDING);
		mServer.setStatus(ServerStatus.BUSY);
		mDAO.insert(mLobby);
	}


}
