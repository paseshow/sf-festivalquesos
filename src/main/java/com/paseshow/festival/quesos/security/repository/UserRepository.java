package com.paseshow.festival.quesos.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paseshow.festival.quesos.security.entity.User;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	User findByNameUser(String nameUser);
	Optional<User> findByName(String nameUser);
	boolean existsByNameUser(String nameUser);
	boolean existsByEmail(String email);
	boolean existsByName(String name);
	List<User> findAll();
	void deleteById(Long id);
	User findByid(Long id);
	void delete(User user);
}
