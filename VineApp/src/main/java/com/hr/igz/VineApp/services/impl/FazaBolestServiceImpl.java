package com.hr.igz.VineApp.services.impl;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.BolestHasFaza;
import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import com.hr.igz.VineApp.domain.dto.BolestFazaDto;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.BolestFazaMapper;
import com.hr.igz.VineApp.repository.BolestRepository;
import com.hr.igz.VineApp.repository.FazaBolestRepository;
import com.hr.igz.VineApp.repository.FenofazaRepository;
import com.hr.igz.VineApp.services.FazaBolestService;
import com.hr.igz.VineApp.utils.SortingHelperUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FazaBolestServiceImpl implements FazaBolestService {

    private final BolestRepository bolestRepository;

    private final FenofazaRepository fenofazaRepository;

    private final FazaBolestRepository fazaBolestRepository;

    private final SortingHelperUtil sortHelper;

    private final BolestFazaMapper bolestFazaMapper;

    @Override
    @Transactional
    public ResponseEntity<Object> insertFazaBolest(Long bolestId, Long fazaId) {

        log.info("Pokrenuto dodavanje bolesti: {} i faze: {}",bolestId,fazaId);
        Bolest bolest = dohvatiBolestPremaId(bolestId);
        FenozafaRazvoja faza = dohvatiFazuPremaId(fazaId);
        BolestHasFaza bolestHasFaza = fazaBolestRepository.findByBolestAndFenozafaRazvoja(bolest,faza);
        if(bolestHasFaza == null)
            bolestHasFaza= new BolestHasFaza();
        bolestHasFaza.setBolest(bolest);
        bolestHasFaza.setFenozafaRazvoja(faza);
        bolestHasFaza.setApproved(0);
        try {
            fazaBolestRepository.save(bolestHasFaza);
        }catch (Exception e){
            throw new PostFailureException("Unos bolestHasFaza nije uspio!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("BolestFaza uspjesno kreiran!");
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestFazaDto> getBolestFazePaged(int pageSize, int pageNo, String[] sort) {

        List<Order> orders = sortHelper.getOrdersFromArray(sort);
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(orders));
        return fazaBolestRepository.findAll(paging).map(bolestFazaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestFazaDto> getSredstvoBolestPageFiltered(int pageSize, int pageNo, String[] sort, Long bolestId, Long fazaId) {

        List<Sort.Order> orders = sortHelper.getOrdersFromArray(sort);
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(orders));
        if(bolestId != null && fazaId != null){
            Bolest bolest = dohvatiBolestPremaId(bolestId);
            FenozafaRazvoja faza = dohvatiFazuPremaId(fazaId);
            return fazaBolestRepository.findByBolestAndFenozafaRazvoja(bolest,faza,paging).map(bolestFazaMapper::toDto);
        }else if(bolestId != null ){
            Bolest bolest = dohvatiBolestPremaId(bolestId);
            return fazaBolestRepository.findByBolest(bolest,paging).map(bolestFazaMapper::toDto);
        }else if( azaId != null){
            FenozafaRazvoja faza = dohvatiFazuPremaId(fazaId);
            return fazaBolestRepository.findByFenozafaRazvoja(faza,paging).map(bolestFazaMapper::toDto);
        }else{
            throw new IllegalArgumentException("Nije moguce pronaci zapise s danim filterom!");
        }
    }

    private FenozafaRazvoja dohvatiFazuPremaId(Long fazaId){

        return  fenofazaRepository.findById(fazaId)
                .orElseThrow(()-> new IllegalArgumentException("Nije moguce pronaci fazu s id: "+fazaId));
    }

    private Bolest dohvatiBolestPremaId(Long bolestId){

        return bolestRepository.findById(bolestId)
                .orElseThrow(()-> new IllegalArgumentException("Nije moguce pronaci bolest s id: "+bolestId));
    }
}
