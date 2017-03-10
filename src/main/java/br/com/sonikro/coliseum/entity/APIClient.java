package br.com.sonikro.coliseum.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.ColumnTransformer;

@Entity
public class APIClient {

	@Id
	private String username;
	@ColumnTransformer(	
	        read =  "pgp_sym_decrypt(password::bytea, current_setting('encrypt.key')::text)",
	        write = "pgp_sym_encrypt(?::text,current_setting('encrypt.key')::text) "
	    )
	@Column(columnDefinition = "bytea")	
	private String password;
	private String hostname;
	

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
