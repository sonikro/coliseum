package br.com.sonikro.coliseum.command.lobby;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyUser;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.coliseum.entity.key.LobbyUserKey;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;
import br.com.sonikro.command.ICommandListener;

public class RemoveUserFromLobbyCMD extends BaseResourceCMD<LobbyUser>{
	
	@CmdStarterVar
	private Lobby mLobby;
	
	@CmdStarterVar
	private User mUser;
	
	@Override
	public void execute() throws Exception {
		LobbyUserKey key = new LobbyUserKey(mLobby, mUser);
		
		LobbyUser user = mDAO.find(key);
		
		mDAO.delete(user);
		//mLobby.removeUser(user);

	}

}
