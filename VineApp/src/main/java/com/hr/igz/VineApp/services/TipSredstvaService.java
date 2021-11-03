package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Set;

public interface TipSredstvaService {

	ResponseEntity<Object> dodajTipSredstva(TipSredstvaDto tipSredstva);

	Page<TipSredstvaDto> findAllPagable(int page, int size, String[] sort);

	ResponseEntity<Set<Object>> findAll();
	
	TipZastitnogSredstva findById(Long id);

	Page<TipSredstvaDto> findTipSredstvaByName(int pageSize, int pageNo, String[] sort, String name);

	ResponseEntity<Object> updateTipSredstva(TipSredstvaDto tipSredstva, Long id);

	ResponseEntity<Object> deleteTipSredstvaById(Long id);

}
