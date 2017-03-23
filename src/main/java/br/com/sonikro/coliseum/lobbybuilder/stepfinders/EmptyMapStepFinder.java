package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;

@LobbyStepFinder(step_code="SELECT_MAP",step_sequence=4)
public class EmptyMapStepFinder implements ILobbyStepFinder{

	@Override
	public LobbyBuilderStep findStep(Lobby lobby) {
		if(lobby.getMap() == null)
		{
			LobbyBuilderStep step = new LobbyBuilderStep();
			step.setDesription("Select the MAP");
			step.getActionKeys().put("{lobbyId}", lobby.getId().toString());
			return step;
		}
		return null;
	}

}
