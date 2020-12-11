package com.paseshow.festival.quesos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.Conflict;

import com.paseshow.festival.quesos.model.util.ErrorSimple;
import com.paseshow.festival.quesos.models.dto.ForhomeDTO;
import com.paseshow.festival.quesos.models.entity.Formhome;
import com.paseshow.festival.quesos.services.IFormhomeServiceImpl;
import com.paseshow.festival.quesos.services.ResourceNotFoundException;

//@CrossOrigin(origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/apiform")
@CrossOrigin(origins = "*")
public class ForhomeRestController {

	
	@Autowired
	private IFormhomeServiceImpl forhomeServiceImpl;
	
	@PutMapping(name="add", path="add")
	public ResponseEntity<?> addData(@Valid @RequestBody ForhomeDTO forhomeadd,BindingResult bindingResult)
			throws ResourceNotFoundException {
		if(bindingResult.hasErrors()) {
			ErrorSimple error = new ErrorSimple();
			error.setId(1);
			error.setDescripcion("Formulario incorrecto");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
		Formhome aux = new Formhome();
		aux.setNombre(forhomeadd.getNombre());
		aux.setEmail(forhomeadd.getEmail());
		aux.setTelefono(forhomeadd.getTelefono());
		aux.setDescripcionentrada(forhomeadd.getDescripcionentrada());
		aux.setLoaddb(forhomeadd.getLoaddb());
		aux.setSuscripcion(forhomeadd.getSuscripcion());
		
		Formhome resp = this.forhomeServiceImpl.save(aux);
		
		return ResponseEntity.ok().body(resp);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(name="listAll", path="listAll")
	public ResponseEntity<?> listAllForms(@RequestParam String idEvento) {
		List<Formhome> listForm = this.forhomeServiceImpl.findAll();
		
		if(listForm.isEmpty()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("No hay datos guardados");
		}

		
		return ResponseEntity.status(HttpStatus.CREATED).body(listForm);
	}
}
