package br.com.sonikro.coliseum.entity.key;

import java.io.Serializable;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.Team;

public class LobbyTeamKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Lobby lobby;
	Long team_id;

}
