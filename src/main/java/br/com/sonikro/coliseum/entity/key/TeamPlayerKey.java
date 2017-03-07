package br.com.sonikro.coliseum.entity.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import br.com.sonikro.coliseum.entity.Team;
import br.com.sonikro.coliseum.entity.User;


public class TeamPlayerKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Team team;

	User player;


	
}
