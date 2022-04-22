package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.service.TipSredstvaService;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.TipSredstvaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;


@Service
public class TipSredstvaServiceImpl implements TipSredstvaService {
	
	private final TipSredstvaRepository tipSredstvaRepository;
	private final TipSredstvaMapper mapper;

	private Logger log = LoggerFactory.getLogger(TipSredstvaServiceImpl.class);

	public TipSredstvaServiceImpl(TipSredstvaRepository tipSredstvaRepository, TipSredstvaMapper mapper) {
		this.tipSredstvaRepository = tipSredstvaRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public ResponseEntity<Object> dodajTipSredstva(TipSredstvaDto tipSredstva) {
		
		if(tipSredstvaRepository.existsByName(tipSredstva.name())) {
			log.error("Postoji tip sredstva s imenom: {}",tipSredstva.name());
			throw new ObjectAlreadyExists("Tip sredstva vec postoji u bazi!");
		}
		TipZastitnogSredstva tip = mapper.toEntity(tipSredstva);
		try {
			tipSredstvaRepository.save(tip);
		}catch (Exception e) {
			log.error("Nije moguce unijeti tip sredstva{}",tipSredstva);
			throw new PostFailureException("Nije moguce unijeti tip sredstva!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Tip sredstva je uspješno kreiran");
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TipSredstvaDto> findAllPagable(Pageable pageable) {
		return tipSredstvaRepository.findAll(pageable).map(mapper::toDto);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<Set<Object>> findAll() {
		
		ArrayList<TipZastitnogSredstva> tipovi = new ArrayList<>();
		Set<Object> tipoviSredstva = new HashSet<>();
		
		tipSredstvaRepository.findAll().forEach(tipovi::add);
		tipovi.stream().forEach(tip ->{
			tipoviSredstva.add(mapper.AntTipSredstvaToAntDCascaderDto(tip));
		});
		return new ResponseEntity<>(tipoviSredstva, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public TipZastitnogSredstva findById(Long id) {
		return tipSredstvaRepository.findById(id).get();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TipSredstvaDto> findTipSredstvaByName(Pageable pageable, String name) {
		return tipSredstvaRepository.findByNameContaining(name,pageable).map(mapper::toDto);

	}

	@Override
	@Transactional
	public ResponseEntity<Object> updateTipSredstva(TipSredstvaDto tipSredstva) {
		
		TipZastitnogSredstva oldTip = tipSredstvaRepository.findById(tipSredstva.id())
				.orElseThrow(()->{
					log.error("Nije moguce pronaći tip sredstva: {}",tipSredstva.id());
					throw new PostFailureException("Nije moguce pronaći željeni tip sredstva!");
				});
		oldTip = mapper.UpdateTipZastitnogSredstvaFromDto(oldTip,tipSredstva);
		try {
			tipSredstvaRepository.save(oldTip);
		}catch (Exception e) {
			log.error("Nije moguce ažurirati fenofazu{}",tipSredstva.toString());
			throw new PostFailureException("Nije moguce ažurirati zeljenu fenofazu!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Fenofaza je uspješno ažurirana");
	}

	@Override
	@Transactional
	public ResponseEntity<Object> deleteTipSredstvaById(Long id) {
		
		TipZastitnogSredstva tipSredstva = tipSredstvaRepository.findById(id).orElseThrow(()->{
			
			log.error("Nije moguce pronaći tip sredstva  s id: {}",id.toString());
			throw new PostFailureException("Nije moguce pronaći željeni tip!");
		});
		try {
			tipSredstvaRepository.delete(tipSredstva);
			return ResponseEntity.status(HttpStatus.OK).body("Tip uspjesno obrisan");
		} catch (Exception e) {
			log.error("Nije moguce obrisati tip: {}",tipSredstva.toString());
			throw new DeleteFailureException("Ne postoji objekt za brisanje!");
		}
	}
	
	private Map<String, Object> createResponse(Page<TipZastitnogSredstva> page){
		
		Map<String, Object> response = new HashMap<>();
		if(page != null) {
			response.put("tipoviSredstava",mapAllTipSredstva(page.getContent()));
			response.put("totalPages", page.getTotalPages());
			response.put("totalItems", page.getTotalElements());
			response.put("currentPage", page.getNumber());
		}
		return response;
	}
	
	private Set<TipSredstvaDto> mapAllTipSredstva(List<TipZastitnogSredstva> list){
		
		Set<TipSredstvaDto> set = new HashSet<TipSredstvaDto>();
		if(!list.isEmpty()) {
			list.stream().map(mapper::toDto);
		}
		return set;
	}
	

}
