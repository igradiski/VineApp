package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import com.hr.igz.VineApp.services.BolestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bolest")
@Slf4j
@RequiredArgsConstructor
public class BolestController {
	
	private final BolestService bolestService;

	@GetMapping(value = "/bolest-by-name")
	@Operation(summary= "Operacija za dohvacanje bolesti prema imenu sa stranicenjem")
	public Page<BolestDto> findBolestiByNamePaged(
			@RequestParam String name,
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		return bolestService.findBolestByNamePaged(pageSize,pageNo,sort,name);
	}

	@GetMapping(value = "bolest-name")
	public Optional<BolestDto> findBolestByName(@RequestParam String name){
		return bolestService.findBolestByName(name);
	}

	@GetMapping(value="/sve-bolesti")
	@Operation(summary= "Operacija za dohvacanje bolesti sa stranicenjem")
	public Page<BolestDto> dohvatiBolestiPaged(
			@RequestParam(defaultValue = "2") int pageSize,
			@RequestParam(defaultValue = "0") int pageNo,
			@RequestParam(defaultValue = "id,desc") String [] sort){
		return bolestService.getBolestiPaged(pageSize,pageNo,sort);	
	}
	@GetMapping(value="/sve-bolesti-cascader")
	@Operation(summary= "Operacija za dohvacanje bolesti za cascader u antd")
	public ResponseEntity<Set<AntDCascaderDto>> dohvatiBolestiZaCascader(){
		return bolestService.getBolestiZaCascader();
	}
	
	@PostMapping(value="/nova-bolest")
	@Operation(summary= "Operacija za unos nove bolesti")
	public ResponseEntity<Object> dodajBolest(@Validated @RequestBody BolestDto bolest){
		return bolestService.addBolest(bolest);
	}
	
	@PatchMapping(value="/azurirana-bolest")
	@Operation(summary= "Operacija za azuriranje bolesti")
	public ResponseEntity<Object> updateBolest(
			@Validated @RequestBody BolestDto bolestDto,
			@RequestParam Long id){
		return bolestService.updateBolest(bolestDto,id);
	}
	
	@DeleteMapping(value = "/")
	@Operation(summary= "Operacija za brisanje bolesti")
	public ResponseEntity<Object> obrisiBolest(@RequestParam Long id){
		return bolestService.deleteBolestByName(id);
	}
}
