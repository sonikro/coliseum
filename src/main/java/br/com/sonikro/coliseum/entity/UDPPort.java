package br.com.sonikro.coliseum.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UDPPort {
	@Id
	private Integer port;
	
	private Boolean in_use;
	
	@ManyToOne
	private Server server;

	

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public Boolean getIn_use() {
		return in_use;
	}

	public void setIn_use(Boolean in_use) {
		this.in_use = in_use;
	}

	public Server getServer() {
		return server;
	}

	public void setServer(Server server) {
		this.server = server;
	}
	
	public void assignToServer(Server server)
	{
		this.setIn_use(true);
		this.setServer(server);
	}
	
	public void release()
	{
		this.setIn_use(false);
		this.setServer(null);
	}
}
