package com.login.jwt.entity;

import java.io.File;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "guest",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "userName"),
		@UniqueConstraint(columnNames = "email") 
	})

public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String userName;
	
	private String userFirstName;

	
	private String userLastName;
	
	
	private String email;
	private String phone;
	/*
	 * @OneToOne(mappedBy = "formatter") private ImageModel imageModel;
	 */
	private String adresse;
	
	private String userPassword;
    private String userConfirmPassword;

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "USER_ROLE",
            joinColumns = {
                    @JoinColumn(name = "USER_ID")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "ROLE_ID")
            }
    )
    private Set<Role> role;
	
	@OneToMany(mappedBy = "guest",fetch=FetchType.EAGER, cascade=CascadeType.ALL)
	@JsonIgnore
	private List<Formation> formation;
	
	  public Guest() {
		  super();
	  }
	  
	  
	  public Guest(String userName, String userFirstName, String userLastName,
	  String email,String phone, String userPassword,String userConfirmPassword) {
	  super();
	  this.userName = userName; 
	  this.userPassword = userPassword;
	  this.userConfirmPassword = userConfirmPassword;
	  this.userFirstName = userFirstName; 
	  this.userLastName = userLastName; 
	  this.email = email;
	  this.phone = phone;
	  
	  }
	 

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserFirstName() {
		return userFirstName;
	}

	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}

	public String getUserLastName() {
		return userLastName;
	}

	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	} 

	public List<Formation> getFormation() {
		return formation;
	}


	public void setFormation(List<Formation> formation) {
		this.formation = formation;
	}


	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

		
	

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	

	public String getUserConfirmPassword() {
		return userConfirmPassword;
	}

	public void setUserConfirmPassword(String userConfirmPassword) {
		this.userConfirmPassword = userConfirmPassword;
	}

	public Set<Role> getRole() {
		return role;
	}

	public void setRole(Set<Role> role) {
		this.role = role;
	}

	/*
	 * public Set<Formation> getFormation() { return formation; }
	 * 
	 * public void setFormation(Set<Formation> formation) { this.formation =
	 * formation; }
	 */
	

}
