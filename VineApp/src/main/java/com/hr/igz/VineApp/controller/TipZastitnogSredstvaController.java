package com.hr.igz.VineApp.controller;

import java.util.Map;
import java.util.Set;

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

import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import com.hr.igz.VineApp.services.TipSredstvaService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tip_sredstva")
@Slf4j
public class TipZastitnogSredstvaController {
	
	private TipSredstvaService tipSredstvaService;
	
	@Autowired
	public TipZastitnogSredstvaController(TipSredstvaService tipSredstvaService) {
		this.tipSredstvaService = tipSredstvaService;
	}
	
	@GetMapping("/svi-tipovi-paged")
	public ResponseEntity<Map<String, Object>> getAllTipoviSredstavaPage(
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		
		log.info("Pokrenuto dohvacanje tipa sredstava na stranici : {}",pageNo);
		return tipSredstvaService.findAllPagable(pageSize,pageNo,sort);
	}
	
	@GetMapping("/svi-tipovi")
	public ResponseEntity<Set<Object>> getAllTipoviSredstava (){
		
		log.info("Pokrenuto dohvacanje svih sredstava");
		return tipSredstvaService.findAll();
	}
	
	@GetMapping(value = "/tip-by-name")
	public ResponseEntity<Map<String,Object>> findTipSredstvaByNamePaged(
			@RequestParam String name,
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		
		log.info("Pokrenuto pretraživanje tipa sredstava prema imenu:{}",name);
		return tipSredstvaService.findTipSredstvaByName(pageSize,pageNo,sort,name);
	}
	
	@PostMapping(value = "/novi-tip")
	public ResponseEntity<Object> dodajTipSredstva(@Validated @RequestBody TipSredstvaDto tipSredstva){
		
		log.info("Pokrenuto dodavanje sredstva; {}",tipSredstva.toString());
		return tipSredstvaService.dodajTipSredstva(tipSredstva);
	}
	
	@PutMapping(value="azurirani-tip")
	public ResponseEntity<Object> updateTipSredstva(
			@Validated @RequestBody TipSredstvaDto tipSredstva,
			@RequestParam Long id){
		
		log.info("Pokrenuto ažuriranje tipa sredstva s id: {}",id);
		return tipSredstvaService.updateTipSredstva(tipSredstva,id);
	}
	
	
	@DeleteMapping(value = "/")
	public ResponseEntity<Object> deleteTipSredstva(@RequestParam Long id){
		
		log.info("Pokrenuto brisanje tipa sredstva s id: {}",id);
		return tipSredstvaService.deleteTipSredstvaById(id);
		
	}

}
