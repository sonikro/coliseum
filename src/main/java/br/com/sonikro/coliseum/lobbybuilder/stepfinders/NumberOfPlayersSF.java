package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;

@LobbyStepFinder(step_code="NOT_ENOUGH_PLAYERS", step_sequence=0)
public class NumberOfPlayersSF implements ILobbyStepFinder{

	@Override
	public LobbyBuilderStep findStep(Lobby lobby) {
		if(lobby.getUsers().size() < lobby.getGameType().getNumber_of_players())
		{
			LobbyBuilderStep step = new LobbyBuilderStep();
			step.setDesription("Not enough players. Need "+lobby.getGameType().getNumber_of_players()+
					" , has "+lobby.getUsers().size());
			step.setActionVerb("POST");
			step.setActionPath(buildActionPath(lobby));
			return step;
		}
		return null;
	}

	private String buildActionPath(Lobby lobby) {
		return "/lobby/"+lobby.getId()+"/addUser/{userId}";
	}

}
