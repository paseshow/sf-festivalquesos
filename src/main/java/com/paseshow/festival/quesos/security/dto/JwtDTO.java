package com.paseshow.festival.quesos.security.dto;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class JwtDTO {

	private String token;
	private String bearer= "Bearer";
	private String nameUser;
	private Collection<? extends GrantedAuthority> authorities;
	
	
	public JwtDTO(String token, String nameUser, Collection<? extends GrantedAuthority> authorities) {
		super();
		this.token = token;
		this.nameUser = nameUser;
		this.authorities = authorities;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	public String getBearer() {
		return bearer;
	}


	public void setBearer(String bearer) {
		this.bearer = bearer;
	}


	public String getNameUser() {
		return nameUser;
	}


	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}


	public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
		this.authorities = authorities;
	}
	
	
	
	
}
