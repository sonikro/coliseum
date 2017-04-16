package br.com.sonikro.coliseum.entity;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import org.jboss.logging.Logger;

@Entity
public class ServerCFG {
	private static Logger logger = Logger.getLogger(ServerCFG.class);
	
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
		try {
			String out = new Scanner(new URL(getCfg_url()).openStream(), "UTF-8").useDelimiter("\\A").next();
			return out;
		} catch (Exception e) {
			logger.error("Error parsing CFG URL to String",e);
		}
		return "";
	}
	
	
}
