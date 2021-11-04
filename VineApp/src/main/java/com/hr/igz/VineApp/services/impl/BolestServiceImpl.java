package com.hr.igz.VineApp.services.impl;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import com.hr.igz.VineApp.exception.DeleteFailureException;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.BolestMapper;
import com.hr.igz.VineApp.repository.BolestRepository;
import com.hr.igz.VineApp.services.BolestService;
import com.hr.igz.VineApp.utils.SortingHelperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import java.util.Base64;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class BolestServiceImpl implements BolestService {

	private final BolestRepository bolestRepository;

	private final BolestMapper mapper;

	private final SortingHelperUtil sortHelper;

	@Override
	@Transactional
	public ResponseEntity<Object> addBolest(BolestDto bolestDto) {


		if (bolestRepository.existsByName(bolestDto.getName())) {
			log.error("Postoji fenofaza s imenom: {}", bolestDto.getName());
			throw new ObjectAlreadyExists("Bolest toga imena vec postoji!");
		}
		Bolest bolest = mapper.BolestDtoToBolest(bolestDto);
		bolest.setApproved(0);
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(bolestDto.getBase64());
			log.info(String.valueOf(decodedBytes.length));
			bolest.setPicture(decodedBytes);
			bolestRepository.save(bolest);
		} catch (Exception e) {
			log.error("Greška kod unosa bolesti: {}", bolest);
			throw new PostFailureException("Nije moguce unijeti zeljenu bolest!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Tip sredstva je uspješno kreiran");
	}

	@Override
	@Transactional(readOnly = true)
	public Page<BolestDto> getBolestiPaged(int pageSize, int pageNo, String[] sort) {

		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
		return bolestRepository.findAll(paging).map(mapper::BolestToBolestDto);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Page<BolestDto> findBolestByNamePaged(int pageSize, int pageNo, String[] sort,
			String name) {
		
		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
		return bolestRepository.findByNameContaining(name,paging).map(mapper::BolestToBolestDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<BolestDto> getBolestForCard(Long id) {
		Bolest bolest = bolestRepository.findById(id).get();
		String base64 = Base64.getEncoder().encodeToString(bolest.getPicture());
		BolestDto dto = mapper.BolestToBolestDto(bolest);
		dto.setBase64(base64);
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
		return bolestRepository.findByName(name).map(mapper::BolestToBolestDto);
	}

	@Override
	@Transactional
	public ResponseEntity<Object> updateBolest(BolestDto bolestDto,Long id) {

		Bolest oldBolest = bolestRepository.findById(id)
				.orElseThrow(()->{
					log.error("Ne postoji bolest za azuriranje s ID: {}",id);
					throw new PostFailureException("Ne postoji bolest za azuriranje!");
				});
		oldBolest = mapper.UpdateBolestFromDto(oldBolest, bolestDto);
		try {
			bolestRepository.save(oldBolest);
		}catch (Exception e) {
			log.error("Nije moguce ažurirati bolest: {}",oldBolest.toString());
			throw new PostFailureException("Nije moguce ažurirati zeljenu bolest!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Bolest je uspješno ažurirana");
	}

	@Override
	@Transactional
	public ResponseEntity<Object> deleteBolestByName(Long id) {

		Bolest bolest = bolestRepository.findById(id)
				.orElseThrow(()->{
					log.error("Ne postoji bolest s id: {}",id);
					throw new DeleteFailureException("Ne postoji objekt za brisanje!");
				});
		try {
			bolestRepository.delete(bolest);
			return ResponseEntity.status(HttpStatus.CREATED).body("Bolest uspjesno obrisana");
		} catch (Exception e) {
			log.error("Nije moguce obrisati bolest: {}",bolest.toString());
			throw new DeleteFailureException(e.getMessage());
		}
	}
}
