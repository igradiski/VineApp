package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.SpricanjeDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

public interface SpricanjaService {
    ResponseEntity<Object> insertSpricanje(SpricanjeDto spricanjeDto);

    Page<SpricanjeDto> getSpricanjePaged(int pageSize, int pageNo, String[] sort);

    ResponseEntity<Object> deleteSpricanjeById(Long id);

    ResponseEntity<Object> updateSpricanje(SpricanjeDto spricanjeDto, Long id);

}
