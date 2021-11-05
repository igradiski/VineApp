package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.VinogradDto;
import com.hr.igz.VineApp.services.VinogradService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
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
    @GetMapping(value ="/vinogradi")
    @Operation(summary = "Dohvacanje korisnikovih vinograda")
    public Page<VinogradDto> getVinogradi(
            @RequestParam(defaultValue = "2") int pageSize,
            @RequestParam(defaultValue = "0") int pageNo,
            @RequestParam(defaultValue = "id,desc") String [] sort){
        return vinogradService.getVinogradi(pageSize,pageNo,sort);
    }


}
