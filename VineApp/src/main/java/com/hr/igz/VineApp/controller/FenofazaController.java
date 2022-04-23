package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import com.hr.igz.VineApp.service.FenofazaService;
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
@RequestMapping("/fenofaza")
public class FenofazaController {

	private final FenofazaService fenofazaService;
	private Logger log = LoggerFactory.getLogger(FenofazaController.class);

	public FenofazaController(FenofazaService fenofazaService) {
		this.fenofazaService = fenofazaService;
	}

	@PostMapping
	@Operation(summary= "Operacija za unos fenofaze")
	public ResponseEntity<Object> addFenofazu(@Validated @RequestBody FenofazaDto fenofaza){
		return ResponseEntity.status(HttpStatus.CREATED).body(fenofazaService.addFenofaza(fenofaza));
	}

	@GetMapping(value = "/by-name/{name}")
	@Operation(summary= "Operacija za dohvacanje fenofaza prema imenu sa stranicenjem")
	public Page<FenofazaDto> findFenofazaByNamePaged(@PathVariable String name, Pageable pageable){
		return fenofazaService.findFenofazaByNamePaged(pageable,name);
	}

	@GetMapping(value= "/name/{name}")
	@Operation(summary = "Operacija za dohvacanje odredene fenofaze")
	public Optional<FenofazaDto> findFenofazaByname(@PathVariable String name){
		return fenofazaService.findFenofazaByName(name);
	}
	
	@GetMapping
	@Operation(summary= "Operacija za dohvacanje fenofaza sa stranicenjem")
	public Page<FenofazaDto> getFenofazePaged(Pageable pageable){
		return fenofazaService.getFenofazePaged(pageable);
	}

	@GetMapping(value ="/sve-fenofaze-cascader")
	@Operation(summary= "Operacija za dohvacanje fenofaza za ant d cascader")
	public ResponseEntity<Set<AntDCascaderDto>> getFenofazeZaCascader(){
		return fenofazaService.getFenofazeZaCascader();
	}

	@PutMapping
	@Operation(summary= "Operacija za azuriranje fenofaze")
	public ResponseEntity<Object> updateFenofaza(@Validated @RequestBody FenofazaDto fenofaza){
		return ResponseEntity.status(HttpStatus.OK).body(fenofazaService.updateFenofaza(fenofaza));
	}
	
	@DeleteMapping(value = "/{id}")
	@Operation(summary= "Operacija za brisanje fenofaze")
	public ResponseEntity<Object> deleteFenofaza(@PathVariable Long id){
		return fenofazaService.deleteFenofazaById(id);
	}
}
