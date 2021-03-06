package com.paseshow.festival.quesos.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.paseshow.festival.quesos.models.entity.Eventoquesos;

@Service
public interface EventosquesosService {

	public Eventoquesos save(Eventoquesos eventoquesos);
	public List<Eventoquesos> findAll();
	public Eventoquesos findByid(Long id);
	public Boolean delete(Eventoquesos eventoquesos);
	public List<Eventoquesos> findByFechaEventContaining(String fecha);
}
