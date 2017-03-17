package br.com.sonikro.coliseum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity //@IdClass(LobbyUserKey.class)
public class LobbyUser {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne 
	private User user;
	/*
	@ManyToOne
	private Lobby lobby;*/
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	/*
	public Lobby getLobby() {
		return lobby;
	}
	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}*/
	public Long getLobby_user_id() {
		return id;
	}
	public void setLobby_user_id(Long lobby_user_id) {
		this.id = lobby_user_id;
	}
	@Override
	public boolean equals(Object obj) {
		LobbyUser user = (LobbyUser) obj;
		return this.getUser().getId().equals(user.getUser().getId());
	}
	
	
}
