package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import com.hr.igz.VineApp.service.BolestService;
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
@RequestMapping("/bolest")
public class BolestController {

	private final BolestService bolestService;
	private Logger log = LoggerFactory.getLogger(BolestController.class);

	public BolestController(BolestService bolestService) {
		this.bolestService = bolestService;
	}

	@PostMapping
	@Operation(summary= "Operacija za unos nove bolesti")
	public ResponseEntity<BolestDto> dodajBolest(@Validated @RequestBody BolestDto bolest){
		return ResponseEntity.status(HttpStatus.CREATED).body(bolestService.addBolest(bolest));
	}

	@GetMapping
	@Operation(summary= "Operacija za dohvacanje bolesti sa stranicenjem")
	public Page<BolestDto> dohvatiBolestiPaged(Pageable pageable){
		return bolestService.getBolestiPaged(pageable);
	}

	@GetMapping(value = "/by-name/{name}")
	@Operation(summary= "Operacija za dohvacanje bolesti prema imenu sa stranicenjem")
	public Page<BolestDto> findBolestiByNamePaged(@PathVariable String name, Pageable pageable){
		return bolestService.findBolestByNamePaged(pageable,name);
	}

	@GetMapping(value = "/bolest-name/{name}")
	@Operation(summary= "Operacija za dohvacanje bolesti prema imenu")
	public Optional<BolestDto> findBolestByName(@PathVariable String name){
		return bolestService.findBolestByName(name);
	}

	@GetMapping(value = "/bolest-card/{id}")
	@Operation(summary = "Dohvaca bolest za prikaz u kartici")
	public Optional<BolestDto> getBolestForCard(@PathVariable Long id){
		return bolestService.getBolestForCard(id);
	}

	@GetMapping(value="/cascader")
	@Operation(summary= "Operacija za dohvacanje bolesti za cascader u antd")
	public ResponseEntity<Set<AntDCascaderDto>> dohvatiBolestiZaCascader(){
		return bolestService.getBolestiZaCascader();
	}

	@PutMapping
	@Operation(summary= "Operacija za azuriranje bolesti")
	public ResponseEntity<Object> updateBolest(@Validated @RequestBody BolestDto bolestDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(bolestService.updateBolest(bolestDto));
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary= "Operacija za brisanje bolesti")
	public ResponseEntity<Object> obrisiBolest(@PathVariable("id") Long id){
		return bolestService.deleteBolestById(id);
	}
}
