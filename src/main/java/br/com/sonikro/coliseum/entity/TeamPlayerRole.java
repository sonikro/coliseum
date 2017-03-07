package br.com.sonikro.coliseum.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import br.com.sonikro.coliseum.entity.key.TeamPlayerRoleKey;

@Entity @IdClass(TeamPlayerRoleKey.class)
public class TeamPlayerRole {
	@Id @ManyToOne
	private TeamPlayer player;
	@Id @ManyToOne
	private GameClass gameClass;
	private Boolean is_main;
	
	
	
	public TeamPlayer getPlayer() {
		return player;
	}
	public void setPlayer(TeamPlayer player) {
		this.player = player;
	}
	public GameClass getGameClass() {
		return gameClass;
	}
	public void setGameClass(GameClass gameClass) {
		this.gameClass = gameClass;
	}
	public Boolean getIs_main() {
		return is_main;
	}
	public void setIs_main(Boolean is_main) {
		this.is_main = is_main;
	}
	
	

}
