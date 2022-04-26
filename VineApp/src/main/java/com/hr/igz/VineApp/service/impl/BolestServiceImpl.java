package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
import com.hr.igz.VineApp.service.exception.NoSuchElementException;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.BolestMapper;
import com.hr.igz.VineApp.repository.BolestRepository;
import com.hr.igz.VineApp.service.BolestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BolestServiceImpl implements BolestService {

	private final BolestRepository bolestRepository;
	private final BolestMapper mapper;

	private Logger log = LoggerFactory.getLogger(BolestServiceImpl.class);

	public BolestServiceImpl(BolestRepository bolestRepository, BolestMapper mapper) {
		this.bolestRepository = bolestRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public BolestDto addBolest(BolestDto bolestDto) {

		log.debug(bolestDto.toString());
		log.info("Inserting bolest :"+ bolestDto.toString());

		if (bolestRepository.existsByName(bolestDto.name())) {
			log.error("Postoji bolest s imenom: {}", bolestDto.name());
			throw new ObjectAlreadyExists("Bolest toga imena vec postoji!");
		}
		Bolest bolest = mapper.toEntity(bolestDto);
		bolest.setApproved(0);
		return mapper.toDto(bolestRepository.save(bolest));
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BolestDto> getBolestiPaged(Pageable pageable) {

		log.info("Getting all bolesti");
		return bolestRepository.findAll(pageable).map(mapper::toDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<BolestDto> findBolestByNamePaged(Pageable pageable, String name) {

		log.debug(name);
		log.info("Fetching bolest by name containing: "+name);
		return bolestRepository.findByNameContaining(name,pageable).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<BolestDto> getBolestForCard(Long id) {

		log.debug("Id: "+id.toString());
		log.info("Fetching bolest for card with id: "+id);
		Optional<Bolest> bolest = bolestRepository.findById(id);
		if(bolest.isEmpty())
			throw new NoSuchElementException("Element with id : "+id +"doest not exists");
		BolestDto dto = mapper.toDto(bolest.get());
		return Optional.of(dto);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<Set<AntDCascaderDto>> getBolestiZaCascader() {

		Set<AntDCascaderDto> set = bolestRepository.findAll().stream()
				.map(mapper::BolestToCascaderDto).collect(Collectors.toSet());
		return new ResponseEntity<>(set, HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<BolestDto> findBolestByName(String name) {

		log.debug(name);
		log.info("Fetching bolest with name: "+name);
		return bolestRepository.findByName(name).map(mapper::toDto);
	}

	@Override
	@Transactional
	public BolestDto updateBolest(BolestDto bolestDto) {

		log.debug(bolestDto.toString());
		Objects.requireNonNull(bolestDto.id(),"Id cant be null!");
		if (bolestRepository.existsByName(bolestDto.name())) {
			log.error("Postoji bolest s imenom: {}", bolestDto.name());
			throw new ObjectAlreadyExists("Bolest toga imena vec postoji!");
		}
		Bolest oldBolest = bolestRepository.findById(bolestDto.id())
				.orElseThrow(()->{
					log.error("Ne postoji bolest za azuriranje s ID: {}",bolestDto.id());
					throw new PostFailureException("Ne postoji bolest za azuriranje!");
				});
		oldBolest = mapper.UpdateBolestFromDto(oldBolest, bolestDto);
		try {
			return mapper.toDto(bolestRepository.save(oldBolest));
		}catch (Exception e) {
			log.error("Nije moguce ažurirati bolest: {}",oldBolest.toString());
			throw new PostFailureException("Nije moguce ažurirati zeljenu bolest!");
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Object> deleteBolestById(Long id) {

		log.debug("deleting bolest with ID: "+id);
		Bolest bolest = bolestRepository.findById(id)
				.orElseThrow(()->{
					log.error("Ne postoji bolest s id: {}",id);
					throw new DeleteFailureException("Ne postoji objekt za brisanje!");
				});
		try {
			bolestRepository.delete(bolest);
			return ResponseEntity.status(HttpStatus.OK).body("Bolest uspjesno obrisana");
		} catch (Exception e) {
			log.error("Nije moguce obrisati bolest: {}",bolest.toString());
			throw new DeleteFailureException(e.getMessage());
		}
	}
}
