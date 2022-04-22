package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.service.SredstvoService;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
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
	public ResponseEntity<Object> addSredstvo(SredstvoDto sredstvo) {
		
		if(sredstvoRepository.existsByName(sredstvo.name())){
			log.info("Sredstvo s imenom {} postoji u bazi",sredstvo.name());
			throw new ObjectAlreadyExists("Zastitno sredstvo vec postoji u bazi!");
		}
		ZastitnoSredstvo zastitnoSredstvo = mapper.toEntity(sredstvo);
		zastitnoSredstvo.setApproved(0);
		try {
			sredstvoRepository.save(zastitnoSredstvo);
		}catch (Exception e) {
			log.info("Greska kod unosa zastitnog sredstva {}",sredstvo);
			throw new PostFailureException(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Sredstvo uspješno dodano!");
	}

	@Override
	@Transactional(readOnly = true)
	public Page<SredstvoDto> getAllSredstvaPagable(Pageable pageable) {
		return sredstvoRepository.findAll(pageable).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SredstvoDto> findSredstvoByName(String name) {
		return sredstvoRepository.findByName(name).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<SredstvoDto> getSredstvoForCard(Long id) {

		ZastitnoSredstvo sredstvo = getSredstvo(id);
		String base64 = Base64.getEncoder().encodeToString(sredstvo.getPicture());
		SredstvoDto sredstvoDto = mapper.toDto(sredstvo);
		return Optional.of(sredstvoDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<Object> getUtrosak(Integer voda, Long id) {

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
		return sredstvoRepository.findByNameContaining(name,pageable).map(mapper::toDto);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<Set<AntDCascaderDto>> getSredstvaForCascader() {

		Set<AntDCascaderDto> set = sredstvoRepository.findAll().stream()
				.map(mapper::SredstvoToCascaderDto).collect(Collectors.toSet());
		return new ResponseEntity<>(set,HttpStatus.OK);
	}

	@Override
	@Transactional
	public ResponseEntity<Object> updateSredstvo(SredstvoDto sredstvoDto) {

		ZastitnoSredstvo oldSredstvo = getSredstvo(sredstvoDto.id());
		oldSredstvo = mapper.UpdateSredstvoFromDto(oldSredstvo,sredstvoDto,tipSredstvaRepository);
		try{
			sredstvoRepository.save(oldSredstvo);
		}catch (Exception e){
			log.error("Azuriranje sredstva {} nije uspjelo!",sredstvoDto.toString());
			throw new PostFailureException("Nije moguce ažurirati zeljeno sredstvo!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Sredstvo je uspješno ažurirano");
	}

	@Override
	@Transactional
	public ResponseEntity<Object> deleteSredstvoById(Long id) {

		ZastitnoSredstvo sredstvo = getSredstvo(id);
		try{
			sredstvoRepository.delete(sredstvo);
			return ResponseEntity.status(HttpStatus.OK).body("Fenofaza uspjesno obrisana");
		}catch (Exception e){
			log.error("Nije moguce obrisati sredstvo: {}",sredstvo.toString());
			throw new DeleteFailureException("Ne postoji objekt za brisanje!");
		}
	}

	private ZastitnoSredstvo getSredstvo(Long id){
		return sredstvoRepository.findById(id)
				.orElseThrow(()->{
					log.error("Ne postoji sredstvo s id: {}",id);
					throw new DeleteFailureException("Nije moguce pronaći željeno sredstvo!");
				});
	}
}
