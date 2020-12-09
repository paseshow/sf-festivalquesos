package com.paseshow.festival.quesos.controllers;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.paseshow.festival.quesos.model.util.ErrorSimple;
import com.paseshow.festival.quesos.model.util.Mensajes;
import com.paseshow.festival.quesos.security.dto.JwtDTO;
import com.paseshow.festival.quesos.security.dto.LoginUser;
import com.paseshow.festival.quesos.security.dto.NewUser;
import com.paseshow.festival.quesos.security.entity.Role;
import com.paseshow.festival.quesos.security.entity.User;
import com.paseshow.festival.quesos.security.enums.RoleName;
import com.paseshow.festival.quesos.security.jwt.JwtProvider;
import com.paseshow.festival.quesos.services.RoleService;
import com.paseshow.festival.quesos.services.UserService;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {
	
	@Autowired
	PasswordEncoder passwordEncoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserService userService;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	JwtProvider jwtProvider;
	
	@PostMapping(name="new", path="new")
	public ResponseEntity<?> newUser(@Valid @RequestBody NewUser newUser, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ErrorSimple error = new ErrorSimple();
			error.setId(1);
			error.setDescripcion("Credenciales incorrectas");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
		
		if(userService.existsByNameUser(newUser.getNameUser())) {
			ErrorSimple error = new ErrorSimple();
			error.setId(2);
			error.setDescripcion("Nombre de usuario ya existente");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);	
		}
		
		if(userService.existsByEmail(newUser.getEmail())) {
			ErrorSimple error = new ErrorSimple();
			error.setId(3);
			error.setDescripcion("Email ya existente");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);	
		}
		
		if(userService.existsByName(newUser.getName())) {
			ErrorSimple error = new ErrorSimple();
			error.setId(3);
			error.setDescripcion("Nombre ya existente");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);	
		}
		
		User user = new User(
				newUser.getName(),
				newUser.getNameUser(),
				newUser.getEmail(),
				passwordEncoder.encode(newUser.getPassword()));
		Set<Role> roles = new HashSet<>();
		roles.add(roleService.getByRolName(RoleName.ROLE_USER).get());
		
		if(newUser.getRoles().contains("admin"))
			roles.add(roleService.getByRolName(RoleName.ROLE_ADMIN).get());
		if(newUser.getRoles().contains("super"))
			roles.add(roleService.getByRolName(RoleName.ROLE_SUPER_ADMIN).get());
			
		user.setRoles(roles);
		userService.save(user);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Usuario creado con exito!");
	}
	
	@PostMapping(name="login", path = "login")
	public ResponseEntity<?> login(@Valid @RequestBody LoginUser loginUser, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			ErrorSimple error = new ErrorSimple();
			error.setId(4);
			error.setDescripcion("Credenciales incompletas");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
		
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginUser.getNameUser(), loginUser.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String jwt = jwtProvider.generateToken(authentication,loginUser);
		
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		JwtDTO jwtDto = new JwtDTO(jwt, userDetails.getUsername(), userDetails.getAuthorities());
		
		return ResponseEntity.status(HttpStatus.CREATED).body(jwtDto);
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping(name="users", path="users")
	public ResponseEntity<?> getUsers() {
		
		List<User> listUser = userService.findAll();
		
		return ResponseEntity.status(HttpStatus.CREATED).body(listUser);
	}
	
	
	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping(name="deletUser", path="{id}")
	public ResponseEntity<?> deletUser(@PathVariable("id") Long id) {
		
		User userDelet = userService.findById(id);
		if(userDelet != null) {
			if(userService.delete(userDelet))
				return ResponseEntity.ok().body("Se elimino correctamente");
			
			return new ResponseEntity(new Mensajes("Error al eliminar"), HttpStatus.CREATED);			
		}
		
		return new ResponseEntity(new Mensajes("Usuario no encontrado"), HttpStatus.CREATED);
	}
	
	

}
