package br.com.sonikro.coliseum.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.ColumnTransformer;

import br.com.sonikro.coliseum.connections.RCONConnection;
import br.com.sonikro.coliseum.enumerators.ServerStatus;

@Entity
public class Server {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(nullable=false)
	private String ip;
	@Column(nullable=false)
	private Integer port;
	@ColumnTransformer(
	        read =  "pgp_sym_decrypt(rcon_password::bytea,current_setting('encrypt.key')::text)",
	        write = "pgp_sym_encrypt(?::text,current_setting('encrypt.key')::text)"
	    )
	@Column(columnDefinition = "bytea")
	private String rcon_password;
	private Integer sourcetv_port;	
	private String sourcetv_password;
	private String hostname;
	@Enumerated(EnumType.STRING)
	private ServerStatus status;
	@Column(nullable=false)
	private Integer number_of_slots;
	private String ftp_user;
	private Integer ftp_port;
	private String ftp_password;
	private String server_password;
	private String default_password;
	
	

	@ManyToMany
	private List<Map> maps;
	
	
	public String getDefault_password() {
		return default_password;
	}
	public void setDefault_password(String default_password) {
		this.default_password = default_password;
	}

	
	public List<Map> getMaps() {
		return maps;
	}
	public void setMaps(List<Map> maps) {
		this.maps = maps;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Integer getPort() {
		return port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public String getRcon_password() {
		return rcon_password;
	}
	public void setRcon_password(String rcon_password) {
		this.rcon_password = rcon_password;
	}
	public Integer getSourcetv_port() {
		return sourcetv_port;
	}
	public void setSourcetv_port(Integer sourcetv_port) {
		this.sourcetv_port = sourcetv_port;
	}
	public String getSourcetv_password() {
		return sourcetv_password;
	}
	public void setSourcetv_password(String sourcetv_password) {
		this.sourcetv_password = sourcetv_password;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	public ServerStatus getStatus() {
		return status;
	}
	public void setStatus(ServerStatus status) {
		this.status = status;
	}
	public Integer getNumber_of_slots() {
		return number_of_slots;
	}
	public void setNumber_of_slots(Integer number_of_slots) {
		this.number_of_slots = number_of_slots;
	}
	public String getFtp_user() {
		return ftp_user;
	}
	public void setFtp_user(String ftp_user) {
		this.ftp_user = ftp_user;
	}
	public Integer getFtp_port() {
		return ftp_port;
	}
	public void setFtp_port(Integer ftp_port) {
		this.ftp_port = ftp_port;
	}
	public String getFtp_password() {
		return ftp_password;
	}
	public void setFtp_password(String ftp_password) {
		this.ftp_password = ftp_password;
	}
	public String getServer_password() {
		return server_password;
	}
	public void setServer_password(String server_password) {
		this.server_password = server_password;
	}
	
	public RCONConnection getRCONConnection()
	{
		return new RCONConnection(ip, port, rcon_password);
	}
	public void resetPassword() {
		setServer_password(getDefault_password());
	}
	
}
