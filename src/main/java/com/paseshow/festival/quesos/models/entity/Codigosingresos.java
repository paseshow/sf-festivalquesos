package com.paseshow.festival.quesos.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;

@Entity
@Table
public class Codigosingresos implements Serializable{


	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	private String etiqueta;
	
	/*@ManyToOne(fetch = FetchType.LAZY) //se carga solamente cuando se hace el get
	//automaticamente toma la llave de su tabla (id)
	@JoinTable(
			name="event",
			//joinColumns = @JoinColumn(name ="user_rol"),
			inverseJoinColumns =  @JoinColumn(name = "rol_id")
			)*/
	private Long idEvento;

	
	
	public Long getIdEvento() {
		return idEvento;
	}

	public void setIdEvento(Long idEvento) {
		this.idEvento = idEvento;
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getEtiqueta() {
		return etiqueta;
	}

	public void setEtiqueta(String etiqueta) {
		this.etiqueta = etiqueta;
	}

	private static final long serialVersionUID = 1L;

	
	
}
