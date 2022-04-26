package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

public interface TipSredstvaService {

	TipSredstvaDto dodajTipSredstva(TipSredstvaDto tipSredstva);

	Page<TipSredstvaDto> findAllPagable(Pageable pageable);

	List<AntDCascaderDto> findAll();

	TipSredstvaDto findById(Long id);

	Page<TipSredstvaDto> findTipSredstvaByName(Pageable pageable, String name);

	TipSredstvaDto updateTipSredstva(TipSredstvaDto tipSredstva);

	ResponseEntity<Object> deleteTipSredstvaById(Long id);

}
