package com.hr.igz.VineApp.controller;


import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import com.hr.igz.VineApp.services.SredstvoBolestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bolest-sredstvo")
@Slf4j
@RequiredArgsConstructor
public class SredstvoBolestController {

    private final SredstvoBolestService sredstvoBolestService;

    @PutMapping(value="/bolesti-sredstva")
    @Operation(summary = "Unos veze sredstva i bolesti")
    public ResponseEntity<Object> insertBolestSredstvo(
            @RequestParam Long bolestId,
            @RequestParam Long sredstvoId){
        return sredstvoBolestService.insertBolestSredstvo(bolestId,sredstvoId);
    }

    @GetMapping(value ="/sve-bolesti-sredstva")
    @Operation(summary = "Dohvacanje page za bolest i sredstva")
    public Page<BolestSredstvoDto> getSredstvoBolestPage(
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "id,desc") String [] sort){
        return sredstvoBolestService.getSredstvoBolestPage(pageSize,pageNo,sort);
    }

    @GetMapping(value ="/sve-bolesti-sredstva-filter")
    @Operation(summary = "Dohvacanje page za bolest i sredstva sa filterom")
    public Page<BolestSredstvoDto> getSredstvoBolestPage(
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "id,desc") String [] sort,
            @RequestParam(required = false) Long bolestId,
            @RequestParam(name ="sredstvoFaza",required = false ) Long sredstvoId){
        return sredstvoBolestService.getSredstvoBolestPageFiltered(pageSize,pageNo,sort,bolestId,sredstvoId);
    }

}
