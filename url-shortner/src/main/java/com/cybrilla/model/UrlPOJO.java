package com.cybrilla.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="URL_Table")
public class UrlPOJO {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	private int id;
	private String url;
	@Column(unique = true)
	private String shortURl;
	
	public UrlPOJO(int id, String url, String shortURl) {
		super();
		this.id = id;
		this.url = url;
		this.shortURl = shortURl;
	}
	
	public UrlPOJO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public String getShortURl() {
		return shortURl;
	}
	public void setShortURl(String shortURl) {
		this.shortURl = shortURl;
	}
	
	@Override
	public String toString() {
		return "Url [id=" + id + ", url=" + url + ", shortURl=" + shortURl + "]";
	}
	
	
	
}
