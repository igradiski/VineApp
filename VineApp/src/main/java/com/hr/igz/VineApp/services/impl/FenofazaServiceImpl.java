package com.hr.igz.VineApp.services.impl;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.FenofazaMapper;
import com.hr.igz.VineApp.repository.FenofazaRepository;
import com.hr.igz.VineApp.services.FenofazaService;

@Service
public class FenofazaServiceImpl  implements FenofazaService{
	
	private FenofazaRepository fenofazaRepository;
	
	private final FenofazaMapper mapper;
	
	//private SortingHelperUtil sortHelper;
	
	@Autowired
	public FenofazaServiceImpl(FenofazaRepository fenofazaRepository,FenofazaMapper mapper) {
		this.fenofazaRepository = fenofazaRepository;
		this.mapper = mapper;
	}
	
//	@Autowired
//	public void setSortHelper(SortingHelperUtil sortHelper) {
//		this.sortHelper = sortHelper;
//	}

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

}
