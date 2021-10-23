package com.hr.igz.VineApp.services;

import java.util.Map;
import java.util.Set;

import org.springframework.http.ResponseEntity;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;

public interface TipSredstvaService {

	ResponseEntity<Object> dodajTipSredstva(TipSredstvaDto tipSredstva);

	ResponseEntity<Map<String, Object>> findAllPagable(int page, int size, String[] sort);

	ResponseEntity<Set<Object>> findAll();
	
	TipZastitnogSredstva findById(Long id);

	ResponseEntity<Map<String, Object>> findTipSredstvaByName(int pageSize, int pageNo, String[] sort, String name);

	ResponseEntity<Object> updateTipSredstva(TipSredstvaDto tipSredstva, Long id);

	ResponseEntity<Object> deleteTipSredstvaById(Long id);

}
