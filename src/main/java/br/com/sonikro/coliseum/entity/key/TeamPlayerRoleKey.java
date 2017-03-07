package br.com.sonikro.coliseum.entity.key;

import java.io.Serializable;

import br.com.sonikro.coliseum.entity.GameClass;
import br.com.sonikro.coliseum.entity.TeamPlayer;


public class TeamPlayerRoleKey implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	TeamPlayer player;
	
	GameClass gameClass;

	

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	

}
