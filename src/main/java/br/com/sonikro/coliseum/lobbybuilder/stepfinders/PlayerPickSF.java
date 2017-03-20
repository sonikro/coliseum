package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;
import br.com.sonikro.coliseum.lobbybuilder.picking.IPickingStrategy;
import br.com.sonikro.coliseum.lobbybuilder.picking.StandardSixPickingStrategy;

@LobbyStepFinder(step_code="PICKING_PLAYERS",step_sequence=3)
public class PlayerPickSF implements ILobbyStepFinder{

	@Override
	public LobbyBuilderStep findStep(Lobby lobby) throws Exception {
		IPickingStrategy strategy = getPickingStrategy(lobby);
		return strategy.getNextStep();
	}
	
	public IPickingStrategy getPickingStrategy(Lobby lobby) throws Exception
	{
		if(lobby.getGameType().getNumber_of_players() == 12)
		{
			return (IPickingStrategy) new StandardSixPickingStrategy();
		}
		throw new Exception("No available Picking Strategy for this kind of Lobby");
	}

}
