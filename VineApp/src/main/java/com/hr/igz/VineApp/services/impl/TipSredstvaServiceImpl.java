package com.hr.igz.VineApp.services.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.mapper.TipSredstvaMapper;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.services.TipSredstvaService;


@Service
public class TipSredstvaServiceImpl implements TipSredstvaService {
	
	private TipSredstvaRepository tipSredstvaRepository;
	
	private final TipSredstvaMapper mapper;
	
	@Autowired
	public TipSredstvaServiceImpl(TipSredstvaRepository tipSredstvaRepository,TipSredstvaMapper mapper) {
		this.tipSredstvaRepository= tipSredstvaRepository;
		this.mapper= mapper;
	}

	@Override
	public ResponseEntity<Object> dodajTipSredstva(TipSredstvaDto tipSredstva) {
		if(tipSredstvaRepository.existsByName(tipSredstva.getName())) {
			throw new ObjectAlreadyExists("Tip sredstva vec postoji u bazi!");
		}
		TipZastitnogSredstva tip = new TipZastitnogSredstva();
		tip.setName(tipSredstva.getName());
		tip.setDate(Instant.now());
		tipSredstvaRepository.save(tip);
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Tip sredstva je uspješno kreiran");
	}

	@Override
	public ResponseEntity<Map<String, Object>> findAllPagable(int page, int size) {
		
		Pageable paging = PageRequest.of(page, size);
		Page<TipZastitnogSredstva> pageTipovi = tipSredstvaRepository.findAll(paging);
		Map<String, Object> response = new HashMap<>();
		response.put("tipovi", mapAllTipovi(pageTipovi.getContent()));
		response.put("totalPages", pageTipovi.getTotalPages());
		response.put("totalItems", pageTipovi.getTotalElements());
		response.put("currentPage", pageTipovi.getNumber());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<Set<Object>> findAll() {
		
		ArrayList<TipZastitnogSredstva> tipovi = new ArrayList<>();
		Set<Object> tipoviSredstva = new HashSet<Object>();
		
		tipSredstvaRepository.findAll().forEach(tipovi::add);
		tipovi.stream().forEach(tip ->{
			tipoviSredstva.add(mapper.AntTipSredstvaToAntDCascaderDto(tip));
		});
		return new ResponseEntity<>(tipoviSredstva, HttpStatus.OK);
	}

	@Override
	public TipZastitnogSredstva findById(Long id) {
		return tipSredstvaRepository.findById(id).get();
	}
	
	private Set<TipSredstvaDto> mapAllTipovi(List<TipZastitnogSredstva> list) {
		
		Set<TipSredstvaDto> set = new HashSet<TipSredstvaDto>();
		list.stream().forEach(tip ->{
			set.add(mapper.TipSredstvaToTipSredstvaDto(tip));
		});
		return set;
	}

}
