package br.com.sonikro.coliseum.entity.key;

import java.io.Serializable;

import br.com.sonikro.coliseum.entity.LobbyTeam;
import br.com.sonikro.coliseum.entity.User;

public class LobbyTeamPlayerKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	LobbyTeam lobbyTeam;
	User user;
}
