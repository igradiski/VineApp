package com.hr.igz.VineApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import com.hr.igz.VineApp.services.FenofazaService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Slf4j
public class FenofazaController {
	
	private FenofazaService fenofazaService;
	
	@Autowired
	public FenofazaController(FenofazaService fenofazaService) {
		this.fenofazaService=fenofazaService;
	}
	
	@PostMapping(value="/addFenofaza")
	public ResponseEntity<Object> dodajFenofazu(@Validated @RequestBody FenofazaDto fenofaza){
		log.info("Pokrenuto dodavanje fenofaze!");
		return fenofazaService.addFenofaza(fenofaza);
		
	}

}
