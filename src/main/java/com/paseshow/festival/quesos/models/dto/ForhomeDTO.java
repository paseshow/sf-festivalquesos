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
	
	@Size(max=35)
	private String apellido;
	
	@NotNull
	@Size(max=35)
	private String email;
	
	@NotNull
	@Size(max=22)
	private String telefono;
	
	@NotNull
	private String tipoSector;
	
	
	@NotNull
	private Boolean loaddb;

	
	@JsonCreator
	public ForhomeDTO(
			@JsonProperty("nombre") String nombre,
			@JsonProperty("apellido") String apellido,
			@JsonProperty("email") String email,
			@JsonProperty("telefono") String telefono,
			@JsonProperty("tipoSector") String tipoSector,
			@JsonProperty("suscripcion") Boolean suscripcion
			
			) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.email = email;
		this.telefono = telefono;
		this.tipoSector = tipoSector;
		this.loaddb = loaddb;
	}

	

	public String getTipoSector() {
		return tipoSector;
	}



	public void setTipoSector(String tipoSector) {
		this.tipoSector = tipoSector;
	}



	public String getApellido() {
		return apellido;
	}



	public void setApellido(String apellido) {
		this.apellido = apellido;
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


	public Boolean getLoaddb() {
		return loaddb;
	}


	public void setLoaddb(Boolean loaddb) {
		this.loaddb = loaddb;
	}
}
