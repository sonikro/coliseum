package br.com.sonikro.coliseum.lobbybuilder.picking;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.LobbyTeamPickingStatus;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;

public class StandardSixPickingStrategy implements IPickingStrategy{

	@Override
	public LobbyBuilderStep getNextStep(Lobby lobby) {
		LobbyBuilderStep step = new LobbyBuilderStep();
		
	/*	for (LobbyTeam lobbyTeam : lobby.getTeams()) {

			if(status.getTurn_to_pick())
			{
				step.setDesription("Team "+lobbyTeam.getName()+" should pick now");
				return step;
			}
		}*/
			
		
		return step;
	}

}
