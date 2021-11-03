package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.BolestFazaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface FazaBolestService {

    ResponseEntity<Object> insertFazaBolest(Long bolestId, Long fazaId);

    Page<BolestFazaDto> getBolestFazePaged(int pageSize, int pageNo, String[] sort);

    Page<BolestFazaDto> getSredstvoBolestPageFiltered(int pageSize, int pageNo, String[] sort, Long bolestId, Long fazaId);
}
