package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.services.SredstvoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/zastitno-sredstvo")
@Slf4j
@RequiredArgsConstructor
public class ZastitnoSredstvoController {

	private final SredstvoService sredstvoService;

	@GetMapping(value = "/sredstva")
	@Operation(summary= "Dohvaca zastitna sredstva po stranicama")
	public Page<SredstvoDto> getAllSredstvaPage(
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "3") int pageSize, 
			@RequestParam(defaultValue = "id,desc") String[] sort) {

		return sredstvoService.getAllSredstvaPagable(pageNo,pageSize,sort);
	}

	@GetMapping(value= "/sredstva-by-name")
	@Operation(summary= "Dohvaca zastitna sredstva po upitu koji sadrzava ime")
	public Page<SredstvoDto> getSredstvaByNamePaged(
			@RequestParam String name,
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		return sredstvoService.findSredstvoByNamePaged(pageSize,pageNo,sort,name);
	}

	@GetMapping(value= "/sredstva-name")
	@Operation(summary= "Dohvaca zastitno sredstvo prema imenu")
	public Optional<SredstvoDto> getSredstvoByName(@RequestParam String name){
		return sredstvoService.findSredstvoByName(name);
	}

	@GetMapping(value = "/sredstva-card")
	public Optional<SredstvoDto> getSredstvoForCard(@RequestParam Long id){
		return sredstvoService.getSredstvoForCard(id);
	}

	@GetMapping(value = "/utrosak")
	@Operation(description = "Dohvacanje preporucene kolicine sredstva ")
	public Optional<Object> getUtrosak(@RequestParam Integer water, @RequestParam Long id){
		return sredstvoService.getUtrosak(water,id);
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
