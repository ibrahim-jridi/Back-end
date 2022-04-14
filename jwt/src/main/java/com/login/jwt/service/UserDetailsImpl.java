package com.login.jwt.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.login.jwt.entity.User;
public class UserDetailsImpl implements UserDetails {
	private static final long serialVersionUID = 1L;
	
	private String userName;
	private String email;
	@JsonIgnore
	private String userPassword;
	private String userConfirmPassword;
	private String userLastName;
	private String userFirstName;
	private String phone;
	private String adresse;
	private Collection<? extends GrantedAuthority> authorities;
	public UserDetailsImpl( String userName, String email, String userPassword,String userFirstName,String userLastName,
			String phone,String adresse,String userConfirmPassword,
			Collection<? extends GrantedAuthority> authorities) {
		
		this.userName = userName;
		this.email = email;
		this.userPassword = userPassword;
		this.userConfirmPassword = userConfirmPassword;
		this.userLastName = userLastName;
		this.userFirstName = userFirstName;
		this.phone= phone;
		this.adresse = adresse;
		this.authorities = authorities;
	}
	public static UserDetailsImpl build(User user) {
		List<GrantedAuthority> authorities = user.getRole().stream()
				.map(role -> new SimpleGrantedAuthority(role.getRoleName()))
				.collect(Collectors.toList());
		return new UserDetailsImpl(
				 
				user.getUserName(), 
				user.getUserFirstName(),
				user.getUserLastName(),
				user.getEmail(),
				user.getUserPassword(), 
				user.getAdresse(),
				user.getPhone(),
				user.getAdresse(),
				authorities);
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	
	public String getEmail() {
		return email;
	}
	@Override
	public String getPassword() {
		return userPassword;
	}
	@Override
	public String getUsername() {
		return userName;
	}
	
	public String getUserFirstName() {
		return userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public String getPhone() {
		return phone;
	}
	public String getAdresse() {
		return adresse;
	}
	public String getUserConfirmPassword() {
		return userConfirmPassword;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}
	@Override
	public boolean isEnabled() {
		return true;
	}
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(userName, user.userName);
	}
}