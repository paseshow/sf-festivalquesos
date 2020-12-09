package com.paseshow.festival.quesos.services;

import org.springframework.stereotype.Service;

import com.paseshow.festival.quesos.models.entity.Codigosingresos;

@Service
public interface CodigosService {

	public Codigosingresos save(Codigosingresos codigoIngreso);
	public boolean existById(Long id);
	public Codigosingresos findById(Long id);
	
}
