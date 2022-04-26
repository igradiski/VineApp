package com.hr.igz.VineApp.service.impl;


import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.SredstvoBolest;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import com.hr.igz.VineApp.repository.BolestRepository;
import com.hr.igz.VineApp.repository.SredstvoBolestRepository;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.service.SredstvoBolestService;
import com.hr.igz.VineApp.service.exception.NoSuchElementException;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.BolestSredstvoMapper;
import javassist.tools.rmi.ObjectNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.swing.text.html.Option;
import java.util.Optional;

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
    public BolestSredstvoDto insertBolestSredstvo(Long bolestId, Long sredstvoId) {

        log.debug("Bolest: "+bolestId+ "Sredstvo:"+sredstvoId);
        log.info("Pokrenuto dodavanje bolesti: {} i sredstva: {}",bolestId,sredstvoId);
        var bolest = bolestRepository.findById(bolestId).orElseThrow(() -> {
            log.error("Ne postoji bolest s id:" +bolestId);
            throw new NoSuchElementException("Bolest s id: "+bolestId+" ne postoji");
        });
        var sredstvo = sredstvoRepository.findById(sredstvoId).orElseThrow(() -> {
            log.error("Ne postoji sredstvo s id:" +sredstvoId);
            throw new NoSuchElementException("Sredstvo s id: "+sredstvoId+" ne postoji");
        });
        SredstvoBolest sredstvoBolest = sredstvoBolestRepository.findByZastitnoSredstvoAndBolest(sredstvo,bolest);
        if(sredstvoBolest != null)
            throw new ObjectAlreadyExists("Zapis vec postoji u bazi!");
        else{
            sredstvoBolest = new SredstvoBolest();
            sredstvoBolest.setBolest(bolest);
            sredstvoBolest.setZastitnoSredstvo(sredstvo);
            sredstvoBolest.setApproved(0);
        }
        try {
            return mapper.toDto(sredstvoBolestRepository.save(sredstvoBolest));
        }catch (Exception e){
            throw new PostFailureException("Unos sredstvoHasBolest nije uspio!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestSredstvoDto> getSredstvoBolestPage(Pageable pageable) {
        return sredstvoBolestRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestSredstvoDto> getSredstvoBolestPageFiltered(Pageable pageable, Long bolestId, Long sredstvoId) {

        log.debug("Sredstvo: "+sredstvoId+" bolest id:"+bolestId);
        Optional<Bolest> bolest = bolestRepository.findById(bolestId);
        Optional<ZastitnoSredstvo> sredstvo = sredstvoRepository.findById(sredstvoId);

        if( !bolest.isEmpty() && sredstvo.isEmpty())
            return sredstvoBolestRepository.findByBolest(bolest.get(),pageable).map(mapper::toDto);
        else if(bolest.isEmpty() && !sredstvo.isEmpty())
            return sredstvoBolestRepository.findByZastitnoSredstvo(sredstvo.get(),pageable).map(mapper::toDto);
        else if(!bolest.isEmpty() && !sredstvo.isEmpty())
            return sredstvoBolestRepository.findByZastitnoSredstvoAndBolest(sredstvo.get(),bolest.get(),pageable).map(mapper::toDto);
        else
            throw new IllegalArgumentException("Nije moguce pronaci zapise s danim filterom!");
    }


}
