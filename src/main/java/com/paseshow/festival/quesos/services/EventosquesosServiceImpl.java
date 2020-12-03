package com.paseshow.festival.quesos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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


	
}
