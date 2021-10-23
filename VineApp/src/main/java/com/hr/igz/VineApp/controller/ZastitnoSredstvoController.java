package com.hr.igz.VineApp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.services.SredstvoService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/zastitno_sredstvo")
@Slf4j
public class ZastitnoSredstvoController {

	private SredstvoService sredstvoService;

	@Autowired
	public ZastitnoSredstvoController(SredstvoService service) {
		this.sredstvoService = service;
	}

	@GetMapping(value = "/getSredstvaPage")
	public ResponseEntity<Map<String, Object>> getAllSredstvaPage(
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "3") int pageSize, 
			@RequestParam(defaultValue = "id,desc") String[] sort) {
		
		log.info("Pokrenuto dohvacanje zastinih sredstava sa stranicenjem");
		return sredstvoService.getAllSredstvaPagable(pageNo,pageSize,sort);
	}

	@PostMapping(value = "/addSredstvo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> dodajSredstvo(@Validated @RequestBody SredstvoDto sredstvo) {
		log.info("Pokrenuto dodavanje novog sredstva!");
		return sredstvoService.addSredstvo(sredstvo);
	}

}
