package com.hr.igz.VineApp.controller;


import com.hr.igz.VineApp.services.SredstvoBolestService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/bolest-sredstvo")
@Slf4j
@RequiredArgsConstructor
public class SredstvoBolestController {

    private final SredstvoBolestService sredstvoBolestService;

    @PostMapping(value="/bolesti-sredstva")
    @Operation(summary = "Unos veze sredstva i bolesti")
    public ResponseEntity<Object> insertBolestSredstvo(
            @RequestParam Long bolestId,
            @RequestParam Long sredstvoId){
        return sredstvoBolestService.insertBolestSredstvo(bolestId,sredstvoId);
    }
}
