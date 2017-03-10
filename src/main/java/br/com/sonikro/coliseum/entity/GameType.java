package br.com.sonikro.coliseum.entity;

import java.util.List;

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
	@OneToMany(mappedBy="gameType")
	private List<GameTypeLineup> lineup;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<GameTypeLineup> getLineup() {
		return lineup;
	}
	public void setLineup(List<GameTypeLineup> lineup) {
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
	
	
}
