package com.hr.igz.VineApp.services;

import java.util.Map;
import java.util.Set;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import org.springframework.http.ResponseEntity;

import com.hr.igz.VineApp.domain.dto.SredstvoDto;

public interface SredstvoService {

	ResponseEntity<Object> addSredstvo(SredstvoDto sredstvo);

	ResponseEntity<Map<String, Object>> getAllSredstvaPagable(int pageNo, int pageSize, String[] sort);

    ResponseEntity<Map<String, Object>> findSredstvoByNamePaged(int pageSize, int pageNo, String[] sort, String name);

	ResponseEntity<Object> updateSredstvo(SredstvoDto sredstvoDto, Long id);

	ResponseEntity<Object> deleteSredstvoById(Long id);

    ResponseEntity<Set<AntDCascaderDto>> getSredstvaForCascader();
}
