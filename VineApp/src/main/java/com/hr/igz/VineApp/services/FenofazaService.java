package com.hr.igz.VineApp.services;

import org.springframework.http.ResponseEntity;

import com.hr.igz.VineApp.domain.dto.FenofazaDto;

public interface FenofazaService {

	ResponseEntity<Object> addFenofaza(FenofazaDto fenofaza);

}
