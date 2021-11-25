package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.VinogradDto;
import com.hr.igz.VineApp.services.VinogradService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping("/vinogradi")
@Slf4j
@RequiredArgsConstructor
public class VinogradController {

    private final VinogradService vinogradService;

    @PostMapping(value ="/vinograd")
    @Operation(summary = "Unos novog vinograda")
    public ResponseEntity<Object> insertVinograd (
            @Validated @RequestBody VinogradDto vinogradDto){
        return vinogradService.insertVinograd(vinogradDto);
    }

    //@PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_USER','ROLE_ADMIN')")
    @GetMapping(value ="/vinogradi")
    @Operation(summary = "Dohvacanje korisnikovih vinograda")
    public Page<VinogradDto> getVinogradi(
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "id,desc") String [] sort){
        return vinogradService.getVinogradi(pageSize,pageNo,sort);
    }

    @DeleteMapping(value = "/vinograd")
    @Operation(summary = "Operacija za brisanje vinograda")
    public ResponseEntity<Object> deleteVinogradById(@RequestParam Long id){
        return vinogradService.deleteVinogradById(id);
    }

}
