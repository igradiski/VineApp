package com.hr.igz.VineApp.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.hr.igz.VineApp.domain.dto.BolestDto;

public interface BolestService {

	ResponseEntity<Object> addBolest(BolestDto bolest);

	ResponseEntity<Map<String, Object>> getBolestiPaged(int pageSize, int pageNo, String[] sort);

	ResponseEntity<Object> deleteBolestByName(Long id);

	ResponseEntity<Object> updateBolest(BolestDto bolestDto, Long id);

	ResponseEntity<Map<String, Object>> findBolestByNamePaged(int pageSize, int pageNo, String[] sort, String name);

}
