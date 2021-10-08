package com.hr.igz.VineApp.services;

import java.util.Map;

import org.springframework.http.ResponseEntity;

import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;

public interface TipSredstvaService {

	ResponseEntity<Object> dodajTipSredstva(TipSredstvaDto tipSredstva);

	ResponseEntity<Map<String, Object>> findAllPagable(int page, int size);

}
