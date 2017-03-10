package br.com.sonikro.coliseum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class ServerCFG {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String cfg_url;
	@Column(nullable=false)
	private String name;
	@ManyToOne
	private GameType gameType;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCfg_url() {
		return cfg_url;
	}
	public void setCfg_url(String cfg_url) {
		this.cfg_url = cfg_url;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public GameType getGameType() {
		return gameType;
	}
	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}
	
	@Override
	public String toString() {
		return "TO-DO";
	}
	
	
}
