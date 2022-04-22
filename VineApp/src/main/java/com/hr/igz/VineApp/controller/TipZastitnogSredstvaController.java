package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import com.hr.igz.VineApp.service.TipSredstvaService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/tip_sredstva")
public class TipZastitnogSredstvaController {

	private final TipSredstvaService tipSredstvaService;
	private Logger log = LoggerFactory.getLogger(TipZastitnogSredstvaController.class);

	public TipZastitnogSredstvaController(TipSredstvaService tipSredstvaService) {
		this.tipSredstvaService = tipSredstvaService;
	}

	@GetMapping("/svi-tipovi-paged")
	@Operation(summary= "Operacija dohvacanje svih tipova sredstava sa stranicenjem")
	public Page<TipSredstvaDto> getAllTipoviSredstavaPage(Pageable pageable){
		return tipSredstvaService.findAllPagable(pageable);
	}
	
	@GetMapping("/svi-tipovi")
	@Operation(summary= "Operacija za dohvacanje svih tipova sredstava")
	public ResponseEntity<Set<Object>> getAllTipoviSredstava (){
		return tipSredstvaService.findAll();
	}
	
	@GetMapping(value = "/tip-by-name/{name}")
	@Operation(summary= "Operacija za dohvacanje svih tipova sredstava prema imenu")
	public Page<TipSredstvaDto> findTipSredstvaByNamePaged(Pageable pageable,
			@PathVariable String name){
		return tipSredstvaService.findTipSredstvaByName(pageable,name);
	}
	
	@PostMapping
	@Operation(summary= "Operacija za unos tipa sredstva")
	public ResponseEntity<Object> dodajTipSredstva(@Validated @RequestBody TipSredstvaDto tipSredstva){
		return tipSredstvaService.dodajTipSredstva(tipSredstva);
	}
	
	@PutMapping(value="azurirani-tip")
	@Operation(summary= "Operacija za azuriranje tipa sredstva")
	public ResponseEntity<Object> updateTipSredstva(
			@Validated @RequestBody TipSredstvaDto tipSredstva){
		return tipSredstvaService.updateTipSredstva(tipSredstva);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary= "Operacija za brisanje tipa sredstva")
	public ResponseEntity<Object> deleteTipSredstva(@PathVariable Long id){
		return tipSredstvaService.deleteTipSredstvaById(id);
	}
}
