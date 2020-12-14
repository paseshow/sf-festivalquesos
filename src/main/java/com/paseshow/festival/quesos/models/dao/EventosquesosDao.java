package com.paseshow.festival.quesos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paseshow.festival.quesos.models.entity.Eventoquesos;

public interface EventosquesosDao extends JpaRepository<Eventoquesos, Long>{

	Eventoquesos findByid(Long id);
	List<Eventoquesos> findByFechaEventContaining(String fecha);
}
