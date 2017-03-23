package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import br.com.sonikro.coliseum.entity.GameType;
import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;

@LobbyStepFinder(step_code="TEAM_NOT_FULL")
public class TeamsCompleteSF implements ILobbyStepFinder{

	@Override
	public LobbyBuilderStep findStep(Lobby lobby) throws Exception {
		GameType gameType = lobby.getGameType();
		for (LobbyTeam lobbyTeam : lobby.getTeams()) {
			if(lobbyTeam.getPlayers().size() < ( gameType.getNumber_of_players() / 2 ))
			{
				LobbyBuilderStep step = new LobbyBuilderStep();
				step.setDesription("Teams are not ready yet");
				step.getActionKeys().put("{lobbyTeamId}", lobbyTeam.getId().toString());
				return step;
			}
		}
		return null;
	}

}
