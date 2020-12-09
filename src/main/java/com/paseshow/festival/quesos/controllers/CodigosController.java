package com.paseshow.festival.quesos.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.validation.Valid;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.paseshow.festival.quesos.model.util.ErrorSimple;
import com.paseshow.festival.quesos.models.dao.CodigosDao;
import com.paseshow.festival.quesos.models.entity.Codigosingresos;
import com.paseshow.festival.quesos.models.entity.Eventoquesos;
import com.paseshow.festival.quesos.models.entity.Formhome;
import com.paseshow.festival.quesos.services.CodigosServiceImpl;
import com.paseshow.festival.quesos.services.EventosquesosServiceImpl;
import com.paseshow.festival.quesos.services.IFormhomeServiceImpl;
import com.paseshow.festival.quesos.services.ResourceNotFoundException;
import com.paseshow.festival.quesos.models.dto.codigosDTO;

import static org.apache.commons.codec.binary.Base64.encodeBase64;

@RestController
@RequestMapping("/codigos")
@CrossOrigin
public class CodigosController {
	
	// Algoritmo (AES, DES, RSA)
    private final static String algoritmo = "AES";
    
    // Tipo de cifrado, por bloques, padding etc.
    private final static String tipoCifrado = "AES/CBC/PKCS5Padding";
	
	String psk = "6DwRNHTN4zQZDeE9";
	
	String iv = "A5r9jDbJtk4YjT2C";
	
	@Autowired
	private CodigosServiceImpl codigosServiceImpl;
	
	@Autowired
	private EventosquesosServiceImpl eventosquesosServiceImpl;
	
	@Autowired
	private IFormhomeServiceImpl formhomeServiceImpl;
	
	@PreAuthorize("hasRole('USER')")
	@PostMapping(name="save", path="loadCodigos")
	public ResponseEntity<?> loadCodigos(@RequestBody List<Codigosingresos> codigos, BindingResult bindingResult)
	throws ResourceNotFoundException {
		if(bindingResult.hasErrors()) {
			ErrorSimple error = new ErrorSimple();
			error.setId(1);
			error.setDescripcion("Erro en el servidor");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
		
		for(Codigosingresos codigo : codigos) {
			if(!codigosServiceImpl.existById(codigo.getId())) {
				codigosServiceImpl.save(codigo);
			}
		}
		
		return ResponseEntity.ok().body("Se guardaron exitosamente!");
		
	}
	
	@GetMapping(name="verifyCode", path="code")
	public ResponseEntity<?> verificarCodigo(@Valid @RequestBody codigosDTO codigosDTO, BindingResult bindingResult)
	throws ResourceNotFoundException{
		
		if(bindingResult.hasErrors()) {
			ErrorSimple error = new ErrorSimple();
			error.setId(1);
			error.setDescripcion("Codigo vacio");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		}
		
		if(codigosServiceImpl.existById(codigosDTO.getId())) {
			
			Eventoquesos evento = eventosquesosServiceImpl.findByid(codigosDTO.getIdEvent());
			if(evento.getActive() && evento != null)  {
				Formhome formUser = formhomeServiceImpl.findById(codigosDTO.getIdUser());
				String codigos = formUser.getIdCodigo();
				if(codigos != "") 
					codigos = codigos + "," + codigosDTO.getId().toString();
				else
					codigos = codigosDTO.getId().toString();
				
				
				
				formUser.setIdCodigo(codigos);
				formhomeServiceImpl.save(formUser);
				
				Map<String, String> dev = new HashMap<String, String>();
			
				try {
					String pattern = "(?<=watch\\?v=|/videos/|embed\\/|youtu.be\\/|\\/v\\/|\\/e\\/|watch\\?v%3D|"
							+ "watch\\?feature=player_embedded&v=|%2Fvideos%2F|embed%\u200C\u200B2F|youtu.be%2F|%2Fv%2F)"
							+ "[^#\\&\\?\\n]*";

			        Pattern compiledPattern = Pattern.compile(pattern);
			        Matcher matcher = compiledPattern.matcher(evento.getLinkEvent());       
			        String codigoYoutube = "";
					
			        if (matcher.find()) {
			        	codigoYoutube =  matcher.group();
			        	String urlEncrypt = this.encrypt(psk, iv, codigoYoutube);
						dev.put("dghjoi3543u", urlEncrypt);
						//dev.put("ff", fec.toString());
						
						return ResponseEntity.ok().body(dev);									
					}
					
				}catch (Exception e) {
					ErrorSimple error = new ErrorSimple();
					error.setId(3);
					error.setDescripcion("Error al cargar video");
					Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
					mapError.put("error", error);
					return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
				}				
				
			}
			
			ErrorSimple error = new ErrorSimple();
			error.setId(3);
			error.setDescripcion("Evento Inavilitado");
			Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
			mapError.put("error", error);
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
			
		}
		
		ErrorSimple error = new ErrorSimple();
		error.setId(2);
		error.setDescripcion("Codigo Invalido");
		Map<String, ErrorSimple> mapError = new HashMap<String, ErrorSimple>();
		mapError.put("error", error);
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mapError);
		
	};
	
	public static String encrypt(String llave, String iv, String texto) throws Exception {
        Cipher cipher = Cipher.getInstance(tipoCifrado);
        SecretKeySpec secretKeySpec = new SecretKeySpec(llave.getBytes(), algoritmo);
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv.getBytes());
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivParameterSpec);
        byte[] encrypted = cipher.doFinal(texto.getBytes());
        return new String(encodeBase64(encrypted));
	}


}
