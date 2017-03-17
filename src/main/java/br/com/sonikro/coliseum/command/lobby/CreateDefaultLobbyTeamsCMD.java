package br.com.sonikro.coliseum.command.lobby;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.Team;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;
import br.com.sonikro.command.ICommandListener;

public class CreateDefaultLobbyTeamsCMD extends BaseResourceCMD<LobbyTeam>{

	@CmdStarterVar @CmdResultVar(name="lobby")
	private Lobby mLobby;
	
	@CmdResultVar(name="teamBLU")
	private LobbyTeam mTeamBLU;
	
	@CmdResultVar(name="teamRED")
	private LobbyTeam mTeamRED;
	

	@Override
	public void execute() throws Exception {
		mTeamBLU = new LobbyTeam();
		
		//mTeamBLU.setLobby(mLobby);
		mTeamBLU.setName("BLU");
		
		mTeamRED = new LobbyTeam();

		//mTeamRED.setLobby(mLobby);
		mTeamRED.setName("RED");
		
		//mDAO.insert(mTeamBLU);
		//mDAO.insert(mTeamRED);
		mLobby.addTeam(mTeamBLU);
		mLobby.addTeam(mTeamRED);
		
	}

}
