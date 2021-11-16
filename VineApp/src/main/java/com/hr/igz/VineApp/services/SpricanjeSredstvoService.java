package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.SpricanjeOmjerDto;
import com.hr.igz.VineApp.domain.dto.SpricanjeSredstvoDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface SpricanjeSredstvoService {

    ResponseEntity<Object> addSredstvoHasSpricanje(SpricanjeSredstvoDto spricanjeSredstvoDto);

    Page<SpricanjeSredstvoDto> getSredstvaPaged(int pageSize, int pageNo, String[] sort,Long id);

    Optional<SpricanjeOmjerDto> getOmjeri(Long sredstvoId, Long spricanjeId);
}
