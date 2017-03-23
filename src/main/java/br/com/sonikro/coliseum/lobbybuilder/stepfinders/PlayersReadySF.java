package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.LobbyTeamPlayer;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;

@LobbyStepFinder(step_code="PLAYER_READY",step_sequence=5)
public class PlayersReadySF implements ILobbyStepFinder{

	@Override
	public LobbyBuilderStep findStep(Lobby lobby) throws Exception {
		for (LobbyTeam lobbyTeam : lobby.getTeams()) {
			for (LobbyTeamPlayer teamPlayer : lobbyTeam.getPlayers()) {
				if(!teamPlayer.getIs_ready())
				{
					LobbyBuilderStep step = new LobbyBuilderStep();
					step.setDesription("Not all players are ready");
					step.getActionKeys().put("{lobbyId}", lobby.getId().toString());
					step.getActionKeys().put("{lobbyTeamId}", lobbyTeam.getId().toString());
					step.getActionKeys().put("{userId}", teamPlayer.getUser().getId().toString());
					return step;
				}
			}
		}
		return null;
	}

}
