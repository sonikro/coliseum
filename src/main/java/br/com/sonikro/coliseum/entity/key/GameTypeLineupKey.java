package br.com.sonikro.coliseum.entity.key;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.ManyToOne;

import br.com.sonikro.coliseum.entity.GameClass;
import br.com.sonikro.coliseum.entity.GameType;

public class GameTypeLineupKey implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	 GameType gameType;

	 GameClass gameClass;
	
	
	
}
