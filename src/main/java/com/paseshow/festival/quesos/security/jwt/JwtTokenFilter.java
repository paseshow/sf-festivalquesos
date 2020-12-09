package com.paseshow.festival.quesos.security.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.servletapi.SecurityContextHolderAwareRequestFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import com.paseshow.festival.quesos.services.UserDetailsServiceImpl;

public class JwtTokenFilter extends OncePerRequestFilter{

	
	//class que permite el paso a las peticiones
	
	private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);
	
	@Autowired
	JwtProvider jwtProvider;
	
	@Autowired
	UserDetailsServiceImpl userDetailServiceImpl;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		//se va a disparar una vez por cada peticion
		try {
			
			String token = getToken(request);
			
			//validamos token
			if(token != null && jwtProvider.validToken(token)) {
				//obtenemos el usuario
				String nameUser = jwtProvider.getNameUserFromToken(token);
			
				UserDetails userDetails = userDetailServiceImpl.loadUserByUsername(nameUser);
				
				//creamos una autenticacion
				UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			}
			
		} catch (Exception e) {
			logger.error("Error en el método filter " + e.getMessage());
		}
		
		filterChain.doFilter(request, response);
	}
	
	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		
		if(header != null && header.startsWith("Bearer"))
			return header.replace("Bearer ", "");
		
		return null;
	}
	
}
