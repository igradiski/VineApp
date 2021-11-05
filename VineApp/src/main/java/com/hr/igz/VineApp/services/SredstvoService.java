package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface SredstvoService {

	ResponseEntity<Object> addSredstvo(SredstvoDto sredstvo);

	Page<SredstvoDto> getAllSredstvaPagable(int pageNo, int pageSize, String[] sort);

	Page<SredstvoDto> findSredstvoByNamePaged(int pageSize, int pageNo, String[] sort, String name);

	ResponseEntity<Object> updateSredstvo(SredstvoDto sredstvoDto, Long id);

	ResponseEntity<Object> deleteSredstvoById(Long id);

    ResponseEntity<Set<AntDCascaderDto>> getSredstvaForCascader();

    Optional<SredstvoDto> findSredstvoByName(String name);

    Optional<SredstvoDto> getSredstvoForCard(Long id);
}
