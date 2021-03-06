package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.VinogradHasLozaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface VinogradHasLozaService {

    VinogradHasLozaDto dodajVinogradHasLoza(VinogradHasLozaDto vinogradHasLozaDto);

    Page<VinogradHasLozaDto> dohvatiVinogradHasLoza(Pageable pageable, Long vinogradId);

    ResponseEntity<Object> deleteVinogradHasLozaById(Long id);

    VinogradHasLozaDto updateVinogradHasLoza(VinogradHasLozaDto vinogradHasLozaDto);
}
