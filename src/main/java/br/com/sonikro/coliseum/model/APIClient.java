package br.com.sonikro.coliseum.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

@Entity
public class APIClient {
	@Id
	private long id;
	
	private String username;
	private String password;
	
	@Column(nullable = false, unique = true)
	private String hostname;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public boolean validate(String username, String password)
	{
		return(username.equals(getUsername()) && password.equals(getPassword()));
	}

	
	
}
