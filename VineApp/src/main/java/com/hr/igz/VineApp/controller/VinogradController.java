package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.VinogradDto;
import com.hr.igz.VineApp.service.VinogradService;
import io.swagger.v3.oas.annotations.Operation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/vinogradi")
public class VinogradController {

    private final VinogradService vinogradService;
    private Logger log = LoggerFactory.getLogger(VinogradController.class);

    public VinogradController(VinogradService vinogradService) {
        this.vinogradService = vinogradService;
    }

    @PostMapping
    @Operation(summary = "Unos novog vinograda")
    public ResponseEntity<Object> insertVinograd (@Validated @RequestBody VinogradDto vinogradDto){
        return vinogradService.insertVinograd(vinogradDto);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_ADMIN')")
    @GetMapping(value ="/vinogradi")
    @Operation(summary = "Dohvacanje korisnikovih vinograda")
    public Page<VinogradDto> getVinogradi(Pageable pageable){
        return vinogradService.getVinogradi(pageable);
    }

    @DeleteMapping(value = "/{id}")
    @Operation(summary = "Operacija za brisanje vinograda")
    public ResponseEntity<Object> deleteVinogradById(@PathVariable Long id){
        return vinogradService.deleteVinogradById(id);
    }

}
