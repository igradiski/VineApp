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

import java.util.Objects;
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
		Objects.requireNonNull(fenofaza);
		if(fenofaza.id() != null){
			log.error("Nije moguce unijeti fenofazu jer postoji id: {}",fenofaza.id());
			throw new PostFailureException("Nije moguce unijeti zeljenu fenofazu jer postoji ID");
		}
		log.info(fenofaza.toString());
		log.debug("FENOFAZA: "+fenofaza.toString());
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
		log.info("Fething all fenofaze");
		return fenofazaRepository.findAll(pageable).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<FenofazaDto> findFenofazaByName(String name) {
		log.debug("name: "+name);
		log.info("Fetching faza with name "+name);
		return fenofazaRepository.findByName(name).map(mapper::toDto);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Page<FenofazaDto> findFenofazaByNamePaged(Pageable pageable,String name) {
		log.debug("name: "+name);
		log.info("Fetching faze with name "+name);
		return fenofazaRepository.findByNameContainingIgnoreCase(name,pageable).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<Set<AntDCascaderDto>> getFenofazeZaCascader() {
		log.info("Fetching fenofaze for cascader");
		Set<AntDCascaderDto> set = fenofazaRepository.findAll().stream()
				.map(mapper::FenofazaToCascaderDto).collect(Collectors.toSet());
		return new ResponseEntity<>(set,HttpStatus.OK);
	}

	@Override
	@Transactional
	public FenofazaDto updateFenofaza(FenofazaDto fenofaza) {

		log.debug(fenofaza.toString());
		Objects.requireNonNull(fenofaza.id(),"Id cant be null!");
		FenofazaRazvoja oldFenofaza = fenofazaRepository.findById(fenofaza.id())
				.orElseThrow(()->{
					log.error("Nije moguce prona??i fenofazu: {}",fenofaza.id());
					throw new PostFailureException("Nije moguce prona??i ??eljenu fenofazu!");
				});
		oldFenofaza = mapper.UpdateFenofazaFromDto(oldFenofaza,fenofaza);
		log.debug("Updated object: "+oldFenofaza.toString());
		try {
			return mapper.toDto(fenofazaRepository.save(oldFenofaza));
		}catch (Exception e) {
			log.error("Nije moguce a??urirati fenofazu{}",fenofaza.toString());
			throw new PostFailureException("Nije moguce a??urirati zeljenu fenofazu!");
		}
	}
	
	@Override
	@Transactional
	public ResponseEntity<Object> deleteFenofazaById(Long id) {
		log.debug("ID: "+id);
		FenofazaRazvoja fenofaza = fenofazaRepository.findById(id).orElseThrow(()->{
			log.error("Nije moguce prona??i fenofazu s id: {}",id);
			throw new DeleteFailureException("Nije moguce prona??i ??eljenu fenofazu!");
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
