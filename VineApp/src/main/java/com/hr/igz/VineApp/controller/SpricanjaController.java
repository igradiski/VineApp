package com.hr.igz.VineApp.controller;


import com.hr.igz.VineApp.domain.dto.SpricanjeDto;
import com.hr.igz.VineApp.service.SpricanjaService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spricanja")
public class SpricanjaController {

    private final SpricanjaService spricanjaService;
    private Logger log = LoggerFactory.getLogger(SpricanjaController.class);

    public SpricanjaController(SpricanjaService spricanjaService) {
        this.spricanjaService = spricanjaService;
    }

    @PostMapping("/spricanje")
    @Operation(description = "Unos novog spricanja za korisnika")
    public ResponseEntity<Object> insertSpricanje(@Validated @RequestBody SpricanjeDto spricanjeDto){
        return spricanjaService.insertSpricanje(spricanjeDto);
    }

    @GetMapping("/spricanja")
    @Operation(summary = "Operacija za dohvacanje spricanja sa stranicenjem")
    public Page<SpricanjeDto> getSpricanjaPaged(Pageable pageable){
        return spricanjaService.getSpricanjePaged(pageable);
    }


    @DeleteMapping("/{id}")
    @Operation(description = "Operacija za brisanje spricanja")
    public ResponseEntity<Object> deleteSpricanjeById(@PathVariable Long id){
        return spricanjaService.deleteSpricanjeById(id);
    }

    @PutMapping
    @Operation(summary = "Operacija za azuriranje podataka o spricanju")
    public ResponseEntity<Object> updateSpricanje (@Validated @RequestBody SpricanjeDto spricanjeDto){
        return spricanjaService.updateSpricanje(spricanjeDto);
    }
}
