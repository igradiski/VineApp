package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.BolestFazaDto;
import com.hr.igz.VineApp.service.FazaBolestService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bolest-faza")

public class FazaBolestController {

    private final FazaBolestService fazaBolestService;
    private Logger log = LoggerFactory.getLogger(FazaBolestController.class);

    public FazaBolestController(FazaBolestService fazaBolestService) {
        this.fazaBolestService = fazaBolestService;
    }

    @PostMapping(value="/{bolest}/{faza}")
    @Operation(summary = "Unos veze faza i bolesti")
    public ResponseEntity<Object> insertBolestFaza(
            @PathVariable Long bolest,
            @PathVariable Long faza){
        return ResponseEntity.status(HttpStatus.CREATED).body(fazaBolestService.insertFazaBolest(bolest,faza));
    }

    @GetMapping(value="/sve-bolesti-faze")
    @Operation(summary = "Unos veze faza i bolesti")
    public Page<BolestFazaDto> getBolestFazePaged(Pageable pageable){
        return fazaBolestService.getBolestFazePaged(pageable);
    }

    @GetMapping(value ="/sve-bolesti-faze-filter/{bolest}/{faza}")
    @Operation(summary = "Dohvacanje pagea za bolest i faze sa filterom")
    public Page<BolestFazaDto> getFazaBolestPage(
            Pageable pageable,
            @PathVariable Long bolest,
            @PathVariable Long faza){
        return fazaBolestService.getSredstvoBolestPageFiltered(pageable,bolest,faza);
    }

}
