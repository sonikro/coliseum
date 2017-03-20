package br.com.sonikro.coliseum.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlTransient;

import br.com.sonikro.coliseum.entity.key.LobbyUserKey;

@Entity @IdClass(LobbyUserKey.class)
public class LobbyUser {
	
	@Id @ManyToOne
	private Lobby lobby;
	@Id @ManyToOne 
	private User user;
	
	@ManyToMany
	private Set<GameClass> possibleClasses;
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
	
	public void addPossibleClass(GameClass gameClass)
	{
		if(this.possibleClasses==null)
		{
			this.possibleClasses = new HashSet<GameClass>();
		}
		this.possibleClasses.add(gameClass);
	}
	
	public void removePossibleClass(GameClass gameClass)
	{
		if(this.possibleClasses!=null)
		{
			this.possibleClasses.remove(gameClass);
		}
	}
	public Set<GameClass> getPossibleClasses() {
		return possibleClasses;
	}
	public void setPossibleClasses(Set<GameClass> possibleClasses) {
		this.possibleClasses = possibleClasses;
	}
	@XmlTransient
	public Lobby getLobby() {
		return lobby;
	}
	public void setLobby(Lobby lobby) {
		this.lobby = lobby;
	}
	
	@Override
	public boolean equals(Object obj) {
		LobbyUser user = (LobbyUser) obj;
		return this.getUser().getId().equals(user.getUser().getId());
		
	}
	
	
}
