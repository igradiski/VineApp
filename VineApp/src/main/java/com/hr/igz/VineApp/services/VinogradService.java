package com.hr.igz.VineApp.services;

import com.hr.igz.VineApp.domain.dto.VinogradDto;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface VinogradService {

    ResponseEntity<Object> insertVinograd(VinogradDto vinogradDto);

    Page<VinogradDto> getVinogradi(int pageSize, int pageNo, String[] sort);

}
