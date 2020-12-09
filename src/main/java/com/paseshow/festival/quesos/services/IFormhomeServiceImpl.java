package com.paseshow.festival.quesos.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.paseshow.festival.quesos.models.dao.IFormhomeDao;
import com.paseshow.festival.quesos.models.dto.ForhomeDTO;
import com.paseshow.festival.quesos.models.entity.Codigosingresos;
import com.paseshow.festival.quesos.models.entity.Formhome;

@Service
public class IFormhomeServiceImpl implements IForhomeService{

	@Autowired
	private IFormhomeDao formhomeDao;
	
	@Override
	public List<Formhome> findAll() {
		return (List<Formhome>) formhomeDao.findAll();
	}

	@Override
	public Formhome save(Formhome formhome) {
		return formhomeDao.save(formhome);
	}

	public Formhome findById(Long id) {
		try {
			return formhomeDao.findById(id).get();
		} catch (Exception e) {
			return null;
		}

	}

}
