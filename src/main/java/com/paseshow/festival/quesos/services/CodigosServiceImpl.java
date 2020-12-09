package com.paseshow.festival.quesos.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.paseshow.festival.quesos.models.dao.CodigosDao;
import com.paseshow.festival.quesos.models.entity.Codigosingresos;
import com.paseshow.festival.quesos.models.entity.Eventoquesos;

@Service
public class CodigosServiceImpl implements CodigosService{
	
	@Autowired
	private CodigosDao codigosDao;

	@Override
	public Codigosingresos save(Codigosingresos codigoIngreso) {
		return codigosDao.save(codigoIngreso);
	}

	@Override
	public boolean existById(Long id) {
		return codigosDao.existsById(id);
	}

	public Codigosingresos findById(Long id) {
		try {
			return codigosDao.findById(id).get();
		} catch (Exception e) {
			return null;
		}

	}

}
