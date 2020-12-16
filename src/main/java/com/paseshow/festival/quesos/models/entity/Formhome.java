package com.paseshow.festival.quesos.models.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table
public class Formhome {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	
	private String nombre;
	private String apellido;
	private String email;
	private String telefono;
	private String tipoSector;
	private Boolean loaddb;

	
	
	@ElementCollection(targetClass=Long.class)
	private List<Long> idevento;
	
	/*@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Codigosingresos codigoingreso;*/
	@ElementCollection(targetClass=Long.class)
	private List<Long> idCodigos;

	

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

	public List<Long> getIdCodigos() {
		return idCodigos;
	}

	public void setIdCodigos(List<Long> idCodigos) {
		this.idCodigos = idCodigos;
	}

	public List<Long> getIdevento() {
		return idevento;
	}

	public void setIdevento(List<Long> idevento) {
		this.idevento = idevento;
	}

	public Boolean getLoaddb() {
		return loaddb;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLoaddb(Boolean loaddb) {
		this.loaddb = loaddb;
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




	private static final long serialVersionUID = 1L;

}
