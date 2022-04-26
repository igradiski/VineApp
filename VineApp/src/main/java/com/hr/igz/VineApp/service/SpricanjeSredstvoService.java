package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.SpricanjeOmjerDto;
import com.hr.igz.VineApp.domain.dto.SpricanjeSredstvoDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface SpricanjeSredstvoService {

    SpricanjeSredstvoDto addSredstvoHasSpricanje(SpricanjeSredstvoDto spricanjeSredstvoDto);

    Page<SpricanjeSredstvoDto> getSredstvaPaged(Pageable pageable, Long id);

    Optional<SpricanjeOmjerDto> getOmjeri(Long sredstvoId, Long spricanjeId);

    ResponseEntity<Object> deleteById(Long id);

    SpricanjeSredstvoDto updateSredstvoById(SpricanjeSredstvoDto spricanjeSredstvoDto);
}
