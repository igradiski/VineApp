package com.hr.igz.VineApp.services.impl;


import com.hr.igz.VineApp.services.SredstvoBolestService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SredstvoBolestServiceImpl implements SredstvoBolestService {

    @Override
    @Transactional
    public ResponseEntity<Object> insertBolestSredstvo(Long bolestId, Long sredstvoId) {
        log.info("Pokrenuto dodavanje bolesti: {} i sredstva: {}",bolestId,sredstvoId);
        return null;
    }
}
