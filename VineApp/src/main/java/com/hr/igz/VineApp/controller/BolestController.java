package com.hr.igz.VineApp.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hr.igz.VineApp.domain.dto.BolestDto;
import com.hr.igz.VineApp.services.BolestService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@Slf4j
public class BolestController {
	
	private BolestService bolestService;
	
	@Autowired
	public BolestController(BolestService bolestService) {
		this.bolestService = bolestService;
	}
	
	@PostMapping(value="/addBolest")
	public ResponseEntity<Object> dodajBolest(@Validated @RequestBody BolestDto bolest){
		log.info("Pokrenut insert bolesti!");
		return bolestService.addBolest(bolest);
	}
	
	@GetMapping(value="/getBolestiPaged")
	public ResponseEntity <Map<String,Object>> dohvatiBolestiPaged(
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		return bolestService.getBolestiPaged(pageSize,pageNo,sort);	
	}
	
	@DeleteMapping(value = "/deleteBolest")
	public ResponseEntity<Object> obrisiBolest(@RequestParam String name){
		return bolestService.deleteBolestByName(name);
	}

}
