package com.hr.igz.VineApp.services.impl;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.exception.DeleteFailureException;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.SredstvoMapper;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.services.SredstvoService;
import com.hr.igz.VineApp.utils.SortingHelperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class SredstvoServiceImpl implements SredstvoService {
	
	private final SredstvoRepository sredstvoRepository;
	
	private final TipSredstvaRepository tipSredstvaRepository;
	
	private final SredstvoMapper mapper;
	
	private final SortingHelperUtil sortHelper;

	@Override
	@Transactional
	public ResponseEntity<Object> addSredstvo(SredstvoDto sredstvo) {
		
		if(sredstvoRepository.existsByName(sredstvo.getName())){
			log.info("Sredstvo s imenom {} postoji u bazi",sredstvo.getName());
			throw new ObjectAlreadyExists("Zastitno sredstvo vec postoji u bazi!");
		}
		ZastitnoSredstvo zastitnoSredstvo = mapper.sredstvoDtoToZastitnoSredstvo(sredstvo,tipSredstvaRepository);
		zastitnoSredstvo.setApproved(1);
		zastitnoSredstvo.setDate(Instant.now());
		try {
			sredstvoRepository.save(zastitnoSredstvo);
		}catch (Exception e) {
			log.info("Greska kod unosa zastitnog sredstva {}",sredstvo.toString());
			throw new PostFailureException(e.getMessage());
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Sredstvo uspješno dodano!");
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<Map<String, Object>> getAllSredstvaPagable(int pageNo, int pageSize, String[] sort) {
		
		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(orders));
		Page<ZastitnoSredstvo> page = sredstvoRepository.findAll(paging);
		return new ResponseEntity<>(createResponse(page),HttpStatus.OK);
	}

	@Override
	@Transactional(readOnly = true)
	public ResponseEntity<Map<String, Object>> findSredstvoByNamePaged(int pageSize, int pageNo, String[] sort, String name) {

		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(orders));
		Page<ZastitnoSredstvo> page = sredstvoRepository.findByNameContaining(name,paging);
		return new ResponseEntity<>(createResponse(page),HttpStatus.OK);
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
	public ResponseEntity<Object> updateSredstvo(SredstvoDto sredstvoDto, Long id) {

		ZastitnoSredstvo oldSredstvo = sredstvoRepository.findById(id)
				.orElseThrow(()->{
					log.error("Nije moguce pronaci sredstvo s id: {}",id.toString());
					throw new PostFailureException("Nije moguce pronaci zeljeno sredstvo!");
				});
		oldSredstvo = mapper.UpdateSredstvoFromDto(oldSredstvo,sredstvoDto,tipSredstvaRepository);
		oldSredstvo.setDate(Instant.now());
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

		ZastitnoSredstvo sredstvo = sredstvoRepository.findById(id)
				.orElseThrow(()->{
					log.error("Ne postoji sredstvo s id: {}",id.toString());
					throw new DeleteFailureException("Nije moguce pronaći željenu fenofazu!");
				});
		try{
			sredstvoRepository.delete(sredstvo);
			return ResponseEntity.status(HttpStatus.OK).body("Fenofaza uspjesno obrisana");
		}catch (Exception e){
			log.error("Nije moguce obrisati sredstvo: {}",sredstvo.toString());
			throw new DeleteFailureException("Ne postoji objekt za brisanje!");
		}
	}

	private Map<String, Object> createResponse(Page<ZastitnoSredstvo> page){

		Map<String, Object> response = new HashMap<>();
		if(page != null){
			response.put("sredstva", mapAllSredstva(page.getContent()));
			response.put("totalPages", page.getTotalPages());
			response.put("totalItems", page.getTotalElements());
			response.put("currentPage", page.getNumber());
		}
		return response;
	}

	private Set<SredstvoDto> mapAllSredstva(List<ZastitnoSredstvo> list){
		Set<SredstvoDto> set = new HashSet<SredstvoDto>();
		list.stream().forEach(sredstvo ->{
			set.add(mapper.ZastitnoSredstvoToZastitnoSredstvoDto(sredstvo));
		});
		return set;
	}
}
