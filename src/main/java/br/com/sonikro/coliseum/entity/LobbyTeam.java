package br.com.sonikro.coliseum.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.xml.bind.annotation.XmlTransient;

import br.com.sonikro.coliseum.entity.key.LobbyTeamKey;

@Entity //@IdClass(LobbyTeamKey.class)
public class LobbyTeam implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=true)
	private Team team;
	
	@ManyToOne(optional=true)
	private User leader;
	
	private String name;
	
	@ManyToOne
	private Lobby lobby;
	
	@OneToMany(cascade=CascadeType.ALL, mappedBy="lobbyTeam")
	//@JoinColumn(name="lobby_team_id",insertable=true,updatable=false)
	private List<LobbyTeamPlayer> players = new ArrayList<LobbyTeamPlayer>();
	
	

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<LobbyTeamPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<LobbyTeamPlayer> players) {
		this.players = players;
	}
	
	public User getLeader() {
		return leader;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}

	public Long getLobby_team_id() {
		return id;
	}
	public void setLobby_team_id(Long lobby_team_id) {
		this.id = lobby_team_id;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	
	public void addTeamPlayer(LobbyTeamPlayer player)
	{
		this.players.add(player);
		player.setLobbyTeam(this);
	}
	
	public void removeTeamPlayer(LobbyTeamPlayer player)
	{
		this.players.remove(player);
		//player.setLobbyTeam(null);
	}
	

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	@XmlTransient
	public Lobby getLobby() {
		return lobby;
	}
	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}
	
	
	@Override
	public boolean equals(Object obj) {
		LobbyTeam lobbyTeam = (LobbyTeam) obj;
		return lobbyTeam.getLobby_team_id().equals(this.getLobby_team_id());
	}
	
	
	
}
