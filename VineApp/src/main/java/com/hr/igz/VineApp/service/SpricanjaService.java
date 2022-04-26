package com.hr.igz.VineApp.service;

import com.hr.igz.VineApp.domain.dto.SpricanjeDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface SpricanjaService {
    SpricanjeDto insertSpricanje(SpricanjeDto spricanjeDto);

    Page<SpricanjeDto> getSpricanjePaged(Pageable pageable);

    ResponseEntity<Object> deleteSpricanjeById(Long id);

    SpricanjeDto updateSpricanje(SpricanjeDto spricanjeDto);

}
