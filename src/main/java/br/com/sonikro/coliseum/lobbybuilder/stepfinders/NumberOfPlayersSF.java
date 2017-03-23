package br.com.sonikro.coliseum.lobbybuilder.stepfinders;

import java.lang.annotation.Annotation;

import javax.ws.rs.Path;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.lobbybuilder.LobbyBuilderStep;
import br.com.sonikro.coliseum.resources.LobbyResource;
import br.com.sonikro.coliseum.util.ReflectionTool;

@LobbyStepFinder(step_code="NOT_ENOUGH_PLAYERS", step_sequence=0)
public class NumberOfPlayersSF implements ILobbyStepFinder{

	@Override
	public LobbyBuilderStep findStep(Lobby lobby) {
		if(lobby.getUsers().size() < lobby.getGameType().getNumber_of_players())
		{
			LobbyBuilderStep step = new LobbyBuilderStep();
			step.setDesription("Not enough players. Need "+lobby.getGameType().getNumber_of_players()+
					" , has "+lobby.getUsers().size());
			step.getActionKeys().put("{lobbyId}", lobby.getId().toString());
			return step;
		}
		return null;
	}



}
