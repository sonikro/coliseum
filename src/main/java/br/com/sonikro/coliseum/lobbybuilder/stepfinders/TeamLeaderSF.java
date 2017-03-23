package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;

@LobbyStepFinder(step_code="SET_TEAM_LEADER",step_sequence=1)
public class TeamLeaderSF implements ILobbyStepFinder{
	private static final String actionVerb = "POST";
	@Override
	public LobbyBuilderStep findStep(Lobby lobby) {
		for (LobbyTeam lobbyTeam : lobby.getTeams()) {
			if(lobbyTeam.getLeader() == null)
			{
				LobbyBuilderStep step = new LobbyBuilderStep();
				step.setDesription("Team "+lobbyTeam.getName()+" needs a leader");
				step.getActionKeys().put("{lobbyTeamId}", lobbyTeam.getId().toString());
				return step;
			}
		}
		return null;
	}


}
