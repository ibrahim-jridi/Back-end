package com.login.jwt.entity;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Formation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String name;
	private String description;
	@ManyToMany(
			fetch = FetchType.LAZY,
				      cascade = {
				              CascadeType.PERSIST,
				              CascadeType.MERGE
			    }
		)
	@JoinTable(name = "formation_theme",
    joinColumns = {
            @JoinColumn(name = "FORMATION_ID")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "THEME_ID")
            }
	)

	private Set<Theme> theme = new HashSet<>(); 
	
	@ManyToMany(
			fetch = FetchType.LAZY,
				      cascade = {
				              CascadeType.PERSIST,
				              CascadeType.MERGE
			    }
		)
	@JoinTable(name = "formation_formatter",
    joinColumns = {
            @JoinColumn(name = "FORMATION_ID")
    },
    inverseJoinColumns = {
            @JoinColumn(name = "Formatter_ID")
            }
	)

	private Set<Formatter> formatter = new HashSet<>(); ;
	private String lien;
	
	
	private ZonedDateTime date_creation = ZonedDateTime.now();
	
	private Calendar date_debut;
	
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

	
	

	public Set<Theme> getTheme() {
		return theme;
	}

	public void setTheme(Set<Theme> theme) {
		this.theme = theme;
	}
	
	
	public Set<Formatter> getFormatter() {
		return formatter;
	}

	public void setFormatter(Set<Formatter> formatter) {
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
	
	
}
