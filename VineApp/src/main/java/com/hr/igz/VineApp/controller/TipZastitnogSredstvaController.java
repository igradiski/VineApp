package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import com.hr.igz.VineApp.service.TipSredstvaService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/tip_sredstva")
public class TipZastitnogSredstvaController {

	private final TipSredstvaService tipSredstvaService;
	private Logger log = LoggerFactory.getLogger(TipZastitnogSredstvaController.class);

	public TipZastitnogSredstvaController(TipSredstvaService tipSredstvaService) {
		this.tipSredstvaService = tipSredstvaService;
	}

	@PostMapping
	@Operation(summary= "Operacija za unos tipa sredstva")
	public ResponseEntity<Object> dodajTipSredstva(@Validated @RequestBody TipSredstvaDto tipSredstva){
		return ResponseEntity.status(HttpStatus.CREATED).body(tipSredstvaService.dodajTipSredstva(tipSredstva));
	}

	@GetMapping
	@Operation(summary= "Operacija dohvacanje svih tipova sredstava sa stranicenjem")
	public Page<TipSredstvaDto> getAllTipoviSredstavaPage(Pageable pageable){
		return tipSredstvaService.findAllPagable(pageable);
	}
	
	@GetMapping("/all")
	@Operation(summary= "Operacija za dohvacanje svih tipova sredstava")
	public ResponseEntity<List<AntDCascaderDto>> getAllTipoviSredstava (){
		return ResponseEntity.status(HttpStatus.OK).body(tipSredstvaService.findAll());
	}
	
	@GetMapping(value = "/by-name/{name}")
	@Operation(summary= "Operacija za dohvacanje svih tipova sredstava prema imenu")
	public Page<TipSredstvaDto> findTipSredstvaByNamePaged(Pageable pageable,
			@PathVariable String name){
		return tipSredstvaService.findTipSredstvaByName(pageable,name);
	}

	@PutMapping
	@Operation(summary= "Operacija za azuriranje tipa sredstva")
	public ResponseEntity<Object> updateTipSredstva(
			@Validated @RequestBody TipSredstvaDto tipSredstva){
		return ResponseEntity.status(HttpStatus.OK).body(tipSredstvaService.updateTipSredstva(tipSredstva));
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary= "Operacija za brisanje tipa sredstva")
	public ResponseEntity<Object> deleteTipSredstva(@PathVariable Long id){
		return tipSredstvaService.deleteTipSredstvaById(id);
	}
}
