package br.com.sonikro.coliseum.command.lobby;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.dao.GenericDAO;
import br.com.sonikro.coliseum.entity.GameType;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.enumerators.LobbyStatus;
import br.com.sonikro.coliseum.enumerators.ServerStatus;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;
import br.com.sonikro.command.ICommandListener;

public class InitializeLobbyCMD extends BaseResourceCMD<Lobby>{


	@CmdStarterVar
	protected Server mServer;
	@CmdStarterVar
	protected GameType mGameType;
	@CmdResultVar(name="lobby")
	protected Lobby mLobby;
	

	@Override
	public void execute() throws Exception {
		mLobby = new Lobby();
		mLobby.setGameType(mGameType);
		mLobby.setServer(mServer);
		mLobby.setStatus(LobbyStatus.BUILDING);
		mServer.setStatus(ServerStatus.BUSY);
		mDAO.insert(mLobby);
	}


}
