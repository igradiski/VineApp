package com.hr.igz.VineApp.services;

import java.util.Map;
import java.util.Set;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import org.springframework.http.ResponseEntity;

import com.hr.igz.VineApp.domain.dto.FenofazaDto;

public interface FenofazaService {

	ResponseEntity<Object> addFenofaza(FenofazaDto fenofaza);

	ResponseEntity<Map<String, Object>> getFenofazePaged(int pageSize, int pageNo, String[] sort);

	ResponseEntity<Object> updateFenofaza(FenofazaDto fenofaza, Long id);

	ResponseEntity<Map<String, Object>> findFenofazaByNamePaged(int pageSize, int pageNo, String[] sort, String name);

	ResponseEntity<Object> deleteFenofazaById(Long id);

    ResponseEntity<Set<AntDCascaderDto>> getFenofazeZaCascader();
}
