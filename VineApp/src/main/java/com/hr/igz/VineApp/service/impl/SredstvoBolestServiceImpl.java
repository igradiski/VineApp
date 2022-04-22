package com.hr.igz.VineApp.service.impl;


import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.SredstvoBolest;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import com.hr.igz.VineApp.repository.BolestRepository;
import com.hr.igz.VineApp.repository.SredstvoBolestRepository;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.service.SredstvoBolestService;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.BolestSredstvoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SredstvoBolestServiceImpl implements SredstvoBolestService {

    private final SredstvoBolestRepository sredstvoBolestRepository;
    private final BolestRepository bolestRepository;
    private final SredstvoRepository sredstvoRepository;
    private final BolestSredstvoMapper mapper;

    private Logger log = LoggerFactory.getLogger(SredstvoBolestServiceImpl.class);

    public SredstvoBolestServiceImpl(SredstvoBolestRepository sredstvoBolestRepository, BolestRepository bolestRepository, SredstvoRepository sredstvoRepository, BolestSredstvoMapper mapper) {
        this.sredstvoBolestRepository = sredstvoBolestRepository;
        this.bolestRepository = bolestRepository;
        this.sredstvoRepository = sredstvoRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> insertBolestSredstvo(Long bolestId, Long sredstvoId) {

        log.info("Pokrenuto dodavanje bolesti: {} i sredstva: {}",bolestId,sredstvoId);
        Bolest bolest = dohvatiBolestPremaId(bolestId);
        ZastitnoSredstvo sredstvo = dohvatiSredstvoPremaId(sredstvoId);
        SredstvoBolest sredstvoBolest = sredstvoBolestRepository.findByZastitnoSredstvoAndBolest(sredstvo,bolest);
        if(sredstvoBolest == null)
            sredstvoBolest = new SredstvoBolest();
        sredstvoBolest.setBolest(bolest);
        sredstvoBolest.setZastitnoSredstvo(sredstvo);
        sredstvoBolest.setApproved(0);
        try {
            sredstvoBolestRepository.save(sredstvoBolest);
        }catch (Exception e){
            throw new PostFailureException("Unos sredstvoHasBolest nije uspio!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("BolestSredstvo uspjesno kreiran!");
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestSredstvoDto> getSredstvoBolestPage(Pageable pageable) {
        return sredstvoBolestRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestSredstvoDto> getSredstvoBolestPageFiltered(Pageable pageable, Long bolestId, Long sredstvoId) {

        if(bolestId != null && sredstvoId != null){
            Bolest bolest = dohvatiBolestPremaId(bolestId);
            ZastitnoSredstvo sredstvo = dohvatiSredstvoPremaId(sredstvoId);
            return sredstvoBolestRepository.findByZastitnoSredstvoAndBolest(sredstvo,bolest,pageable).map(mapper::toDto);
        }else if(bolestId != null){
            Bolest bolest = dohvatiBolestPremaId(bolestId);
            return sredstvoBolestRepository.findByBolest(bolest,pageable).map(mapper::toDto);
        }else if( sredstvoId != null){
            ZastitnoSredstvo sredstvo = dohvatiSredstvoPremaId(sredstvoId);
            return sredstvoBolestRepository.findByZastitnoSredstvo(sredstvo,pageable).map(mapper::toDto);
        }else{
            throw new IllegalArgumentException("Nije moguce pronaci zapise s danim filterom!");
        }
    }

    private Bolest dohvatiBolestPremaId(Long bolestId){

        return bolestRepository.findById(bolestId)
                .orElseThrow(()-> new IllegalArgumentException("Nije moguce pronaci bolest s id: "+bolestId));
    }

    private ZastitnoSredstvo dohvatiSredstvoPremaId(Long sredstvoId){

        return sredstvoRepository.findById(sredstvoId)
                .orElseThrow(()-> new IllegalArgumentException("Nije moguce pronaci sredstvo s id: "));
    }
}
