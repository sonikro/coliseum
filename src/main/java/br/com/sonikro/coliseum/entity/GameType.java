package br.com.sonikro.coliseum.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class GameType {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private Integer max_rost_number; //Max number of players in a Team of this GameType
	private Integer number_of_players; //Number of players needed for a match
	private String name;
	@OneToMany(mappedBy="gameType",cascade=CascadeType.ALL)
	private Set<GameTypeLineup> lineup;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<GameTypeLineup> getLineup() {
		return lineup;
	}
	public void setLineup(Set<GameTypeLineup> lineup) {
		this.lineup = lineup;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Integer getMax_rost_number() {
		return max_rost_number;
	}
	public void setMax_rost_number(Integer max_rost_number) {
		this.max_rost_number = max_rost_number;
	}
	public Integer getNumber_of_players() {
		return number_of_players;
	}
	public void setNumber_of_players(Integer number_of_players) {
		this.number_of_players = number_of_players;
	}
	
	public void addGameTypeLineUp(GameTypeLineup lineup)
	{
		if(this.lineup == null)
		{
			this.lineup = new HashSet<GameTypeLineup>();
		}
		lineup.setGameType(this);
		this.lineup.add(lineup);
	}
	
	@Override
	public boolean equals(Object obj) {
		return(((GameType)obj).getId().equals(this.getId()));
	}
	
}
