package com.hr.igz.VineApp.controller;


import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.VinovaLozaDto;
import com.hr.igz.VineApp.services.VinovaLozaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/vinova-loza")
@Slf4j
@RequiredArgsConstructor
public class VinovaLozaController {

    private final VinovaLozaService vinovaLozaService;

    @PostMapping(value = "/loza")
    @Operation(summary = "Operacija za unos vinove loze")
    public ResponseEntity<Object> dodajLozu(@Validated @RequestBody VinovaLozaDto vinovaLozaDto){
        return vinovaLozaService.dodajLozu(vinovaLozaDto);
    }

    @GetMapping(value = "/loze")
    @Operation(summary = "Operacija koja dohvaca vinove loze")
    public Page<VinovaLozaDto> getVinovaLozaPaged(
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "id,desc") String [] sort){
        return vinovaLozaService.getVinovaLozaPaged(pageSize,pageNo,sort);
    }

    @GetMapping(value = "/loze-cascader")
    @Operation(summary = "Dohvaca sve loze za cascader menu")
    public Set<AntDCascaderDto> getVinovaLozaCascader(){
        return vinovaLozaService.getVinovaLozaCascader();
    }

    @GetMapping(value = "/loze-card")
    @Operation(summary = "Operacija koja dohvaca podatke o vinovoj lozi za karticni prikaz")
    public Optional<VinovaLozaDto> getLozaForCard(@RequestParam Long id){
        return vinovaLozaService.getLozaForCard(id);
    }

    @DeleteMapping(value ="/loza")
    @Operation(description = "Operacija za brisanje vinove loze")
    public ResponseEntity<Object> deleteLozaById(@RequestParam Long id){
        return vinovaLozaService.deleteLozaById(id);
    }

    @PatchMapping(value = "/loza")
    @Operation(summary = "Ažuriranje vinove loze")
    public ResponseEntity<Object> updateLoza(
            @Valid @RequestBody VinovaLozaDto vinovaLozaDto,
            @RequestParam Long id){
        return vinovaLozaService.updateLoza(vinovaLozaDto,id);
    }

}
