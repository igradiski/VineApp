package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.BolestFazaDto;
import com.hr.igz.VineApp.services.FazaBolestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bolest-faza")
@Slf4j
@RequiredArgsConstructor
public class FazaBolestController {

    private final FazaBolestService fazaBolestService;

    @PutMapping(value="/bolesti-faze")
    @Operation(summary = "Unos veze faza i bolesti")
    public ResponseEntity<Object> insertBolestFaza(
            @RequestParam Long bolestId,
            @RequestParam Long fazaId){
        return  fazaBolestService.insertFazaBolest(bolestId,fazaId);
    }

    @GetMapping(value="/sve-bolesti-faze")
    @Operation(summary = "Unos veze faza i bolesti")
    public Page<BolestFazaDto> getBolestFazePaged(
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "id,desc") String [] sort){
        return fazaBolestService.getBolestFazePaged(pageSize,pageNo,sort);
    }

    @GetMapping(value ="/sve-bolesti-faze-filter")
    @Operation(summary = "Dohvacanje page za bolest i faze sa filterom")
    public Page<BolestFazaDto> getFazaBolestPage(
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "id,desc") String [] sort,
            @RequestParam(required = false) Long bolestId,
            @RequestParam(name ="sredstvoFaza",required = false ) Long fazaId){
        return fazaBolestService.getSredstvoBolestPageFiltered(pageSize,pageNo,sort,bolestId,fazaId);
    }

}
