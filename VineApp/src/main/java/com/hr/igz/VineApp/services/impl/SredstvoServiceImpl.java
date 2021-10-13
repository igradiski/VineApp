package com.hr.igz.VineApp.services.impl;

import java.time.Instant;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.SredstvoMapper;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.services.SredstvoService;
import com.hr.igz.VineApp.utils.SortingHelperUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SredstvoServiceImpl implements SredstvoService {
	
	private SredstvoRepository repos;
	
	private TipSredstvaRepository sredstvoRepos;
	
	private final SredstvoMapper mapper;
	
	private SortingHelperUtil sortHelper;
	
	
	@Autowired
	public SredstvoServiceImpl(SredstvoRepository repos,TipSredstvaRepository sredstvoRepos,SredstvoMapper mapper) {
		this.repos= repos;
		this.sredstvoRepos=sredstvoRepos;
		this.mapper= mapper;
	}
	
	@Autowired
	public void setSortHelper(SortingHelperUtil sortHelper) {
		this.sortHelper = sortHelper;
	}
	

	@Override
	@Transactional
	public ResponseEntity<Object> addSredstvo(SredstvoDto sredstvo) {
		
		if(repos.existsByName(sredstvo.getName()))
			throw new ObjectAlreadyExists("Zastitno sredstvo vec postoji u bazi!");
		ZastitnoSredstvo zastitnoSredstvo = mapper.sredstvoDtoToZastitnoSredstvo(sredstvo,sredstvoRepos);
		zastitnoSredstvo.setApproved(1);
		zastitnoSredstvo.setDate(Instant.now());
		try {
			repos.save(zastitnoSredstvo);
		}catch (Exception e) {
			log.info("Greska kod unosa zastitnog sredstva!");
			throw new PostFailureException(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Sredstvo uspješno dodano!");
	}

	@Override
	public ResponseEntity<Map<String, Object>> getAllSredstvaPagable(int pageNo, int pageSize, String[] sort) {
		
		List<Order> orders = new ArrayList<Order>();
		if (sort[0].contains(",")) {
	        for (String sortOrder : sort) {
	          String[] _sort = sortOrder.split(",");
	          orders.add(new Order(sortHelper.getSortDirection(_sort[1]), _sort[0]));
	        }
	      } else {
	        orders.add(new Order(sortHelper.getSortDirection(sort[1]), sort[0]));
	      }
		Map<String, Object> response = new HashMap<>();
		Pageable paging = PageRequest.of(pageNo, pageSize);
		Page<ZastitnoSredstvo> page = repos.findAll(paging);
		response.put("sredstva", page.getContent());
		response.put("totalPages", page.getTotalPages());
		response.put("totalItems", page.getTotalElements());
		response.put("currentPage", page.getNumber());
		return new ResponseEntity<>(response,HttpStatus.OK);
	}
	
	
	
}
