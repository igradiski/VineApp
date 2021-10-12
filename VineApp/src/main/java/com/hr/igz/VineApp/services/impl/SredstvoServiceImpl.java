package com.hr.igz.VineApp.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.mapper.MapStructMapper;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.services.SredstvoService;

@Service
public class SredstvoServiceImpl implements SredstvoService {
	
	private SredstvoRepository repos;
	
	private MapStructMapper mapper;
	
	@Autowired
	public SredstvoServiceImpl(SredstvoRepository repos,MapStructMapper mapper) {
		this.repos= repos;
		this.mapper = mapper;
	}

	@Override
	public ResponseEntity<Object> addSredstvo(SredstvoDto sredstvo) {
		
		if(repos.existsByName(sredstvo.getName()))
			throw new ObjectAlreadyExists("Zastitno sredstvo vec postoji u bazi!");
		ZastitnoSredstvo zastitnoSredstvo = mapper.sredstvoDtoToZastitnoSredstvo(sredstvo);
		repos.save(zastitnoSredstvo);
		return null;
	}

}
