package br.com.sonikro.coliseum.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.annotations.Cascade;

import br.com.sonikro.coliseum.enumerators.LobbyStatus;

@Entity
public class Lobby implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Server server;
	
	@ManyToOne
	private GameType gameType;
	
	@ManyToOne
	private ServerCFG serverCFG;
	
	@ManyToOne
	private Map map;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="lobby")
	//@JoinColumn(name="lobby_id",insertable=true,updatable=true)
	private List<LobbyUser> users;
	
	@OneToMany(cascade=CascadeType.ALL,mappedBy="lobby")
	//@JoinColumn(name="lobby_id",insertable=true,updatable=true)
	private List<LobbyTeam> teams;
	
	@Enumerated(EnumType.STRING)
	private LobbyStatus status;
	
	
	public ServerCFG getServerCFG() {
		return serverCFG;
	}

	public void setServerCFG(ServerCFG serverCFG) {
		this.serverCFG = serverCFG;
	}

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
	
	public void addTeam(LobbyTeam team)
	{
		if(this.teams == null)
		{
			this.teams = new ArrayList<LobbyTeam>();
		}
		this.teams.add(team);
		team.setLobby(this);
	}
	
	public void addUser(LobbyUser user)
	{
		if(this.users==null)
		{
			this.users = new ArrayList<LobbyUser>();
		}
		this.users.add(user);
		user.setLobby(this);
	}
	
	public void removeUser(LobbyUser user)
	{
		this.users.remove(user);
		//user.setLobby(null);
		
	}
}
