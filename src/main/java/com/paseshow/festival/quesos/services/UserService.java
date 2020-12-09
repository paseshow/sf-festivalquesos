package com.paseshow.festival.quesos.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.PersistenceException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.paseshow.festival.quesos.models.entity.Eventoquesos;
import com.paseshow.festival.quesos.security.entity.User;
import com.paseshow.festival.quesos.security.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public User getByNameUser(String nameUser) {
		User use = userRepository.findByNameUser(nameUser);
		return use;
	}
	
	public Optional<User> getByName(String nameUser) {
		return userRepository.findByName(nameUser);
	}
	
	public boolean existsByNameUser(String nameUser) {
		return userRepository.existsByNameUser(nameUser);
	}
	public boolean existsByEmail(String email) {
		return userRepository.existsByEmail(email);
	}
	
	public boolean existsByName(String name) {
		return userRepository.existsByName(name);
	}
	
	public void save(User user) {
		userRepository.save(user);
	}
	
	public List<User> findAll() {
		return userRepository.findAll();
	}
	
	public User findById(Long id) {
		try {
			return userRepository.findByid(id);
		} catch (Exception e) {
			return null;
		}
	}
	
	public Boolean delete(User user) {
		try {
			userRepository.delete(user);
			return true;
		} catch (IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error id no debe ser null. Error: " + e);
		} catch (PersistenceException e) {
			throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error en el servidor. Error: " + e);
		}
	}
	
}
