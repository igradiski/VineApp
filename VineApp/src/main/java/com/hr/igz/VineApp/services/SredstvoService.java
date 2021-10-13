package com.hr.igz.VineApp.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.hr.igz.VineApp.domain.dto.SredstvoDto;

public interface SredstvoService {

	ResponseEntity<Object> addSredstvo(SredstvoDto sredstvo);

	ResponseEntity<Map<String, Object>> getAllSredstvaPagable(int pageNo, int pageSize, String[] sort);
	

}
