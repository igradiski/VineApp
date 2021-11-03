package com.hr.igz.VineApp.services.impl;


import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.SredstvoHasBolest;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.BolestSredstvoMapper;
import com.hr.igz.VineApp.repository.BolestRepository;
import com.hr.igz.VineApp.repository.SredstvoBolestRepository;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.services.SredstvoBolestService;
import com.hr.igz.VineApp.utils.SortingHelperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SredstvoBolestServiceImpl implements SredstvoBolestService {

    private final SredstvoBolestRepository sredstvoBolestRepository;

    private final BolestRepository bolestRepository;

    private final SredstvoRepository sredstvoRepository;

    private final SortingHelperUtil sortHelper;

    private final BolestSredstvoMapper mapper;

    @Override
    @Transactional
    public ResponseEntity<Object> insertBolestSredstvo(Long bolestId, Long sredstvoId) {

        log.info("Pokrenuto dodavanje bolesti: {} i sredstva: {}",bolestId,sredstvoId);
        Bolest bolest = dohvatiBolestPremaId(bolestId);
        ZastitnoSredstvo sredstvo = dohvatiSredstvoPremaId(sredstvoId);
        SredstvoHasBolest sredstvoHasBolest = sredstvoBolestRepository.findByZastitnoSredstvoAndBolest(sredstvo,bolest);
        if(sredstvoHasBolest == null)
            sredstvoHasBolest = new SredstvoHasBolest();
        sredstvoHasBolest.setBolest(bolest);
        sredstvoHasBolest.setZastitnoSredstvo(sredstvo);
        sredstvoHasBolest.setApproved(0);
        try {
            sredstvoBolestRepository.save(sredstvoHasBolest);
        }catch (Exception e){
            throw new PostFailureException("Unos sredstvoHasBolest nije uspio!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("BolestSredstvo uspjesno kreiran!");
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestSredstvoDto> getSredstvoBolestPage(int pageSize, int pageNo, String[] sort) {

        List<Sort.Order> orders = sortHelper.getOrdersFromArray(sort);
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(orders));
        return sredstvoBolestRepository.findAll(paging).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestSredstvoDto> getSredstvoBolestPageFiltered(int pageSize, int pageNo, String[] sort, Long bolestId, Long sredstvoId) {

        List<Sort.Order> orders = sortHelper.getOrdersFromArray(sort);
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(orders));
        if(bolestId != null && sredstvoId != null){
            Bolest bolest = dohvatiBolestPremaId(bolestId);
            ZastitnoSredstvo sredstvo = dohvatiSredstvoPremaId(sredstvoId);
            return sredstvoBolestRepository.findByZastitnoSredstvoAndBolest(sredstvo,bolest,paging).map(mapper::toDto);
        }else if(bolestId != null){
            Bolest bolest = dohvatiBolestPremaId(bolestId);
            return sredstvoBolestRepository.findByBolest(bolest,paging).map(mapper::toDto);
        }else if( sredstvoId != null){
            ZastitnoSredstvo sredstvo = dohvatiSredstvoPremaId(sredstvoId);
            return sredstvoBolestRepository.findByZastitnoSredstvo(sredstvo,paging).map(mapper::toDto);
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
