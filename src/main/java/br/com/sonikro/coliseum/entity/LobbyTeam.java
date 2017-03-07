package br.com.sonikro.coliseum.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.sonikro.coliseum.entity.key.LobbyTeamKey;

@Entity @IdClass(LobbyTeamKey.class)
public class LobbyTeam {
	@Id @ManyToOne
	private Lobby lobby;
	@Id 
	private Long team_id;
	@ManyToOne
	private User leader;
	private Boolean is_real_team;
	@OneToMany(mappedBy="lobbyTeam")
	private List<LobbyTeamPlayer> players = new ArrayList<LobbyTeamPlayer>();
	
	
	public List<LobbyTeamPlayer> getPlayers() {
		return players;
	}
	public void setPlayers(List<LobbyTeamPlayer> players) {
		this.players = players;
	}
	public Lobby getLobby() {
		return lobby;
	}
	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}

	public Long getTeam_id() {
		return team_id;
	}
	public void setTeam_id(Long team_id) {
		this.team_id = team_id;
	}
	public User getLeader() {
		return leader;
	}
	public void setLeader(User leader) {
		this.leader = leader;
	}
	public Boolean getIs_real_team() {
		return is_real_team;
	}
	public void setIs_real_team(Boolean is_real_team) {
		this.is_real_team = is_real_team;
	}
	
}
