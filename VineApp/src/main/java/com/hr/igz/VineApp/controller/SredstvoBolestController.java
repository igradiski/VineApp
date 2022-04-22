package com.hr.igz.VineApp.controller;


import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import com.hr.igz.VineApp.service.SredstvoBolestService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bolest-sredstvo")
public class SredstvoBolestController {

    private final SredstvoBolestService sredstvoBolestService;
    private Logger log = LoggerFactory.getLogger(SredstvoBolestController.class);

    public SredstvoBolestController(SredstvoBolestService sredstvoBolestService) {
        this.sredstvoBolestService = sredstvoBolestService;
    }

    @PostMapping(value="/{bolest}/{sredstvo}")
    @Operation(summary = "Unos veze sredstva i bolesti")
    public ResponseEntity<Object> insertBolestSredstvo(
            @PathVariable Long bolest,
            @PathVariable Long sredstvo){
        return sredstvoBolestService.insertBolestSredstvo(bolest,sredstvo);
    }

    @GetMapping(value ="/sve-bolesti-sredstva")
    @Operation(summary = "Dohvacanje page za bolest i sredstva")
    public Page<BolestSredstvoDto> getSredstvoBolestPage(Pageable pageable){
        return sredstvoBolestService.getSredstvoBolestPage(pageable);
    }

    @GetMapping(value ="/sve-bolesti-sredstva-filter/{bolest}/{sredstvo}")
    @Operation(summary = "Dohvacanje page za bolest i sredstva sa filterom")
    public Page<BolestSredstvoDto> getSredstvoBolestPage(Pageable pageable,
            @PathVariable Long bolest,
            @PathVariable Long sredstvo){
        return sredstvoBolestService.getSredstvoBolestPageFiltered(pageable,bolest,sredstvo);
    }

}
