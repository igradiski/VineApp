package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.service.SredstvoService;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
import com.hr.igz.VineApp.service.exception.NoSuchElementException;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.SredstvoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Base64;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class SredstvoServiceImpl implements SredstvoService {
	
	private final SredstvoRepository sredstvoRepository;
	private final TipSredstvaRepository tipSredstvaRepository;
	private final SredstvoMapper mapper;

	private Logger log = LoggerFactory.getLogger(SredstvoServiceImpl.class);

	public SredstvoServiceImpl(SredstvoRepository sredstvoRepository, TipSredstvaRepository tipSredstvaRepository, SredstvoMapper mapper) {
		this.sredstvoRepository = sredstvoRepository;
		this.tipSredstvaRepository = tipSredstvaRepository;
		this.mapper = mapper;
	}

	@Override
	@Transactional
	public SredstvoDto addSredstvo(SredstvoDto sredstvo) {

		log.debug(sredstvo.toString());
		log.info("Adding new sredstvo: "+sredstvo.toString());
		if(sredstvoRepository.existsByName(sredstvo.name())){
			log.error("Sredstvo s imenom {} postoji u bazi",sredstvo.name());
			throw new ObjectAlreadyExists("Zastitno sredstvo vec postoji u bazi!");
		}
		var tipSredstva = tipSredstvaRepository.findById(sredstvo.tipSredstvaId()).orElseThrow( () -> {
			log.error("Tip sredstva s id: {} postoji u bazi",sredstvo.tipSredstvaId());
			throw new PostFailureException("Tip sredstva s id: "+ sredstvo.tipSredstvaId()+" ne postoji !");
		});
		ZastitnoSredstvo zastitnoSredstvo = mapper.toEntity(sredstvo);
		zastitnoSredstvo.setTipZastitnogSredstva(tipSredstva);
		zastitnoSredstvo.setApproved(0);
		try {
			return mapper.toDto(sredstvoRepository.save(zastitnoSredstvo));
		}catch (Exception e) {
			log.info("Greska kod unosa zastitnog sredstva {}",sredstvo);
			throw new PostFailureException(e.getMessage());
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SredstvoDto> getAllSredstvaPagable(Pageable pageable) {

		log.debug(pageable.toString());
		log.info("Fetching all sredstava with paging");
		return sredstvoRepository.findAll(pageable).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public SredstvoDto findSredstvoByName(String name) {

		log.debug(name);
		log.info("Finding sredstvo with name : "+name);
		ZastitnoSredstvo sredstvo = sredstvoRepository.findByName(name).orElseThrow(() -> {
			log.error("Sredstvo ne postoji!");
			throw new NoSuchElementException("Sredstvo ne postoji!");
		});
		return mapper.toDto(sredstvo);
	}

	@Override
	@Transactional(readOnly = true)
	public SredstvoDto getSredstvoForCard(Long id) {

		log.debug("ID: "+id);
		log.info("Fetching sredstvo with id: "+id);
		ZastitnoSredstvo sredstvo = getSredstvo(id);
		return mapper.toDto(sredstvo);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Object> getUtrosak(Integer voda, Long id) {

		log.debug("Voda: "+voda+" Sredstvo: "+id);
		log.info("Voda: "+voda+" Sredstvo: "+id);

		ZastitnoSredstvo sredstvo = getSredstvo(id);
		if(voda != null  && voda > 0){
			BigDecimal na100 = (sredstvo.getDosageOn100().divide(new BigDecimal(100)));
			BigDecimal naKolicinu = na100.multiply(new BigDecimal(voda));
			return Optional.of(naKolicinu);
		}else{
			return Optional.of(0);
		}
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SredstvoDto> findSredstvoByNamePaged(Pageable pageable, String name) {

		log.debug("name "+name);
		log.info("Fetching sredstvo with name containing: "+name);
		return sredstvoRepository.findByNameContaining(name,pageable).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<Set<AntDCascaderDto>> getSredstvaForCascader() {

		log.info("Fetching sredstva for cascader");
		Set<AntDCascaderDto> set = sredstvoRepository.findAll().stream()
				.map(mapper::SredstvoToCascaderDto).collect(Collectors.toSet());
		return new ResponseEntity<>(set,HttpStatus.OK);
	}

	@Override
	@Transactional
	public SredstvoDto updateSredstvo(SredstvoDto sredstvoDto) {

		log.debug(sredstvoDto.toString());
		log.info("updating sredstvo : "+sredstvoDto.toString());
		Objects.requireNonNull(sredstvoDto.id(),"Id cant be null!");
		ZastitnoSredstvo oldSredstvo = getSredstvo(sredstvoDto.id());

		var tipSredstva = tipSredstvaRepository.findById(sredstvoDto.tipSredstvaId()).orElseThrow( () -> {
			log.error("Tip sredstva s id: {} postoji u bazi",sredstvoDto.tipSredstvaId());
			throw new PostFailureException("Tip sredstva s id: "+ sredstvoDto.tipSredstvaId()+" ne postoji !");
		});
		oldSredstvo = mapper.UpdateSredstvoFromDto(oldSredstvo,sredstvoDto);
		oldSredstvo.setTipZastitnogSredstva(tipSredstva);
		try{
			return mapper.toDto(sredstvoRepository.save(oldSredstvo));
		}catch (Exception e){
			log.error("Azuriranje sredstva {} nije uspjelo!",sredstvoDto.toString());
			throw new PostFailureException("Nije moguce ažurirati zeljeno sredstvo!");
		}
	}

	@Override
	@Transactional
	public ResponseEntity<Object> deleteSredstvoById(Long id) {

		log.debug("Deleting id: "+id);
		ZastitnoSredstvo sredstvo = getSredstvo(id);
		try{
			sredstvoRepository.delete(sredstvo);
			return ResponseEntity.status(HttpStatus.OK).body("Fenofaza uspjesno obrisana");
		}catch (Exception e){
			log.error("Nije moguce obrisati sredstvo: {}",sredstvo.toString());
			throw new DeleteFailureException("Ne postoji objekt za brisanje!");
		}
	}

	 @Transactional(readOnly = true)
	private ZastitnoSredstvo getSredstvo(Long id){
		return sredstvoRepository.findById(id)
				.orElseThrow(()->{
					log.error("Ne postoji sredstvo s id: {}",id);
					throw new DeleteFailureException("Nije moguce pronaći željeno sredstvo!");
				});
	}
}
