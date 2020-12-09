package com.paseshow.festival.quesos.services;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.paseshow.festival.quesos.security.entity.Role;
import com.paseshow.festival.quesos.security.enums.RoleName;
import com.paseshow.festival.quesos.security.repository.RoleRepository;

@Service
@Transactional
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	public Optional<Role> getByRolName(RoleName rolName) {
		return roleRepository.findByRolName(rolName);
	}
	
	public void save(Role rol) {
		roleRepository.save(rol);
	}
}
