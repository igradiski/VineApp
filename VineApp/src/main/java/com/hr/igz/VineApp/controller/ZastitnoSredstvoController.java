package com.hr.igz.VineApp.controller;

import java.util.Map;
import java.util.Set;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.services.SredstvoService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/zastitno_sredstvo")
@Slf4j
@RequiredArgsConstructor
public class ZastitnoSredstvoController {

	private final SredstvoService sredstvoService;

	@GetMapping(value = "/sredstva")
	@Operation(summary= "Dohvaca zastitna sredstva po stranicama")
	public ResponseEntity<Map<String, Object>> getAllSredstvaPage(
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "3") int pageSize, 
			@RequestParam(defaultValue = "id,desc") String[] sort) {

		return sredstvoService.getAllSredstvaPagable(pageNo,pageSize,sort);
	}

	@GetMapping(value= "/sredstva-name")
	@Operation(summary= "Dohvaca zastitna sredstva po upitu koji sadrzava ime")
	public ResponseEntity<Map<String,Object>> getSredstvaByNamePaged(
			@RequestParam String name,
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		return sredstvoService.findSredstvoByNamePaged(pageSize,pageNo,sort,name);
	}

	@GetMapping(value= "/sva-sredstva-cascader")
	@Operation(summary= "Dohvaca zastitna sredstva za cascader")
	public ResponseEntity<Set<AntDCascaderDto>> getSredstvaForCascader(){
		return sredstvoService.getSredstvaForCascader();
	}

	@PostMapping(value = "/sredstva")
	@Operation(summary= "Operacija za dodavanje novog sredstva")
	public ResponseEntity<Object> dodajSredstvo(@Validated @RequestBody SredstvoDto sredstvo) {
		return sredstvoService.addSredstvo(sredstvo);
	}

	@PatchMapping(value = "/sredstva")
	@Operation(summary= "Operacija za update novog sredstva")
	public ResponseEntity<Object> updateSredstvo(
			@Validated @RequestBody SredstvoDto sredstvoDto,
			@RequestParam Long id){
		return sredstvoService.updateSredstvo(sredstvoDto,id);
	}

	@DeleteMapping(value = "/sredstva")
	@Operation(summary= "Operacija za brisanje zastitnog sredstva")
	public ResponseEntity<Object> deleteSredstvo(@RequestParam Long id){
		return sredstvoService.deleteSredstvoById(id);
	}
}
