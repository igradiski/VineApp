package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface SredstvoService {

	ResponseEntity<Object> addSredstvo(SredstvoDto sredstvo);

	Page<SredstvoDto> getAllSredstvaPagable(Pageable pageable);

	Page<SredstvoDto> findSredstvoByNamePaged(Pageable pageable, String name);

	ResponseEntity<Object> updateSredstvo(SredstvoDto sredstvoDto);

	ResponseEntity<Object> deleteSredstvoById(Long id);

    ResponseEntity<Set<AntDCascaderDto>> getSredstvaForCascader();

    Optional<SredstvoDto> findSredstvoByName(String name);

    Optional<SredstvoDto> getSredstvoForCard(Long id);

    Optional<Object> getUtrosak(Integer voda, Long id);
}
