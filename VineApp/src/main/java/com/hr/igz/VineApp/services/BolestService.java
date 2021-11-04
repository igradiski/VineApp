package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface BolestService {

	ResponseEntity<Object> addBolest(BolestDto bolest);

	Page<BolestDto> getBolestiPaged(int pageSize, int pageNo, String[] sort);

	ResponseEntity<Object> deleteBolestByName(Long id);

	ResponseEntity<Object> updateBolest(BolestDto bolestDto, Long id);

	Page<BolestDto> findBolestByNamePaged(int pageSize, int pageNo, String[] sort, String name);

    ResponseEntity<Set<AntDCascaderDto>> getBolestiZaCascader();

	Optional<BolestDto> findBolestByName(String name);

    Optional<BolestDto> getBolestForCard(Long id);
}
