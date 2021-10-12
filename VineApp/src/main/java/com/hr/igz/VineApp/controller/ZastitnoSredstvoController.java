package com.hr.igz.VineApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.services.SredstvoService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Slf4j
public class ZastitnoSredstvoController {
	
	private SredstvoService sredstvoService;
	
	@Autowired
	public ZastitnoSredstvoController(SredstvoService service) {
		this.sredstvoService = service;
	}
	
	
	@PostMapping(value = "/addSredstvo",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> dodajSredstvo(@Validated @RequestBody SredstvoDto sredstvo){
		log.info("Pokrenuto dodavanje novog sredstva!");
		return sredstvoService.addSredstvo(sredstvo);
	}

}
