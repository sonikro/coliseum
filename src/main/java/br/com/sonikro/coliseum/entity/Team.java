package br.com.sonikro.coliseum.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Team {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String avatar_url;
	@ManyToOne
	private GameType gameType;
	@ManyToOne
	private User team_creator;
	
	@OneToMany(mappedBy="team")
	private List<TeamPlayer> players = new ArrayList();
	
	
	public List<TeamPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<TeamPlayer> players) {
		this.players = players;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	public User getTeam_creator() {
		return team_creator;
	}
	public void setTeam_creator(User team_creator) {
		this.team_creator = team_creator;
	}
	
	
}
