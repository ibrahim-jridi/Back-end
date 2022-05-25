package com.login.jwt.entity;

import java.io.Serializable;
import java.net.URL;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer","handler"})
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	private String prix;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idTheme")
	private Theme theme;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="idFormatter")
	private Formatter formatter;
	
	
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name = "guest_id")
	@JsonIgnore
    private Guest guest;
	
	
	private String lien;
	
	 @DateTimeFormat(pattern = "dd-MM-yyyy")
	private ZonedDateTime date_creation = ZonedDateTime.now();
	 @DateTimeFormat(pattern = "dd-MM-yyyy")
	private Calendar date_debut;
	 @DateTimeFormat(pattern = "dd-MM-yyyy")
	private Calendar date_final;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Theme getTheme() {
		return theme;
	}
	public void setTheme(Theme theme) {
		this.theme = theme;
	}
	public Formatter getFormatter() {
		return formatter;
	}
	public void setFormatter(Formatter formatter) {
		this.formatter = formatter;
	}
	public String getLien() {
		return lien;
	}

	public void setLien(String lien) {
		this.lien = lien;
	}

	public ZonedDateTime getDate_creation() {
		return date_creation;
	}

	public void setDate_creation(ZonedDateTime date_creation) {
		this.date_creation = date_creation;
	}

	
	public Calendar getDate_debut() {
		return date_debut;
	}

	public void setDate_debut(Calendar date_debut) {
		this.date_debut = date_debut;
	}

	public Calendar getDate_final() {
		return date_final;
	}

	public void setDate_final(Calendar date_final) {
		this.date_final = date_final;
	}

	public Guest getGuest() {
		return guest;
	}

	public void setGuest(Guest guest) {
		this.guest = guest;
	}

	public String getPrix() {
		return prix;
	}

	public void setPrix(String prix) {
		this.prix = prix;
	}

	
	
	
	
}
