package br.com.sonikro.coliseum.entity;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class GameTypeLineup {
	@Id 
	private GameTypeLineupKey key;
	private Integer quantity;
	
	public GameTypeLineupKey getKey() {
		return key;
	}
	public void setKey(GameTypeLineupKey key) {
		this.key = key;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
}
