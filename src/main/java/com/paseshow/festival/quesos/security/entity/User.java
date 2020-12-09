package com.paseshow.festival.quesos.security.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String name;
	
	@NotNull
	@Column(unique = true)
	private String nameUser;
	
	@NotNull
	private String email;
	
	@NotNull
	private String password;
	
	@NotNull
	@ManyToMany(fetch = FetchType.EAGER) //tenemos que un usuario puede tener varios roles
	@JoinTable(
			name="user_rol",
			joinColumns = @JoinColumn(name ="user_rol"),
			inverseJoinColumns =  @JoinColumn(name = "rol_id")
			)
	private Set<Role> roles = new HashSet<>();

	public User() {
		super();
	}

	public User(
			@NotNull String name,
			@NotNull String nameUser,
			@NotNull String email,
			@NotNull String password) {
		this.name = name;
		this.nameUser = nameUser;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameUser() {
		return nameUser;
	}

	public void setNameUser(String nameUser) {
		this.nameUser = nameUser;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
	

}
