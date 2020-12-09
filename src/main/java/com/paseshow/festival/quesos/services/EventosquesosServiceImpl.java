package com.paseshow.festival.quesos.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.paseshow.festival.quesos.models.dao.EventosquesosDao;
import com.paseshow.festival.quesos.models.entity.Eventoquesos;

@Service
public class EventosquesosServiceImpl implements EventosquesosService{

	@Autowired
	private EventosquesosDao eventosquesosDao;

	@Override
	public Eventoquesos save(Eventoquesos eventoquesos) {
		return eventosquesosDao.save(eventoquesos);
	}

	@Override
	public List<Eventoquesos> findAll() {
		return eventosquesosDao.findAll();
	}


	public Eventoquesos findByid(Long id) {
		try {
			return eventosquesosDao.findByid(id);
		} catch (Exception e) {
			return null;
		}
	}

	public Boolean delete(Eventoquesos eventoquesos) {
		try {
			eventosquesosDao.delete(eventoquesos);
			return true;
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error id no debe ser null. Error: " + e);
		} catch (PersistenceException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en el servidor. Error: " + e);
		}
	}
	

}
