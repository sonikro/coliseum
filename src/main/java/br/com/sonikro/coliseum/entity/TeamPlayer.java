package br.com.sonikro.coliseum.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import br.com.sonikro.coliseum.entity.key.TeamPlayerKey;

@Entity @IdClass(TeamPlayerKey.class)
public class TeamPlayer {
	@Id @ManyToOne
	private Team team;
	@Id @ManyToOne
	private User player;
	
	private Boolean is_leader;
	@OneToMany(mappedBy="player")
	private List<TeamPlayerRole> roles = new ArrayList();
	
	public List<TeamPlayerRole> getRoles() {
		return roles;
	}

	public void setRoles(List<TeamPlayerRole> roles) {
		this.roles = roles;
	}

	

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public User getPlayer() {
		return player;
	}

	public void setPlayer(User player) {
		this.player = player;
	}

	public Boolean getIs_leader() {
		return is_leader;
	}

	public void setIs_leader(Boolean is_leader) {
		this.is_leader = is_leader;
	}
	
	
}
