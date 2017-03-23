package br.com.sonikro.coliseum.command.lobby;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.jboss.logging.Logger;

import br.com.sonikro.coliseum.command.BaseResourceCMD;
import br.com.sonikro.coliseum.entity.GameClass;
import br.com.sonikro.coliseum.entity.GameTypeLineup;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.LobbyTeamPlayer;
import br.com.sonikro.coliseum.entity.User;
import br.com.sonikro.command.CmdResultVar;
import br.com.sonikro.command.CmdStarterVar;

public class AddPlayerToTeamCMD extends BaseResourceCMD<LobbyTeamPlayer>{
	private static Logger logger = Logger.getLogger(AddPlayerToTeamCMD.class);
	
	@CmdStarterVar
	private LobbyTeam mLobbyTeam;
	
	@CmdStarterVar
	private User mUser;
	
	@CmdStarterVar
	private GameClass mGameClass;
	
	@CmdResultVar(name="lobbyTeamPlayer")
	private LobbyTeamPlayer mLobbyTeamPlayer;
	
	@Override
	public void execute() throws Exception {
		
		
		
		mLobbyTeamPlayer = new LobbyTeamPlayer();
		mLobbyTeamPlayer.setGameClass(mGameClass);
		mLobbyTeamPlayer.setIs_ready(false);
		mLobbyTeamPlayer.setLobbyTeam(mLobbyTeam);
		mLobbyTeamPlayer.setUser(mUser);
		
		mLobbyTeam.addTeamPlayer(mLobbyTeamPlayer);
		
		validate_class_quantity();
		//mDAO.insert(mLobbyTeamPlayer);
		
	}

	private void validate_class_quantity() throws Exception {
		Lobby lobby = mLobbyTeam.getLobby();
		Set<GameTypeLineup> fullLineup = lobby.getGameType().getLineup();
		List<LobbyTeamPlayer> players = mLobbyTeam.getPlayers();
		Integer balance = 0;
		for (GameTypeLineup lineup : fullLineup) {
			logger.info("Validation quantity of class "+lineup.getGameClass().getName());
			balance = lineup.getQuantity();
			for (LobbyTeamPlayer lobbyTeamPlayer : players) { //Validate class quantity per team
				if(lobbyTeamPlayer.getGameClass().equals(lineup.getGameClass()))
				{
					logger.info("Player "+lobbyTeamPlayer.getUser().getNickname()+ " is playing "+lineup.getGameClass().getName());
					balance--;
				}
			}
			if(balance < 0)
			{
				throw new Exception("Too many "+lineup.getGameClass().getName()+ " on team "+mLobbyTeam.getName());
			}

		}
		
				
	}

}
