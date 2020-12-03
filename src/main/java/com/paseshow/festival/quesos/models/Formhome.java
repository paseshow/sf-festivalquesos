package com.paseshow.festival.quesos.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


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

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
