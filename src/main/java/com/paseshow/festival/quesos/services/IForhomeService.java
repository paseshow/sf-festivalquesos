package com.paseshow.festival.quesos.services;

import java.util.List;

import com.paseshow.festival.quesos.models.dto.ForhomeDTO;
import com.paseshow.festival.quesos.models.entity.Formhome;

public interface IForhomeService {

	public List<Formhome> findAll();
	public Formhome save(Formhome formhome);
}
