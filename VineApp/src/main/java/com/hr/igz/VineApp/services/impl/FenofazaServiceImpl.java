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

import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import com.hr.igz.VineApp.exception.DeleteFailureException;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.FenofazaMapper;
import com.hr.igz.VineApp.repository.FenofazaRepository;
import com.hr.igz.VineApp.services.FenofazaService;
import com.hr.igz.VineApp.utils.SortingHelperUtil;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class FenofazaServiceImpl  implements FenofazaService{
	
	private FenofazaRepository fenofazaRepository;
	
	private final FenofazaMapper mapper;
	
	private SortingHelperUtil sortHelper;
	
	@Autowired
	public FenofazaServiceImpl(FenofazaRepository fenofazaRepository,FenofazaMapper mapper) {
		
		this.fenofazaRepository = fenofazaRepository;
		this.mapper = mapper;
	}
	
	@Autowired
	public void setSortHelper(SortingHelperUtil sortHelper) {
		this.sortHelper = sortHelper;
	}

	@Override
	public ResponseEntity<Object> addFenofaza(FenofazaDto fenofaza) {
		
		if(fenofazaRepository.existsByName(fenofaza.getName())) {
			throw new ObjectAlreadyExists("Fenofaza tog imena vec postoji!");
		}
		FenozafaRazvoja fenofazaRazvoja = mapper.FenofazaDtoToFenofaza(fenofaza);
		fenofazaRazvoja.setDate(Instant.now());
		try {
			fenofazaRepository.save(fenofazaRazvoja);
		} catch (Exception e) {
			throw new PostFailureException("Nije moguce unijeti zeljenu fenofazu!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Fenofaza je uspješno kreirana");
	}

	@Override
	public ResponseEntity<Map<String, Object>> getFenofazePaged(int pageSize, int pageNo, String[] sort) {
		
		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(orders));
		Page<FenozafaRazvoja> page = fenofazaRepository.findAll(paging);
		return new ResponseEntity<>(createResponse(page),HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<Object> updateFenofaza(FenofazaDto fenofaza, Long id) {

		FenozafaRazvoja oldFenofaza = fenofazaRepository.findById(id).get();
		oldFenofaza = mapper.UpdateFenofazaFromDto(oldFenofaza,fenofaza);
		oldFenofaza.setDate(Instant.now());
		fenofazaRepository.save(oldFenofaza);
		return ResponseEntity.status(HttpStatus.OK).body("Fenofaza je uspješno ažurirana");
	}
	
	@Override
	public ResponseEntity<Map<String, Object>> findFenofazaByNamePaged(int pageSize, int pageNo, String[] sort,
			String name) {
		
		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(orders));
		Page<FenozafaRazvoja> page = fenofazaRepository.findByNameContaining(name,paging);
		return new ResponseEntity<>(createResponse(page),HttpStatus.OK);
	}

	private Map<String, Object> createResponse(Page<FenozafaRazvoja> page) {
		
		Map<String, Object> response = new HashMap<>();
		response.put("fenofaze",mapAllFenofaze(page.getContent()));
		response.put("totalPages", page.getTotalPages());
		response.put("totalItems", page.getTotalElements());
		response.put("currentPage", page.getNumber());
		return response;
	}

	private Set<FenofazaDto> mapAllFenofaze(List<FenozafaRazvoja> list) {
		
		Set<FenofazaDto> set = new HashSet<FenofazaDto>();
		list.stream().forEach(fenofazaDomain ->{
			set.add(mapper.FenofazaToFenofazaDto(fenofazaDomain));
		});
		return set;
	}

	@Override
	public ResponseEntity<Object> deleteFenofazaById(Long id) {
		
		FenozafaRazvoja fenofaza = fenofazaRepository.findById(id)
				.orElseThrow(() -> new DeleteFailureException("Ne postoji objekt za brisanje!"));
		try {
			fenofazaRepository.delete(fenofaza);
			return ResponseEntity.status(HttpStatus.CREATED).body("Fenofaza uspjesno obrisana"); 
		}catch (Exception e) {
			throw new DeleteFailureException("Ne postoji objekt za brisanje!");
		}
	}
}
