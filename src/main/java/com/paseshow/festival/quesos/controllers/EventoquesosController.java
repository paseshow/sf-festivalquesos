package com.paseshow.festival.quesos.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paseshow.festival.quesos.models.entity.Eventoquesos;
import com.paseshow.festival.quesos.services.EventosquesosServiceImpl;

@RestController
@RequestMapping(name="eventoes")
public class EventoquesosController {
	
	@Autowired
	private EventosquesosServiceImpl eventosquesosServiceImpl;
	
	/*@PutMapping(name="", path = "")
	public ResponseEntity<?> addNewEvent(@Valid @RequestBody Eventoquesos evento){
		
		if(evento == null) {
			
		}
		
	}*/
	

}
