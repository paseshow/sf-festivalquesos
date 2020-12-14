package com.paseshow.festival.quesos.models.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class EventoquesosDTO {

	public String getFechaEvent() {
		return fechaEvent;
	}

	public void setFechaEvent(String fechaEvent) {
		this.fechaEvent = fechaEvent;
	}

	@NotNull
	private String nameEvent;
	
	@NotNull
	private String linkEvent;

	@NotNull
	private Boolean active;
	
	@NotNull
	private String fechaEvent;
	
	private String linkChat;
	
	private Boolean activeChat;

	@JsonCreator
	public EventoquesosDTO(
			@JsonProperty ("nameEvent") String nameEvent,
			@JsonProperty ("linkEvent") String linkEvent,
			@JsonProperty ("active") Boolean active,
			@JsonProperty ("fechaEvent") String fechaEvent,
			@JsonProperty ("activeChat") Boolean activeChat,
			@JsonProperty ("linkChat") String linkChat
			) {
		this.nameEvent = nameEvent;
		this.linkEvent = linkEvent;
		this.active = active;
		this.fechaEvent = fechaEvent;
		this.activeChat = activeChat;
		this.linkChat = linkChat;
	}

	
	public String getLinkChat() {
		return linkChat;
	}

	public void setLinkChat(String linkChat) {
		this.linkChat = linkChat;
	}

	public Boolean getActiveChat() {
		return activeChat;
	}

	public void setActiveChat(Boolean activeChat) {
		this.activeChat = activeChat;
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
