package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import com.hr.igz.VineApp.services.FenofazaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/fenofaza")
@Slf4j
@AllArgsConstructor
public class FenofazaController {
	
	private final FenofazaService fenofazaService;

	@GetMapping(value = "/fenofaze-by-name")
	@Operation(summary= "Operacija za dohvacanje fenofaza prema imenu sa stranicenjem")
	public Page<FenofazaDto> findFenofazaByNamePaged(
			@RequestParam String name,
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){

		return fenofazaService.findFenofazaByNamePaged(pageSize,pageNo,sort,name);
	}

	@GetMapping(value= "fenofaze-name")
	@Operation(summary = "Operacija za dohvacanje odredene fenofaze")
	public Optional<FenofazaDto> findFenofazaByname(@RequestParam (name = "faza")String name){
		return fenofazaService.findFenofazaByName(name);
	}
	
	@GetMapping(value="/fenofaze")
	@Operation(summary= "Operacija za dohvacanje fenofaza sa stranicenjem")
	public Page<FenofazaDto> getFenofazePaged(
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		return fenofazaService.getFenofazePaged(pageSize,pageNo,sort);
	}

	@GetMapping(value ="/sve-fenofaze-cascader")
	@Operation(summary= "Operacija za dohvacanje fenofaza za ant d cascader")
	public ResponseEntity<Set<AntDCascaderDto>> getFenofazeZaCascader(){
		return fenofazaService.getFenofazeZaCascader();
	}
	
	@PostMapping(value="/fenofaze")
	@Operation(summary= "Operacija za unos fenofaze")
	public ResponseEntity<Object> addFenofazu(@Validated @RequestBody FenofazaDto fenofaza){
		return fenofazaService.addFenofaza(fenofaza);
	}
	
	@PatchMapping(value="/fenofaze")
	@Operation(summary= "Operacija za azuriranje fenofaze")
	public ResponseEntity<Object> updateFenofaza(
			@Validated @RequestBody FenofazaDto fenofaza,
			@RequestParam Long id){
		return fenofazaService.updateFenofaza(fenofaza,id);
	}
	
	@DeleteMapping(value = "/fenofaze")
	@Operation(summary= "Operacija za brisanje fenofaze")
	public ResponseEntity<Object> deleteFenofaza(@RequestParam Long id){
		return fenofazaService.deleteFenofazaById(id);
	}
}
