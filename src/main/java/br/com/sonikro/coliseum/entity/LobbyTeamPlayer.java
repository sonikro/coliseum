package br.com.sonikro.coliseum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import br.com.sonikro.coliseum.entity.key.LobbyTeamPlayerKey;

@Entity @IdClass(LobbyTeamPlayerKey.class)
public class LobbyTeamPlayer {
	/*@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;*/
	
	@Id @ManyToOne
	private LobbyTeam lobbyTeam;
	@Id @ManyToOne
	private User user;
	@ManyToOne
	private GameClass gameClass;
	/*
	@ManyToOne
	private LobbyTeam lobbyTeam;
	*/
	private Boolean is_ready;
	
	
	@XmlTransient
	public LobbyTeam getLobbyTeam() {
		return lobbyTeam;
	}
	
	public void setLobbyTeam(LobbyTeam lobbyTeam) {
		this.lobbyTeam = lobbyTeam;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public GameClass getGameClass() {
		return gameClass;
	}
	public void setGameClass(GameClass gameClass) {
		this.gameClass = gameClass;
	}
	public Boolean getIs_ready() {
		return is_ready;
	}
	public void setIs_ready(Boolean is_ready) {
		this.is_ready = is_ready;
	}
	/*public LobbyTeam getLobbyTeam() {
		return lobbyTeam;
	}
	public void setLobbyTeam(LobbyTeam lobbyTeam) {
		this.lobbyTeam = lobbyTeam;
	}*/
	
	
	@Override
	public boolean equals(Object obj) {
		LobbyTeamPlayer player = (LobbyTeamPlayer) obj;
		LobbyTeam team = player.getLobbyTeam();
		User user = player.getUser();
		
		return (user.equals(this.user) && team.equals(this.lobbyTeam));
	}
	
}
