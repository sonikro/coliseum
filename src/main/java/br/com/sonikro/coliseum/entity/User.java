package br.com.sonikro.coliseum.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity @Table(name="ColiseumUser")
public class User {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String steam_id;
	private Boolean banned;
	private String nickname;
	private String logs_page;
	private String demos_page;
	private String avatar_url;
	@ManyToOne
	private Tier tier;
	@ManyToMany
	private List<SystemRole> systemRoles = new ArrayList<SystemRole>();
	
	
	public List<SystemRole> getRoles() {
		return systemRoles;
	}
	public void setRoles(List<SystemRole> roles) {
		this.systemRoles = roles;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSteam_id() {
		return steam_id;
	}
	public void setSteam_id(String steam_id) {
		this.steam_id = steam_id;
	}
	public Boolean getBanned() {
		return banned;
	}
	public void setBanned(Boolean banned) {
		this.banned = banned;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getLogs_page() {
		return logs_page;
	}
	public void setLogs_page(String logs_page) {
		this.logs_page = logs_page;
	}
	public String getDemos_page() {
		return demos_page;
	}
	public void setDemos_page(String demos_page) {
		this.demos_page = demos_page;
	}
	public String getAvatar_url() {
		return avatar_url;
	}
	public void setAvatar_url(String avatar_url) {
		this.avatar_url = avatar_url;
	}
	public Tier getTier() {
		return tier;
	}
	public void setTier(Tier tier) {
		this.tier = tier;
	}
	
	@Override
	public boolean equals(Object obj) {
		User user = (User) obj;
		return user.getId().equals(this.getId());
	}
	
	
}
