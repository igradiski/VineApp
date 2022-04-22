package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.VinogradDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface VinogradService {

    ResponseEntity<Object> insertVinograd(VinogradDto vinogradDto);

    Page<VinogradDto> getVinogradi(Pageable pageable);

    ResponseEntity<Object> deleteVinogradById(Long id);
}
