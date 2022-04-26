package com.hr.igz.VineApp.controller;


import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.VinovaLozaDto;
import com.hr.igz.VineApp.service.VinovaLozaService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/vinova-loza")
public class VinovaLozaController {

    private final VinovaLozaService vinovaLozaService;
    private Logger log = LoggerFactory.getLogger(VinovaLozaController.class);

    public VinovaLozaController(VinovaLozaService vinovaLozaService) {
        this.vinovaLozaService = vinovaLozaService;
    }

    @PostMapping
    @Operation(summary = "Operacija za unos vinove loze")
    public ResponseEntity<Object> dodajLozu(@Validated @RequestBody VinovaLozaDto vinovaLozaDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(vinovaLozaService.dodajLozu(vinovaLozaDto));
    }

    @GetMapping
    @Operation(summary = "Operacija koja dohvaca vinove loze")
    public Page<VinovaLozaDto> getVinovaLozaPaged(Pageable pageable){
        return vinovaLozaService.getVinovaLozaPaged(pageable);
    }

    @GetMapping(value = "/loze-cascader")
    @Operation(summary = "Dohvaca sve loze za cascader menu")
    public Set<AntDCascaderDto> getVinovaLozaCascader(){
        return vinovaLozaService.getVinovaLozaCascader();
    }

    @GetMapping(value = "/loze-card/{id}")
    @Operation(summary = "Operacija koja dohvaca podatke o vinovoj lozi za karticni prikaz")
    public Optional<VinovaLozaDto> getLozaForCard(@PathVariable Long id){
        return vinovaLozaService.getLozaForCard(id);
    }

    @PutMapping
    @Operation(summary = "Ažuriranje vinove loze")
    public ResponseEntity<Object> updateLoza(@Valid @RequestBody VinovaLozaDto vinovaLozaDto){
        return ResponseEntity.status(HttpStatus.OK).body(vinovaLozaService.updateLoza(vinovaLozaDto));
    }

    @DeleteMapping(value ="/{id}")
    @Operation(description = "Operacija za brisanje vinove loze")
    public ResponseEntity<Object> deleteLozaById(@PathVariable Long id){
        return vinovaLozaService.deleteLozaById(id);
    }

}
