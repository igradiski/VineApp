package com.hr.igz.VineApp.controller;


import com.hr.igz.VineApp.domain.dto.SpricanjeDto;
import com.hr.igz.VineApp.services.SpricanjaService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/spricanja")
@Slf4j
@RequiredArgsConstructor
public class SpricanjaController {

    private final SpricanjaService spricanjaService;

    @PostMapping("/spricanje")
    @Operation(description = "Unos novog spricanja za korisnika")
    public ResponseEntity<Object> insertSpricanje(@Validated @RequestBody SpricanjeDto spricanjeDto){
        return spricanjaService.insertSpricanje(spricanjeDto);
    }

    @GetMapping("/spricanja")
    @Operation(summary = "Operacija za dohvacanje spricanja sa stranicenjem")
    public Page<SpricanjeDto> getSpricanjaPaged(
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "id,desc") String [] sort){
        return spricanjaService.getSpricanjePaged(pageSize,pageNo,sort);
    }


    @DeleteMapping("/spricanje")
    @Operation(description = "Operacija za brisanje spricanja")
    public ResponseEntity<Object> deleteSpricanjeById(@RequestParam Long id){
        return spricanjaService.deleteSpricanjeById(id);
    }

    @PatchMapping(value ="/spricanje")
    @Operation(summary = "Operacija za azuriranje podataka o spricanju")
    public ResponseEntity<Object> updateSpricanje (
            @Validated @RequestBody SpricanjeDto spricanjeDto,
            @RequestParam Long id){
        return spricanjaService.updateSpricanje(spricanjeDto,id);
    }
}
