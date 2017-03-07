package br.com.sonikro.coliseum.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class TeamPlayerKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private Team team;
	@ManyToOne
	private User player;
	
	public TeamPlayerKey()
	{
		
	}
	
	public TeamPlayerKey(Team team, User user)
	{
		setTeam(team);
		setPlayer(user);
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
	
}
