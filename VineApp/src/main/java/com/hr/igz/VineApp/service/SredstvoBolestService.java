package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SredstvoBolestService {

    ResponseEntity<Object> insertBolestSredstvo(Long bolestId, Long sredstvoId);

    Page<BolestSredstvoDto> getSredstvoBolestPage(Pageable pageable);

    Page<BolestSredstvoDto> getSredstvoBolestPageFiltered(Pageable pageable, Long bolestId, Long sredstvoId);
}
