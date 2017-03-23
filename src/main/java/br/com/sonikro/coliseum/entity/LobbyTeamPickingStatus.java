package br.com.sonikro.coliseum.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class LobbyTeamPickingStatus implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id @OneToOne
	private LobbyTeam lobbyTeam;
	
	private Boolean turn_to_pick;
	
	private Integer picks_at_turn;

	public LobbyTeam getLobbyTeam() {
		return lobbyTeam;
	}

	public void setLobbyTeam(LobbyTeam lobbyTeam) {
		this.lobbyTeam = lobbyTeam;
	}

	public Boolean getTurn_to_pick() {
		return turn_to_pick;
	}

	public void setTurn_to_pick(Boolean turn_to_pick) {
		this.turn_to_pick = turn_to_pick;
	}

	public Integer getPicks_at_turn() {
		return picks_at_turn;
	}

	public void setPicks_at_turn(Integer picks_at_turn) {
		this.picks_at_turn = picks_at_turn;
	}
	
	
	
}
