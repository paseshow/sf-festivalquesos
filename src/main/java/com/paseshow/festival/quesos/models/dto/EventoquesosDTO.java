package com.paseshow.festival.quesos.models.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventoquesosDTO {

	@NotNull
	private String nameEvent;
	
	@NotNull
	private String linkEvent;

	@NotNull
	private Boolean active;

	@JsonCreator
	public EventoquesosDTO(
			@JsonProperty ("nameEvent") String nameEvent,
			@JsonProperty ("linkEvent") String linkEvent,
			@JsonProperty ("active") Boolean active
			) {
		this.nameEvent = nameEvent;
		this.linkEvent = linkEvent;
		this.active = active;
	}

	public String getNameEvent() {
		return nameEvent;
	}

	public void setNameEvent(String nameEvent) {
		this.nameEvent = nameEvent;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getLinkEvent() {
		return linkEvent;
	}

	public void setLinkEvent(String linkEvent) {
		this.linkEvent = linkEvent;
	}
	
	
	
}
