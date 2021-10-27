package com.hr.igz.VineApp.services;

import org.springframework.http.ResponseEntity;

public interface SredstvoBolestService {

    ResponseEntity<Object> insertBolestSredstvo(Long bolestId, Long sredstvoId);
}
