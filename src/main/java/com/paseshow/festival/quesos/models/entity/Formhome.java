package com.paseshow.festival.quesos.models.entity;

import java.io.Serializable;
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
	private String email;
	private String telefono;
	private String descripcionentrada;
	private Boolean loaddb;
	private Boolean suscripcion;
	
	@ManyToOne(fetch = FetchType.LAZY) //se carga solamente cuando se hace el get
	//automaticamente toma la llave de su tabla (id)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Eventoquesos eventoquesos;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Codigosingresos codigoingreso;
	
	public Eventoquesos getEventoquesos() {
		return eventoquesos;
	}

	public void setEventoquesos(Eventoquesos eventoquesos) {
		this.eventoquesos = eventoquesos;
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

	public Boolean getSuscripcion() {
		return suscripcion;
	}

	public void setSuscripcion(Boolean suscripcion) {
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

	
	public Codigosingresos getCodigoingreso() {
		return codigoingreso;
	}

	public void setCodigoingreso(Codigosingresos codigoingreso) {
		this.codigoingreso = codigoingreso;
	}


	private static final long serialVersionUID = 1L;

}
