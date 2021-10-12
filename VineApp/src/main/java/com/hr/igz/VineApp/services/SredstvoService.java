package com.hr.igz.VineApp.services;

import org.springframework.http.ResponseEntity;

import com.hr.igz.VineApp.domain.dto.SredstvoDto;

public interface SredstvoService {

	ResponseEntity<Object> addSredstvo(SredstvoDto sredstvo);

}
