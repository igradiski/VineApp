package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.VinovaLozaDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface VinovaLozaService {


    ResponseEntity<Object> dodajLozu(VinovaLozaDto vinovaLozaDto);

    Page<VinovaLozaDto> getVinovaLozaPaged(int pageSize, int pageNo, String[] sort);

    Optional<VinovaLozaDto> getLozaForCard(Long id);

    ResponseEntity<Object> deleteLozaById(Long id);

    ResponseEntity<Object> updateLoza(VinovaLozaDto vinovaLozaDto, Long id);

    Set<AntDCascaderDto> getVinovaLozaCascader();
}
