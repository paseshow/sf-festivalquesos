package com.paseshow.festival.quesos.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paseshow.festival.quesos.models.entity.Codigosingresos;

public interface CodigosDao extends JpaRepository<Codigosingresos, Long>{

	List<Codigosingresos> findByIdEvento(Long idEvento);
}
