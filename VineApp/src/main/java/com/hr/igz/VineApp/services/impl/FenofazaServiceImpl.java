package com.hr.igz.VineApp.services.impl;

import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import com.hr.igz.VineApp.exception.DeleteFailureException;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.FenofazaMapper;
import com.hr.igz.VineApp.repository.FenofazaRepository;
import com.hr.igz.VineApp.services.FenofazaService;
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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class FenofazaServiceImpl  implements FenofazaService{
	
	private final FenofazaRepository fenofazaRepository;
	
	private final FenofazaMapper mapper;
	
	private final SortingHelperUtil sortHelper;

	@Override
	@Transactional
	public ResponseEntity<Object> addFenofaza(FenofazaDto fenofaza) {
		
		if(fenofazaRepository.existsByName(fenofaza.getName())) {
			log.error("Postoji fenofaza s imenom: {}",fenofaza.getName());
			throw new ObjectAlreadyExists("Fenofaza imena vec postoji!");
		}
		FenozafaRazvoja fenofazaRazvoja = mapper.FenofazaDtoToFenofaza(fenofaza);
		try {
			fenofazaRepository.save(fenofazaRazvoja);
		} catch (Exception e) {
			log.error("Nije moguce unijeti fenofazu{}",fenofaza);
			throw new PostFailureException("Nije moguce unijeti zeljenu fenofazu!");
		}
		return ResponseEntity.status(HttpStatus.CREATED).body("Fenofaza je uspješno kreirana");
	}

	@Override
	@Transactional(readOnly = true)
	public Page<FenofazaDto> getFenofazePaged(int pageSize, int pageNo, String[] sort) {
		
		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(orders));
		return fenofazaRepository.findAll(paging).map(mapper::FenofazaToFenofazaDto);
	}

	@Override
	@Transactional(readOnly = true)
	public Optional<FenofazaDto> findFenofazaByName(String name) {
		return fenofazaRepository.findByName(name).map(mapper::FenofazaToFenofazaDto);
	}

	
	@Override
	@Transactional(readOnly = true)
	public Page<FenofazaDto> findFenofazaByNamePaged(int pageSize, int pageNo, String[] sort,
			String name) {
		
		List<Order> orders = sortHelper.getOrdersFromArray(sort);
		Pageable paging = PageRequest.of(pageNo, pageSize,Sort.by(orders));
		return fenofazaRepository.findByNameContaining(name,paging).map(mapper::FenofazaToFenofazaDto);
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
	public ResponseEntity<Object> updateFenofaza(FenofazaDto fenofaza, Long id) {

		FenozafaRazvoja oldFenofaza = fenofazaRepository.findById(id)
				.orElseThrow(()->{
					log.error("Nije moguce pronaći fenofazu: {}",id);
					throw new PostFailureException("Nije moguce pronaći željenu fenofazu!");
				});
		oldFenofaza = mapper.UpdateFenofazaFromDto(oldFenofaza,fenofaza);
		try {
			fenofazaRepository.save(oldFenofaza);
		}catch (Exception e) {
			log.error("Nije moguce ažurirati fenofazu{}",fenofaza.toString());
			throw new PostFailureException("Nije moguce ažurirati zeljenu fenofazu!");
		}
		return ResponseEntity.status(HttpStatus.OK).body("Fenofaza je uspješno ažurirana");
	}
	
	@Override
	@Transactional
	public ResponseEntity<Object> deleteFenofazaById(Long id) {

		FenozafaRazvoja fenofaza = fenofazaRepository.findById(id).orElseThrow(()->{
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
