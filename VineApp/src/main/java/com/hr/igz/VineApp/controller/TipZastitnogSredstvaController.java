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

import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import com.hr.igz.VineApp.services.TipSredstvaService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Slf4j
public class TipZastitnogSredstvaController {
	
	private TipSredstvaService tipSredstvaService;
	
	@Autowired
	public TipZastitnogSredstvaController(TipSredstvaService tipSredstvaService) {
		this.tipSredstvaService = tipSredstvaService;
	}
	
	@PostMapping(value = "/noviTipSredstva",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> dodajTipSredstva(@Validated @RequestBody TipSredstvaDto tipSredstva){
		log.info("Ime:{}",tipSredstva.getName());
		return tipSredstvaService.dodajTipSredstva(tipSredstva);
	}
	
	@GetMapping("/tipoviSredstava")
	public ResponseEntity<Map<String, Object>> getAllTipoviSredstava(
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "10") int pageSize){
		
		log.info("Page:{} +  {}",pageNo,pageSize);
		return tipSredstvaService.findAllPagable(pageNo,pageSize);
	}

}
