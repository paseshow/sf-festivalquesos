package com.paseshow.festival.quesos.models.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.paseshow.festival.quesos.models.entity.Formhome;

public interface IFormhomeDao extends JpaRepository<Formhome, Long>{

}
