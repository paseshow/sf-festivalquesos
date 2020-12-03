package com.paseshow.festival.quesos.services;

import java.util.List;

import com.paseshow.festival.quesos.models.entity.Eventoquesos;

public interface EventosquesosService {

	public Eventoquesos save(Eventoquesos eventoquesos);
	public List<Eventoquesos> findAll();
}
