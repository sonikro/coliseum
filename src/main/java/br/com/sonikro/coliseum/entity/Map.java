package br.com.sonikro.coliseum.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Map {
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String name;
	private String map_url;
	private String image_url;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMap_url() {
		return map_url;
	}
	public void setMap_url(String map_url) {
		this.map_url = map_url;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	
}
