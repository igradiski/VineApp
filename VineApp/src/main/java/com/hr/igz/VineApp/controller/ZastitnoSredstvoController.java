package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.service.SredstvoService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/zastitno-sredstvo")
public class ZastitnoSredstvoController {

	private final SredstvoService sredstvoService;
	private Logger log = LoggerFactory.getLogger(ZastitnoSredstvoController.class);

	public ZastitnoSredstvoController(SredstvoService sredstvoService) {
		this.sredstvoService = sredstvoService;
	}

	@PostMapping
	@Operation(summary= "Operacija za dodavanje novog sredstva")
	public ResponseEntity<Object> dodajSredstvo(@Validated @RequestBody SredstvoDto sredstvo) {
		return ResponseEntity.status(HttpStatus.CREATED).body(sredstvoService.addSredstvo(sredstvo));
	}

	@GetMapping(value = "/all")
	@Operation(summary= "Dohvaca zastitna sredstva po stranicama")
	public Page<SredstvoDto> getAllSredstvaPage(Pageable pageable) {
		return sredstvoService.getAllSredstvaPagable(pageable);
	}

	@GetMapping(value= "/sredstva-by-name/{name}")
	@Operation(summary= "Dohvaca zastitna sredstva po upitu koji sadrzava ime")
	public Page<SredstvoDto> getSredstvaByNamePaged(Pageable pageable, @PathVariable String name){
		return sredstvoService.findSredstvoByNamePaged(pageable,name);
	}

	@GetMapping(value= "/sredstva-name/{name}")
	@Operation(summary= "Dohvaca zastitno sredstvo prema imenu")
	public ResponseEntity<SredstvoDto> getSredstvoByName(@PathVariable String name){
		return ResponseEntity.status(HttpStatus.OK).body(sredstvoService.findSredstvoByName(name));
	}

	@GetMapping(value = "/sredstva-card/{id}")
	public ResponseEntity<SredstvoDto> getSredstvoForCard(@PathVariable Long id){
		return ResponseEntity.status(HttpStatus.OK).body(sredstvoService.getSredstvoForCard(id));
	}

	@GetMapping(value = "/utrosak/{water}/{id}")
	@Operation(description = "Dohvacanje preporucene kolicine sredstva ")
	public Optional<Object> getUtrosak(@PathVariable Integer water, @PathVariable Long id){
		return sredstvoService.getUtrosak(water,id);
	}

	@GetMapping(value= "/sva-sredstva-cascader")
	@Operation(summary= "Dohvaca zastitna sredstva za cascader")
	public ResponseEntity<Set<AntDCascaderDto>> getSredstvaForCascader(){
		return sredstvoService.getSredstvaForCascader();
	}

	@PutMapping
	@Operation(summary= "Operacija za update novog sredstva")
	public ResponseEntity<Object> updateSredstvo(@Validated @RequestBody SredstvoDto sredstvoDto){
		return ResponseEntity.status(HttpStatus.OK).body(sredstvoService.updateSredstvo(sredstvoDto));
	}

	@DeleteMapping("/{id}")
	@Operation(summary= "Operacija za brisanje zastitnog sredstva")
	public ResponseEntity<Object> deleteSredstvo(@PathVariable Long id){
		return sredstvoService.deleteSredstvoById(id);
	}
}
