package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.FenofazaRazvoja;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import com.hr.igz.VineApp.repository.FenofazaRepository;
import com.hr.igz.VineApp.service.FenofazaService;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.FenofazaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class FenofazaServiceImpl  implements FenofazaService{
	
	private final FenofazaRepository fenofazaRepository;
	private final FenofazaMapper mapper;

	private Logger log = LoggerFactory.getLogger(FenofazaServiceImpl.class);

	public FenofazaServiceImpl(FenofazaRepository fenofazaRepository, FenofazaMapper mapper) {
		this.fenofazaRepository = fenofazaRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public FenofazaDto addFenofaza(FenofazaDto fenofaza) {
		
		if(fenofazaRepository.existsByName(fenofaza.name())) {
			log.error("Postoji fenofaza s imenom: {}",fenofaza.name());
			throw new ObjectAlreadyExists("Fenofaza imena vec postoji!");
		}
		FenofazaRazvoja fenofazaRazvoja = mapper.toEntity(fenofaza);
		try {
			return mapper.toDto(fenofazaRepository.save(fenofazaRazvoja));
		} catch (Exception e) {
			log.error("Nije moguce unijeti fenofazu{}",fenofaza);
			throw new PostFailureException("Nije moguce unijeti zeljenu fenofazu!");
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<FenofazaDto> getFenofazePaged(Pageable pageable) {
		return fenofazaRepository.findAll(pageable).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<FenofazaDto> findFenofazaByName(String name) {
		log.info("Fetching faza with name "+name);
		return fenofazaRepository.findByName(name).map(mapper::toDto);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Page<FenofazaDto> findFenofazaByNamePaged(Pageable pageable,String name) {
		log.info("Fetching faze with name "+name);
		return fenofazaRepository.findByNameContainingIgnoreCase(name,pageable).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<Set<AntDCascaderDto>> getFenofazeZaCascader() {
		Set<AntDCascaderDto> set = fenofazaRepository.findAll().stream()
				.map(mapper::FenofazaToCascaderDto).collect(Collectors.toSet());
		return new ResponseEntity<>(set,HttpStatus.OK);
	}

	@Override
	@Transactional
	public FenofazaDto updateFenofaza(FenofazaDto fenofaza) {

		FenofazaRazvoja oldFenofaza = fenofazaRepository.findById(fenofaza.id())
				.orElseThrow(()->{
					log.error("Nije moguce pronaći fenofazu: {}",fenofaza.id());
					throw new PostFailureException("Nije moguce pronaći željenu fenofazu!");
				});
		oldFenofaza = mapper.UpdateFenofazaFromDto(oldFenofaza,fenofaza);
		try {
			return mapper.toDto(fenofazaRepository.save(oldFenofaza));
		}catch (Exception e) {
			log.error("Nije moguce ažurirati fenofazu{}",fenofaza.toString());
			throw new PostFailureException("Nije moguce ažurirati zeljenu fenofazu!");
		}
	}
	
	@Override
	@Transactional
	public ResponseEntity<Object> deleteFenofazaById(Long id) {

		FenofazaRazvoja fenofaza = fenofazaRepository.findById(id).orElseThrow(()->{
			log.error("Nije moguce pronaći fenofazu s id: {}",id);
			throw new DeleteFailureException("Nije moguce pronaći željenu fenofazu!");
		});
		try {
			fenofazaRepository.delete(fenofaza);
			return ResponseEntity.status(HttpStatus.OK).body("Fenofaza uspjesno obrisana");
		} catch (Exception e) {
			log.error("Nije moguce obrisati fenofazu: {}",fenofaza.getId().toString());
			throw new DeleteFailureException("Nije moguce obrisati fenofazu:");
		}
	}
}
