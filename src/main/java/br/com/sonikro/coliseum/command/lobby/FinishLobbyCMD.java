package br.com.sonikro.coliseum.command.lobby;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.connections.RCONConnection;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.enumerators.LobbyStatus;
import br.com.sonikro.coliseum.enumerators.ServerStatus;
import br.com.sonikro.command.CmdStarterVar;

public class FinishLobbyCMD extends BaseResourceCMD<Lobby>{
	
	@CmdStarterVar
	private Lobby mLobby;
	
	private Server mServer;
	
	@Override
	public void execute() throws Exception {
		
		mServer = mLobby.getServer();
		
		mLobby.setStatus(LobbyStatus.FINISHED);
		
		mServer.setStatus(ServerStatus.AVAILABLE);
		mServer.resetPassword();
		
		RCONConnection rconConnection = mServer.getRCONConnection();
		rconConnection.open();
		rconConnection.executeCmd("sv_password "+mServer.getDefault_password());
		rconConnection.close();
		
	}

}
