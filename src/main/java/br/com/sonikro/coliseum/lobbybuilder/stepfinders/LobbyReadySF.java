package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;

@LobbyStepFinder(step_code="READY",step_sequence=10)
public class LobbyReadySF implements ILobbyStepFinder{

	@Override
	public LobbyBuilderStep findStep(Lobby lobby) throws Exception {
		LobbyBuilderStep step = new LobbyBuilderStep();
		step.setDesription("Lobby is ready");
		step.getActionKeys().put("{lobbyId}", lobby.getId().toString());
		return step;
	}

}
