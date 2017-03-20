package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;

public interface ILobbyStepFinder {
	LobbyBuilderStep findStep(Lobby lobby) throws Exception;
}
