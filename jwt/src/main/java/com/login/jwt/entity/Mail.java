package com.login.jwt.entity;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table
public class Mail {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String destinaire;
	private String objet;
	private String message;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getDestinaire() {
		return destinaire;
	}
	public void setDestinaire(String destinaire) {
		this.destinaire = destinaire;
	}
	public String getObjet() {
		return objet;
	}
	public void setObjet(String objet) {
		this.objet = objet;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	public Mail() {
		super();
	}
	
	public Mail(Long id, String destinaire, String objet, String message) {
		super();
		this.id = id;
		this.destinaire = destinaire;
		this.objet = objet;
		this.message = message;
	}
	@Override
	public String toString() {
		return "Mail [id=" + id + ", destinaire=" + destinaire + ", objet=" + objet + ", message=" + message + "]";
	}
	
	
	
	
}
