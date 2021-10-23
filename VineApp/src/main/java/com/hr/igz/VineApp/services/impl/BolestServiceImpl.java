package com.hr.igz.VineApp.services.impl;

import java.time.Instant;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import com.hr.igz.VineApp.exception.DeleteFailureException;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.BolestMapper;
import com.hr.igz.VineApp.repository.BolestRepository;
import com.hr.igz.VineApp.services.BolestService;
import com.hr.igz.VineApp.utils.SortingHelperUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BolestServiceImpl implements BolestService {

	private BolestRepository bolestRepository;

	private final BolestMapper mapper;

	private SortingHelperUtil sortHelper;

	@Autowired
	public BolestServiceImpl(BolestRepository repos, BolestMapper mapper) {

		this.mapper = mapper;
		this.bolestRepository = repos;
	}

	@Autowired
	public void setSortHelper(SortingHelperUtil sortHelper) {
		this.sortHelper = sortHelper;
	}

	@Override
	public ResponseEntity<Object> addBolest(BolestDto bolestDto) {

		if (bolestRepository.existsByName(bolestDto.getName())) {
			log.error("Postoji fenofaza s imenom: {}", bolestDto.getName());
			throw new ObjectAlreadyExists("Bolest toga imena vec postoji!");
		}
		Bolest bolest = mapper.BolestDtoToBolest(bolestDto);
		bolest.setDate(Instant.now());
		try {
			bolestRepository.save(bolest);
		} catch (Exception e) {
			log.error("Greška kod unosa bolesti: {}", bolest.toString());
			throw new PostFailureException("Nije moguce unijeti zeljenu bolest!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Tip sredstva je uspješno kreiran");
	}

	@Override
	public ResponseEntity<Map<String, Object>> getBolestiPaged(int pageSize, int pageNo, String[] sort) {

		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
		Page<Bolest> page = bolestRepository.findAll(paging);
		return new ResponseEntity<>(createResponse(page), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> findBolestByNamePaged(int pageSize, int pageNo, String[] sort,
			String name) {
		
		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
		Page<Bolest> page = bolestRepository.findByNameContaining(name,paging);
		return new ResponseEntity<>(createResponse(page), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Object> deleteBolestByName(Long id) {

		Bolest bolest = bolestRepository.findById(id)
				.orElseThrow(()->{
					log.error("Ne postoji bolest s id: {}",id.toString());
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

	@Override
	public ResponseEntity<Object> updateBolest(BolestDto bolestDto,Long id) {
		
		Bolest oldBolest = bolestRepository.findById(id)
				.orElseThrow(()->{
					log.error("Ne postoji bolest za azuriranje s ID: {}",id.toString());
					throw new PostFailureException("Ne postoji bolest za azuriranje!");
				});
		oldBolest = mapper.UpdateBolestFromDto(oldBolest, bolestDto);
		oldBolest.setDate(Instant.now());
		try {
			bolestRepository.save(oldBolest);
		}catch (Exception e) {
			log.error("Nije moguce ažurirati bolest: {}",oldBolest.toString());
			throw new PostFailureException("Nije moguce ažurirati zeljenu bolest!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Bolest je uspješno ažurirana");
	}

	private Map<String, Object> createResponse(Page<Bolest> page) {

		Map<String, Object> response = new HashMap<>();
		if (page != null) {
			response.put("bolesti", mapAllBolesti(page.getContent()));
			response.put("totalPages", page.getTotalPages());
			response.put("totalItems", page.getTotalElements());
			response.put("currentPage", page.getNumber());
		}
		return response;
	}

	private Set<BolestDto> mapAllBolesti(List<Bolest> list) {

		Set<BolestDto> set = new HashSet<BolestDto>();
		if (!list.isEmpty()) {
			list.stream().forEach(fenofazaDomain -> {
				set.add(mapper.BolestToBolestDto(fenofazaDomain));
			});
		}
		return set;
	}
}
