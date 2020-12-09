package com.paseshow.festival.quesos.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.paseshow.festival.quesos.security.entity.Role;
import com.paseshow.festival.quesos.security.enums.RoleName;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
	
	Optional<Role> findByRolName(RoleName rolName);
}
