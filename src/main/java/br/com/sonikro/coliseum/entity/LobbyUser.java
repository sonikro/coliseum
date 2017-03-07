package br.com.sonikro.coliseum.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import br.com.sonikro.coliseum.entity.key.LobbyUserKey;

@Entity @IdClass(LobbyUserKey.class)
public class LobbyUser {
	/*@Id
	private LobbyUserKey key;*/
	@Id @ManyToOne 
	private Lobby lobby;
	@Id @ManyToOne
	private User user;
	
	public Lobby getLobby() {
		return lobby;
	}
	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
}
