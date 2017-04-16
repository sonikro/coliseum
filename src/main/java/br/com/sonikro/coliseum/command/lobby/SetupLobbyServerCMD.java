package br.com.sonikro.coliseum.command.lobby;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.connections.RCONConnection;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.Server;
import br.com.sonikro.coliseum.enumerators.LobbyStatus;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class SetupLobbyServerCMD extends BaseResourceCMD<Lobby>{
	@CmdStarterVar @CmdResultVar(name="lobby")
	private Lobby mLobby;
	
	private Server mServer;
	
	@Override
	public void execute() throws Exception {
		

		mLobby.setStatus(LobbyStatus.SETTING_SERVER_UP);
		mServer = mLobby.getServer();
			
		List<String> rconCommands = new ArrayList<String>();
		RCONConnection rconConnection = mServer.getRCONConnection();
		
		String serverPassword = generatePassword();
		
		rconConnection.open();
		
		rconConnection.executeCmd("changelevel "+mLobby.getMap().getName());
		Thread.sleep(20000); //20 seconds for changelevel

		rconCommands.add("sv_password " +serverPassword);
		rconCommands.add("kickall");
		rconCommands.add(mLobby.getServerCFG().toString());
		rconConnection.executeCommandSequence(rconCommands);
		
		mLobby.setStatus(LobbyStatus.IN_PROGRESS);
		
		rconConnection.close();
		
		mServer.setServer_password(serverPassword);
	}
	
	private String generatePassword()
	{
		return RandomStringUtils.randomAlphanumeric(5, 10);
	}

}
