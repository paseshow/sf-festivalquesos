package com.paseshow.festival.quesos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.paseshow.festival.quesos.model.util.ErrorSimple;
import com.paseshow.festival.quesos.models.dto.EventoquesosDTO;
import com.paseshow.festival.quesos.models.entity.Eventoquesos;
import com.paseshow.festival.quesos.services.EventosquesosServiceImpl;
import com.paseshow.festival.quesos.services.ResourceNotFoundException;

@RestController
@RequestMapping("/eventoes")
@CrossOrigin
public class EventoquesosController {
	
	@Autowired
	private EventosquesosServiceImpl eventosquesosServiceImpl;
	
	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping(name="add", path = "add")
	public ResponseEntity<?> addNewEvent(@Valid @RequestBody EventoquesosDTO evento, BindingResult bindingResult)
			throws ResourceNotFoundException {
		
		if(bindingResult.hasErrors()) {
			ErrorSimple error = new ErrorSimple();
			error.setId(1);
			error.setDescripcion("Datos de evento invalidos");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
		
		Eventoquesos eventoNew = new Eventoquesos();
		eventoNew.setNameEvent(evento.getNameEvent());
		eventoNew.setLinkEvent(evento.getLinkEvent());
		eventoNew.setActive(evento.getActive());

		Eventoquesos eventosUpdate = eventosquesosServiceImpl.save(eventoNew);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Evento creado exitosamente!");
	}
	
	@GetMapping(name="list", path="list")
	public ResponseEntity<?> eventoesList() throws ResourceNotFoundException {
		
		//if(bindingResult.hasErrors())
			//return ResponseEntity.status(HttpStatus.CONFLICT).body("Hubo un problema en el servidor!");
		
		List<Eventoquesos> eventoListAll = eventosquesosServiceImpl.findAll();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(eventoListAll);
	}
	

}
