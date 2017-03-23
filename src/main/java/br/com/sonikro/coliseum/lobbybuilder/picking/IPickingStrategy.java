package br.com.sonikro.coliseum.lobbybuilder.picking;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;

public interface IPickingStrategy {
	public LobbyBuilderStep getNextStep(Lobby lobby);
}
