package com.paseshow.festival.quesos.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.w3c.dom.ls.LSInput;

import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;
import com.paseshow.festival.quesos.model.util.ErrorSimple;
import com.paseshow.festival.quesos.models.dto.EventoquesosDTO;
import com.paseshow.festival.quesos.models.dto.EventosActivos;
import com.paseshow.festival.quesos.models.entity.Eventoquesos;
import com.paseshow.festival.quesos.services.EventosquesosServiceImpl;
import com.paseshow.festival.quesos.services.ResourceNotFoundException;

@RestController
@RequestMapping("/eventoes")
@CrossOrigin
public class EventoquesosController {
	
	@Autowired
	private EventosquesosServiceImpl eventosquesosServiceImpl;
	
	// hacer api para dar dos eventos activos segun la fecha y hora.
	
	@PreAuthorize("hasRole('USER')")
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
		eventoNew.setFechaEvent(evento.getFechaEvent());
		eventoNew.setLinkChat(evento.getLinkChat());
		eventoNew.setActiveChat(evento.getActiveChat());

		Eventoquesos eventosUpdate = eventosquesosServiceImpl.save(eventoNew);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Evento creado exitosamente!");
	}
	
	@GetMapping(name="event", path="{id}")
	public ResponseEntity<?> eventById(@PathVariable("id") Long id){
		
		if(id == null) {
			ErrorSimple error = new ErrorSimple();
			error.setId(1);
			error.setDescripcion("Id null");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
		
		Eventoquesos eventoquesos = eventosquesosServiceImpl.findByid(id);
		
		if(eventoquesos != null) {
			return ResponseEntity.ok().body(eventoquesos);
		}
		
		ErrorSimple error = new ErrorSimple();
		error.setId(2);
		error.setDescripcion("Id no encontrado");
		Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
		mapError.put("error", error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);		
	}
	
	@GetMapping(name="loadEvent", path="active")
	public ResponseEntity<?> searchEventos(@PathParam("fechaEvento") String fechaEvento) {
		if(fechaEvento == null) {
			ErrorSimple error = new ErrorSimple();
			error.setId(1);
			error.setDescripcion("Fecha vacia");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);	
		}
		
		List<Eventoquesos> listEvent = eventosquesosServiceImpl.findByFechaEventContaining(fechaEvento);
				
		List<EventosActivos> listActiveEvent = new ArrayList<EventosActivos>();
		EventosActivos eventPrevio = new EventosActivos();
		EventosActivos eventPrincipal = new EventosActivos();
		Long fechaPrevia = new Long(0);
		Boolean inactive = false;
		Boolean unoActivo = false;
		for(Eventoquesos event : listEvent) {
			if(event.getActive()) {
				String fechaEvt = event.getFechaEvent();
				String[] array = fechaEvt.split("T");
				Long fecha = Long.parseLong(array[1].replace(":", ""));
				
				if(eventPrevio.getIdEvento() == null) {
					fechaPrevia = fecha;
					eventPrevio.setIdEvento(event.getId());
					eventPrevio.setFechaEvento(event.getFechaEvent());
					unoActivo = true;
					if(inactive) {
						listActiveEvent.add(eventPrevio);
					}
				} else {
					
					eventPrincipal.setIdEvento(event.getId());
					eventPrincipal.setFechaEvento(event.getFechaEvent());
					
					if(fechaPrevia < fecha) {
						listActiveEvent.add(eventPrevio);
						listActiveEvent.add(eventPrincipal);
					} else {
						listActiveEvent.add(eventPrincipal);
						listActiveEvent.add(eventPrevio);
					}
				}				
			} else {
				inactive = true;
				if(unoActivo) {
					listActiveEvent.add(eventPrevio);
				}
			}
		}
		if(!listActiveEvent.isEmpty()) {
			return ResponseEntity.ok(listActiveEvent);			
		} else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("No hay eventos activos");
		}
		
		
	}
	
	@GetMapping(name="list", path="list")
	public ResponseEntity<?> eventoesList() throws ResourceNotFoundException {
		
		//if(bindingResult.hasErrors())
			//return ResponseEntity.status(HttpStatus.CONFLICT).body("Hubo un problema en el servidor!");
		
		List<Eventoquesos> eventoListAll = eventosquesosServiceImpl.findAll();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(eventoListAll);
	}
	
	@DeleteMapping(name="delet", path="{id}")
	public ResponseEntity<?> deleteEventId(@PathVariable("id") Long id) {
		Eventoquesos eventDelet = eventosquesosServiceImpl.findByid(id);
		
		if(eventosquesosServiceImpl.delete(eventDelet))
		return ResponseEntity.status(HttpStatus.CREATED).body("Evento eliminado correctamente");
		
		return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al eliminar");
	}
	
	@PutMapping(name="update", path="update/{id}")
	public ResponseEntity<?> updateEvent(@Valid @RequestBody EventoquesosDTO evento, @PathVariable("id") Long id, BindingResult bindingResult)
			throws ResourceNotFoundException {
		
		if(bindingResult.hasErrors())
		{
			ErrorSimple error = new ErrorSimple();
			error.setId(1);
			error.setDescripcion("Error servidor");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
		
		if(id == null) {
			ErrorSimple error = new ErrorSimple();
			error.setId(1);
			error.setDescripcion("Id null");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
		
		Eventoquesos eventoUpdate = eventosquesosServiceImpl.findByid(id);
		
		if(eventoUpdate != null) {
			eventoUpdate.setNameEvent(evento.getNameEvent());
			eventoUpdate.setLinkEvent(evento.getLinkEvent());
			eventoUpdate.setFechaEvent(evento.getFechaEvent());
			eventoUpdate.setActive(evento.getActive());
			eventoUpdate.setLinkChat(evento.getLinkChat());
			eventoUpdate.setActiveChat(evento.getActiveChat());
			
			eventosquesosServiceImpl.save(eventoUpdate);
			
			return ResponseEntity.ok("Evento actualizado");
		}
		
		ErrorSimple error = new ErrorSimple();
		error.setId(1);
		error.setDescripcion("Error al actualizar");
		Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
		mapError.put("error", error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
	}

}
