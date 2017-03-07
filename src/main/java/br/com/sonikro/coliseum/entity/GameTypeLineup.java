package br.com.sonikro.coliseum.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

import br.com.sonikro.coliseum.entity.key.GameTypeLineupKey;

@Entity @IdClass(GameTypeLineupKey.class)
public class GameTypeLineup {
	@Id @ManyToOne 
	private GameType gameType;
	@Id @ManyToOne
	private GameClass gameClass;
	
	private Integer quantity;
	
	
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	public GameClass getGameClass() {
		return gameClass;
	}
	public void setGameClass(GameClass gameClass) {
		this.gameClass = gameClass;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
