package com.paseshow.festival.quesos.models.dto;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.paseshow.festival.quesos.models.entity.Codigosingresos;
import com.paseshow.festival.quesos.models.entity.Formhome;

public class ForhomeDTO {
	
	@NotNull
	@Size(max=35)
	private String nombre;
	
	@NotNull
	@Size(max=35)
	private String email;
	
	@NotNull
	@Size(max=22)
	private String telefono;
	
	@NotNull
	private String descripcionentrada;
	
	@NotNull
	private Boolean loaddb;
	
	@NotNull
	private Boolean suscripcion;

	
	@JsonCreator
	public ForhomeDTO(
			@JsonProperty("nombre") String nombre,
			@JsonProperty("email") String email,
			@JsonProperty("telefono") String telefono,
			@JsonProperty("descripcionentrada") String descripcionentrada,
			@JsonProperty("loaddb") Boolean loaddb,
			@JsonProperty("suscripcion") Boolean suscripcion
			
			) {
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.descripcionentrada = descripcionentrada;
		this.loaddb = loaddb;
		this.suscripcion = suscripcion;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getTelefono() {
		return telefono;
	}


	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}


	public String getDescripcionentrada() {
		return descripcionentrada;
	}


	public void setDescripcionentrada(String descripcionentrada) {
		this.descripcionentrada = descripcionentrada;
	}


	public Boolean getLoaddb() {
		return loaddb;
	}


	public void setLoaddb(Boolean loaddb) {
		this.loaddb = loaddb;
	}


	public Boolean getSuscripcion() {
		return suscripcion;
	}


	public void setSuscripcion(Boolean suscripcion) {
		this.suscripcion = suscripcion;
	}
}
