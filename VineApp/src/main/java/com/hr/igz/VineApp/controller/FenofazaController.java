package com.hr.igz.VineApp.controller;

import java.util.Map;
import java.util.Set;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import com.hr.igz.VineApp.services.FenofazaService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/fenofaza")
@Slf4j
@AllArgsConstructor
public class FenofazaController {
	
	private final FenofazaService fenofazaService;

	@GetMapping(value = "/fenofaze-name")
	@Operation(summary= "Operacija za dohvacanje fenofaza prema imenu sa stranicenjem")
	public ResponseEntity <Map<String,Object>> findFenofazaByNamePaged(
			@RequestParam String name,
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){

		return fenofazaService.findFenofazaByNamePaged(pageSize,pageNo,sort,name);
	}
	
	@GetMapping(value="/fenofaze")
	@Operation(summary= "Operacija za dohvacanje fenofaza sa stranicenjem")
	public ResponseEntity<Map<String,Object>> getFenofazePaged(
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
