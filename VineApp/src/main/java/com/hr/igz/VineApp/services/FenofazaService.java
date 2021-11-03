package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface FenofazaService {

	ResponseEntity<Object> addFenofaza(FenofazaDto fenofaza);

	Page<FenofazaDto> getFenofazePaged(int pageSize, int pageNo, String[] sort);

	ResponseEntity<Object> updateFenofaza(FenofazaDto fenofaza, Long id);

	Page<FenofazaDto> findFenofazaByNamePaged(int pageSize, int pageNo, String[] sort, String name);

	ResponseEntity<Object> deleteFenofazaById(Long id);

    ResponseEntity<Set<AntDCascaderDto>> getFenofazeZaCascader();

    Optional<FenofazaDto> findFenofazaByName(String name);
}
