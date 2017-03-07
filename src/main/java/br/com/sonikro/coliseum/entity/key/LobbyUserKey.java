package br.com.sonikro.coliseum.entity.key;

import java.io.Serializable;

import br.com.sonikro.coliseum.entity.Lobby;
import br.com.sonikro.coliseum.entity.User;

public class LobbyUserKey implements Serializable{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Lobby lobby;
	 User user;
}
