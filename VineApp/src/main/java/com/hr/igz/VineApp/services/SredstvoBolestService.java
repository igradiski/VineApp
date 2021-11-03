package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface SredstvoBolestService {

    ResponseEntity<Object> insertBolestSredstvo(Long bolestId, Long sredstvoId);

    Page<BolestSredstvoDto> getSredstvoBolestPage(int pageSize, int pageNo, String[] sort);

    Page<BolestSredstvoDto> getSredstvoBolestPageFiltered(int pageSize, int pageNo, String[] sort, Long bolestId, Long sredstvoId);
}
