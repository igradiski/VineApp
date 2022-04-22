package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.BolestFazaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface FazaBolestService {

    BolestFazaDto insertFazaBolest(Long bolestId, Long fazaId);

    Page<BolestFazaDto> getBolestFazePaged(Pageable pageable);

    Page<BolestFazaDto> getSredstvoBolestPageFiltered(Pageable pageable, Long bolestId, Long fazaId);
}
