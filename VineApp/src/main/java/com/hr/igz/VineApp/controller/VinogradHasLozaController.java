package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.VinogradHasLozaDto;
import com.hr.igz.VineApp.service.VinogradHasLozaService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vinograd-loza")
public class VinogradHasLozaController {

    private final VinogradHasLozaService vinogradHasLozaService;
    private Logger log = LoggerFactory.getLogger(VinogradHasLozaController.class);

    public VinogradHasLozaController(VinogradHasLozaService vinogradHasLozaService) {
        this.vinogradHasLozaService = vinogradHasLozaService;
    }

    @PostMapping
    @Operation(summary = "Dodavanje nove veze loze i vinograda")
    public ResponseEntity<Object> dodajVinogradHasLoza(@Validated @RequestBody VinogradHasLozaDto vinogradHasLozaDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(vinogradHasLozaService.dodajVinogradHasLoza(vinogradHasLozaDto));
    }

    @GetMapping(value = "/{id}")
    @Operation(description = "Dohvacanje loza za pripadajuci vinograd")
    public Page<VinogradHasLozaDto> dohvatiVinogradHasLoza(Pageable pageable, @PathVariable Long id) {
        return vinogradHasLozaService.dohvatiVinogradHasLoza(pageable, id);
    }

    @PutMapping
    @Operation(description = "Azuriranje podatka o broju cokota")
    public ResponseEntity<Object> updateVinogradHasLoza(@Validated @RequestBody VinogradHasLozaDto vinogradHasLozaDto) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(vinogradHasLozaService.updateVinogradHasLoza(vinogradHasLozaDto));
    }

    @DeleteMapping(value = "/{id}")
    @Operation(description = "Operacija za brisanja zapisa iz tablice koja povezuje vingorad i loze")
    public ResponseEntity<Object> deleteVinogradHasLozaById(@PathVariable Long id) {
        return vinogradHasLozaService.deleteVinogradHasLozaById(id);
    }

}
