package com.paseshow.festival.quesos.models.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.paseshow.festival.quesos.models.dto.ForhomeDTO;

import javax.persistence.Id;

@Entity
@Table
public class Eventoquesos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String nameEvent;
	
	
	private String linkEvent;
	
	private Boolean active;
	
	private String fechaEvent;
	

	public String getFechaEvent() {
		return fechaEvent;
	}

	public void setFechaEvent(String fechaEvent) {
		this.fechaEvent = fechaEvent;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public String getLinkEvent() {
		return linkEvent;
	}

	public void setLinkEvent(String linkEvent) {
		this.linkEvent = linkEvent;
	}
	
	
	
	
}
