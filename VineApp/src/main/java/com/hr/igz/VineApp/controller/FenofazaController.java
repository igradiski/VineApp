package com.hr.igz.VineApp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	public ResponseEntity<Object> addFenofazu(@Validated @RequestBody FenofazaDto fenofaza){
		
		log.info("Pokrenuto dodavanje fenofaze!");
		return fenofazaService.addFenofaza(fenofaza);
	}
	
	@GetMapping(value="/getFenofazePaged")
	public ResponseEntity<Map<String,Object>> getFenofazePaged(
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		
		log.info("Pokrenuto dohvacanje fenofaza za sifrarnik!");
		return fenofazaService.getFenofazePaged(pageSize,pageNo,sort);
		
	}
	
	@PutMapping(value="/updateFenofaza")
	public ResponseEntity<Object> updateFenofaza(
			@Validated @RequestBody FenofazaDto fenofaza,
			@RequestParam Long id){
		
		log.info("Pokrenut update fenofaze ID: {}", id);
		return fenofazaService.updateFenofaza(fenofaza,id);
	}
	
	@GetMapping(value = "/findFenofazaByName")
	public ResponseEntity <Map<String,Object>> findFenofazaByNamePaged(
			@RequestParam String name,
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		return fenofazaService.findFenofazaByNamePaged(pageSize,pageNo,sort,name);
		
	}
	
	@DeleteMapping(value = "/deleteFenofaza")
	public ResponseEntity<Object> deleteFenofaza(@RequestParam Long id){
		return fenofazaService.deleteFenofazaById(id);
	}

}
