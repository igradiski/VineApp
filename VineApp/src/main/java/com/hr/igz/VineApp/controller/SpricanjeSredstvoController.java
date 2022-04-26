package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.SpricanjeOmjerDto;
import com.hr.igz.VineApp.domain.dto.SpricanjeSredstvoDto;
import com.hr.igz.VineApp.service.SpricanjeSredstvoService;
import io.swagger.models.Response;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/spricanje-sredstvo")
public class SpricanjeSredstvoController {

    private final SpricanjeSredstvoService spricanjeSredstvoService;
    private Logger log = LoggerFactory.getLogger(SpricanjeSredstvoController.class);

    public SpricanjeSredstvoController(SpricanjeSredstvoService spricanjeSredstvoService) {
        this.spricanjeSredstvoService = spricanjeSredstvoService;
    }

    @PostMapping
    @Operation(summary = "Operacija za dodavanje sredstva u spricanje")
    public ResponseEntity<Object> addSredstvoHasSpricanje(@Validated @RequestBody SpricanjeSredstvoDto spricanjeSredstvoDto){
        return ResponseEntity.status(HttpStatus.CREATED).body(spricanjeSredstvoService.addSredstvoHasSpricanje(spricanjeSredstvoDto));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Opracija za dohvacanje sredstva u spricanju")
    public Page<SpricanjeSredstvoDto> getSredstvaPaged(Pageable pageable, @PathVariable Long id){
        return spricanjeSredstvoService.getSredstvaPaged(pageable,id);
    }

    @GetMapping("/omjeri/{sredstvo}/{spricanje}")
    @Operation(summary = "Metoda za dohvacanje omjera kod spricanja")
    public Optional<SpricanjeOmjerDto> getOmjeri(@PathVariable Long sredstvo, @PathVariable Long spricanje){
        return spricanjeSredstvoService.getOmjeri(sredstvo,spricanje);
    }

    @PutMapping
    @Operation(summary = "Opracija za azuriranje sredstvo has spricanje")
    public ResponseEntity<Object> updateSredstvoById(
            @Validated @RequestBody SpricanjeSredstvoDto spricanjeSredstvoDto){
        return ResponseEntity.status(HttpStatus.OK).body(spricanjeSredstvoService.updateSredstvoById(spricanjeSredstvoDto));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Operacija za brisanje sredstva iz špricanja")
    public ResponseEntity<Object> deleteSredstvoById(@PathVariable Long id){
        return spricanjeSredstvoService.deleteById(id);
    }
}
