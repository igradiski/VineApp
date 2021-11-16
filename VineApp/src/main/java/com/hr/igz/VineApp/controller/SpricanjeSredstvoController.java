package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.SpricanjeOmjerDto;
import com.hr.igz.VineApp.domain.dto.SpricanjeSredstvoDto;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.repository.SpricanjaRepository;
import com.hr.igz.VineApp.services.SpricanjeSredstvoService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/spricanje-sredstvo")
@Slf4j
@RequiredArgsConstructor
public class SpricanjeSredstvoController {

    private final SpricanjeSredstvoService spricanjeSredstvoService;

    @PostMapping("/sredstvo")
    @Operation(summary = "Operacija za dodavanje sredstva u spricanje")
    public ResponseEntity<Object> addSredstvoHasSpricanje(@Validated @RequestBody SpricanjeSredstvoDto spricanjeSredstvoDto){
        return spricanjeSredstvoService.addSredstvoHasSpricanje(spricanjeSredstvoDto);
    }

    @GetMapping("/sredstvo")
    @Operation(summary = "Opracija za dohvacanje sredstva u spricanju")
    public Page<SpricanjeSredstvoDto> getSredstvaPaged(
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "id,desc") String [] sort,
            @RequestParam Long id){
        return spricanjeSredstvoService.getSredstvaPaged(pageSize,pageNo,sort,id);
    }

    @GetMapping("/omjeri")
    @Operation(summary = "Metoda za dohvacanje omjera kod spricanja")
    public Optional<SpricanjeOmjerDto> getOmjeri(
            @RequestParam Long sredstvoId,
            @RequestParam Long spricanjeId){
        return spricanjeSredstvoService.getOmjeri(sredstvoId,spricanjeId);
    }

}
