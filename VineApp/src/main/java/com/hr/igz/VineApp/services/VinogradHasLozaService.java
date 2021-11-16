package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.VinogradHasLozaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface VinogradHasLozaService {

    ResponseEntity<Object> dodajVinogradHasLoza(VinogradHasLozaDto vinogradHasLozaDto);

    Page<VinogradHasLozaDto> dohvatiVinogradHasLoza(int pageSize, int pageNo, String[] sort, Long vinogradId);

    ResponseEntity<Object> deleteVinogradHasLozaById(Long id);

    ResponseEntity<Object> updateVinogradHasLoza(VinogradHasLozaDto vinogradHasLozaDto, Long id);
}
