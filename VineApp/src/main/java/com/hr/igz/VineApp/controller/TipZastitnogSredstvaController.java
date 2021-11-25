package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import com.hr.igz.VineApp.services.TipSredstvaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/tip_sredstva")
@Slf4j
@RequiredArgsConstructor
public class TipZastitnogSredstvaController {
	
	private final TipSredstvaService tipSredstvaService;

	@GetMapping("/svi-tipovi-paged")
	@Operation(summary= "Operacija dohvacanje svih tipova sredstava sa stranicenjem")
	public Page<TipSredstvaDto> getAllTipoviSredstavaPage(
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		return tipSredstvaService.findAllPagable(pageSize,pageNo,sort);
	}
	
	@GetMapping("/svi-tipovi")
	@Operation(summary= "Operacija za dohvacanje svih tipova sredstava")
	public ResponseEntity<Set<Object>> getAllTipoviSredstava (){
		return tipSredstvaService.findAll();
	}
	
	@GetMapping(value = "/tip-by-name")
	@Operation(summary= "Operacija za dohvacanje svih tipova sredstava prema imenu")
	public Page<TipSredstvaDto> findTipSredstvaByNamePaged(
			@RequestParam String name,
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		return tipSredstvaService.findTipSredstvaByName(pageSize,pageNo,sort,name);
	}
	
	@PostMapping(value = "/novi-tip")
	@Operation(summary= "Operacija za unos tipa sredstva")
	public ResponseEntity<Object> dodajTipSredstva(@Validated @RequestBody TipSredstvaDto tipSredstva){
		return tipSredstvaService.dodajTipSredstva(tipSredstva);
	}
	
	@PatchMapping(value="azurirani-tip")
	@Operation(summary= "Operacija za azuriranje tipa sredstva")
	public ResponseEntity<Object> updateTipSredstva(
			@Validated @RequestBody TipSredstvaDto tipSredstva,
			@RequestParam Long id){
		return tipSredstvaService.updateTipSredstva(tipSredstva,id);
	}

	@DeleteMapping(value = "/tip")
	@Operation(summary= "Operacija za brisanje tipa sredstva")
	public ResponseEntity<Object> deleteTipSredstva(@RequestParam Long id){
		return tipSredstvaService.deleteTipSredstvaById(id);
	}
}
