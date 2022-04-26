package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.VinovaLozaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;
import java.util.Set;

public interface VinovaLozaService {


    VinovaLozaDto dodajLozu(VinovaLozaDto vinovaLozaDto);

    Page<VinovaLozaDto> getVinovaLozaPaged(Pageable pageable);

    Optional<VinovaLozaDto> getLozaForCard(Long id);

    ResponseEntity<Object> deleteLozaById(Long id);

    VinovaLozaDto updateLoza(VinovaLozaDto vinovaLozaDto);

    Set<AntDCascaderDto> getVinovaLozaCascader();
}
