package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.service.TipSredstvaService;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
import com.hr.igz.VineApp.service.exception.NoSuchElementException;
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
import java.util.stream.Collectors;


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
	public TipSredstvaDto dodajTipSredstva(TipSredstvaDto tipSredstva) {
		
		if(tipSredstvaRepository.existsByName(tipSredstva.name())) {
			log.error("Postoji tip sredstva s imenom: {}",tipSredstva.name());
			throw new ObjectAlreadyExists("Tip sredstva vec postoji u bazi!");
		}
		TipZastitnogSredstva tip = mapper.toEntity(tipSredstva);
		try {
			return mapper.toDto(tipSredstvaRepository.save(tip));
		}catch (Exception e) {
			log.error("Nije moguce unijeti tip sredstva{}",tipSredstva);
			throw new PostFailureException("Nije moguce unijeti tip sredstva!");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TipSredstvaDto> findAllPagable(Pageable pageable) {
		return tipSredstvaRepository.findAll(pageable).map(mapper::toDto);
	}
	
	
	@Override
	@Transactional(readOnly = true)
	public List<AntDCascaderDto> findAll() {
		return tipSredstvaRepository
				.findAll()
				.stream()
				.map(mapper::ToAntDCascaderDto)
				.collect(Collectors.toList());
	}

	@Override
	@Transactional(readOnly = true)
	public TipSredstvaDto findById(Long id) {

		log.debug("ID: "+id);
		log.info("Fetching tip sredstva with id :"+id);
		TipZastitnogSredstva tipZastitnogSredstva = tipSredstvaRepository.findById(id).orElseThrow( () -> {
			log.error("Tip sredstva s id:"+id+" ne postoji!");
			throw new NoSuchElementException("Tip sredstva ne postoji");
		});
		return mapper.toDto(tipZastitnogSredstva);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<TipSredstvaDto> findTipSredstvaByName(Pageable pageable, String name) {

		log.debug(name);
		log.info("Fetching tip sredstva with name: "+name);
		return tipSredstvaRepository.findByNameContaining(name,pageable).map(mapper::toDto);

	}

	@Override
	@Transactional
	public TipSredstvaDto updateTipSredstva(TipSredstvaDto tipSredstva) {
		
		TipZastitnogSredstva oldTip = tipSredstvaRepository.findById(tipSredstva.id())
				.orElseThrow(()->{
					log.error("Nije moguce pronaći tip sredstva: {}",tipSredstva.id());
					throw new PostFailureException("Nije moguce pronaći željeni tip sredstva!");
				});
		oldTip = mapper.UpdateTipZastitnogSredstvaFromDto(oldTip,tipSredstva);
		try {
			return mapper.toDto(tipSredstvaRepository.save(oldTip));
		}catch (Exception e) {
			log.error("Nije moguce ažurirati fenofazu{}",tipSredstva.toString());
			throw new PostFailureException("Nije moguce ažurirati zeljenu fenofazu!");
		}
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
}
