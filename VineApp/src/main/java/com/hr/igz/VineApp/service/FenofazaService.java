package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface FenofazaService {

	FenofazaDto addFenofaza(FenofazaDto fenofaza);

	Page<FenofazaDto> getFenofazePaged(Pageable pageable);

	FenofazaDto updateFenofaza(FenofazaDto fenofaza);

	Page<FenofazaDto> findFenofazaByNamePaged(Pageable pageable, String name);

	ResponseEntity<Object> deleteFenofazaById(Long id);

    ResponseEntity<Set<AntDCascaderDto>> getFenofazeZaCascader();

    Optional<FenofazaDto> findFenofazaByName(String name);
}
