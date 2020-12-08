package com.paseshow.festival.quesos.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
