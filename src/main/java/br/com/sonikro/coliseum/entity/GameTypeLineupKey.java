package br.com.sonikro.coliseum.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

@Embeddable
public class GameTypeLineupKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private GameType gameType;
	@ManyToOne
	private GameClass gameClass;
	
	public GameTypeLineupKey()
	{
		
	}
	
	public GameTypeLineupKey(GameType gameType, GameClass gameClass)
	{
		setGameClass(gameClass);
		setGameType(gameType);
	}
	
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
	
	
}
