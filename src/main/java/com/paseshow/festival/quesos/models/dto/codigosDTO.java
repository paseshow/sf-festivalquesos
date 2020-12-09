package com.paseshow.festival.quesos.models.dto;

import javax.validation.constraints.NotNull;

public class codigosDTO {


	@NotNull
	private Long id;
	
	@NotNull
	private Long IdUser;
	
	@NotNull
	private Long IdEvent;

	public Long getIdEvent() {
		return IdEvent;
	}

	public void setIdEvent(Long idEvent) {
		IdEvent = idEvent;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getIdUser() {
		return IdUser;
	}
	
	public void setIdUser(Long idUser) {
		IdUser = idUser;
	}
}
