package com.paseshow.festival.quesos.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.paseshow.festival.quesos.services.GenericService;
import com.paseshow.festival.quesos.services.ResourceNotFoundException;

public class GenericController<T> {
	
	private GenericService<T> genericService;

	/*@Autowired
	private ErrorBody errorBody;*/

	public GenericController() {
	}

	public GenericController(GenericService<T> genericService) {
		this.genericService = genericService;
	}

	@GetMapping(name = "list")
	public ResponseEntity<Page<T>> list(Pageable pageable) {
		return ResponseEntity.ok(this.genericService.findAll(pageable));
	}

	@GetMapping(name = "findById", path = "{id}")
	public ResponseEntity<?> getT(@PathVariable("id") Long id) {
		T t = this.genericService.findById(id);
		if (t == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Id: no encontrado");
		}
		return ResponseEntity.status(200).body(t);
	}

	@PostMapping(name = "create")
	public ResponseEntity<?> create(@Valid @RequestBody T t, BindingResult result) throws ResourceNotFoundException {

		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}

		T newT = this.genericService.save(t);

		return ResponseEntity.status(HttpStatus.CREATED).body(newT);
	}

	@PostMapping(name = "createBatch", path = "batch")
	public ResponseEntity<?> createBatch(@Valid @RequestBody List<T> listT, BindingResult result)
			throws ResourceNotFoundException {

		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}

		this.genericService.saveAll(listT);

		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	@PutMapping(name = "update")
	public ResponseEntity<?> update(@Valid @RequestBody T t, BindingResult result) throws ResourceNotFoundException {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}

		T newT = this.genericService.save(t);

		if (newT == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Error al actualizar");
		}
		return ResponseEntity.ok().body(newT);

	}

	@DeleteMapping(name = "delete")
	public ResponseEntity<?> delete(@Valid @ModelAttribute T t, BindingResult result) {
		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}

		this.genericService.delete(t);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(name = "deleteById", path = "{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		this.genericService.delete(id);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping(name = "deleteBatch", path = "batch")
	public ResponseEntity<?> delete(@Valid @RequestBody List<T> listT, BindingResult result) {

		if (result.hasErrors()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
		}

		this.genericService.deleteAll(listT);
		return ResponseEntity.ok().build();
	}
}
