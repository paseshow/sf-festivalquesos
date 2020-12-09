package com.paseshow.festival.quesos.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.paseshow.festival.quesos.models.entity.Eventoquesos;

public interface EventosquesosDao extends JpaRepository<Eventoquesos, Long>{

	Eventoquesos findByid(Long id);
}
