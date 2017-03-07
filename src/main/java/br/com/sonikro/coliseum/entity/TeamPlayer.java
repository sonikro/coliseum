package br.com.sonikro.coliseum.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class TeamPlayer {
	@Id
	private TeamPlayerKey key;
	
	private Boolean is_leader;

	public TeamPlayerKey getKey() {
		return key;
	}

	public void setKey(TeamPlayerKey key) {
		this.key = key;
	}

	public Boolean getIs_leader() {
		return is_leader;
	}

	public void setIs_leader(Boolean is_leader) {
		this.is_leader = is_leader;
	}
	
	
}
