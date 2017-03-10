package br.com.sonikro.coliseum.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.sonikro.coliseum.enumerators.LobbyStatus;

@Entity
public class Lobby {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch=FetchType.LAZY)
	private Server server;
	@ManyToOne
	private GameType gameType;
	@ManyToOne
	private Map map;
	@OneToMany(mappedBy="lobby")
	private List<LobbyUser> users = new ArrayList<LobbyUser>();
	@OneToMany(mappedBy="lobby")
	private List<LobbyTeam> teams = new ArrayList<LobbyTeam>();
	@Enumerated(EnumType.STRING)
	private LobbyStatus status;
	
	
	public List<LobbyUser> getUsers() {
		return users;
	}

	public void setUsers(List<LobbyUser> users) {
		this.users = users;
	}

	public List<LobbyTeam> getTeams() {
		return teams;
	}

	public void setTeams(List<LobbyTeam> teams) {
		this.teams = teams;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public LobbyStatus getStatus() {
		return status;
	}

	public void setStatus(LobbyStatus status) {
		this.status = status;
	}
	
	
}
