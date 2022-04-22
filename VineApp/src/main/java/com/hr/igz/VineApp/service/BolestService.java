package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface BolestService {

	BolestDto addBolest(BolestDto bolest);

	Page<BolestDto> getBolestiPaged(Pageable pageable);

	ResponseEntity<Object> deleteBolestByName(Long id);

	ResponseEntity<Object> updateBolest(BolestDto bolestDto);

	Page<BolestDto> findBolestByNamePaged(Pageable pageable, String name);

    ResponseEntity<Set<AntDCascaderDto>> getBolestiZaCascader();

	Optional<BolestDto> findBolestByName(String name);

    Optional<BolestDto> getBolestForCard(Long id);
}
