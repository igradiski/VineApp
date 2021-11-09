package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.VinogradHasLozaDto;
import com.hr.igz.VineApp.services.VinogradHasLozaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/vinograd-loza")
@Slf4j
@RequiredArgsConstructor
public class VinogradHasLozaController {

    private final VinogradHasLozaService vinogradHasLozaService;

    @PostMapping(value = "/vingoradHasLoza")
    @Operation(summary = "Dodavanje nove veze loze i vinograda")
    public ResponseEntity<Object> dodajVinogradHasLoza(@Validated @RequestBody VinogradHasLozaDto vinogradHasLozaDto){
        return vinogradHasLozaService.dodajVinogradHasLoza(vinogradHasLozaDto);
    }

    @GetMapping(value ="/loze")
    @Operation(description = "Dohvacanje loza za pripadajuci vinograd")
    public Page<VinogradHasLozaDto> dohvatiVinogradHasLoza(
    @RequestParam(defaultValue = "2") int pageSize,
    @RequestParam(defaultValue = "0") int pageNo,
    @RequestParam(defaultValue = "id,desc") String [] sort,
    @RequestParam Long vinogradId){
        return vinogradHasLozaService.dohvatiVinogradHasLoza(pageSize,pageNo,sort,vinogradId);
    }

}
