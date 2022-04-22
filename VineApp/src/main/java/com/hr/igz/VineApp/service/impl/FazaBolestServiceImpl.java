package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.BolestFaza;
import com.hr.igz.VineApp.domain.FenofazaRazvoja;
import com.hr.igz.VineApp.domain.dto.BolestFazaDto;
import com.hr.igz.VineApp.repository.BolestRepository;
import com.hr.igz.VineApp.repository.FazaBolestRepository;
import com.hr.igz.VineApp.repository.FenofazaRepository;
import com.hr.igz.VineApp.service.FazaBolestService;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.BolestFazaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FazaBolestServiceImpl implements FazaBolestService {

    private final BolestRepository bolestRepository;
    private final FenofazaRepository fenofazaRepository;
    private final FazaBolestRepository fazaBolestRepository;
    private final BolestFazaMapper bolestFazaMapper;

    private Logger log = LoggerFactory.getLogger(FazaBolestServiceImpl.class);

    public FazaBolestServiceImpl(BolestRepository bolestRepository, FenofazaRepository fenofazaRepository, FazaBolestRepository fazaBolestRepository, BolestFazaMapper bolestFazaMapper) {
        this.bolestRepository = bolestRepository;
        this.fenofazaRepository = fenofazaRepository;
        this.fazaBolestRepository = fazaBolestRepository;
        this.bolestFazaMapper = bolestFazaMapper;
    }

    @Override
    @Transactional
    public BolestFazaDto insertFazaBolest(Long bolestId, Long fazaId) {

        log.info("Pokrenuto dodavanje bolesti: {} i faze: {}",bolestId,fazaId);
        Bolest bolest = dohvatiBolestPremaId(bolestId);
        FenofazaRazvoja faza = dohvatiFazuPremaId(fazaId);
        BolestFaza bolestFaza = fazaBolestRepository.findByBolestAndFenofazaRazvoja(bolest,faza);
        if(bolestFaza == null)
            bolestFaza = new BolestFaza();
        bolestFaza.setBolest(bolest);
        bolestFaza.setFenofazaRazvoja(faza);
        bolestFaza.setApproved(0);
        try {
            return bolestFazaMapper.toDto(fazaBolestRepository.save(bolestFaza));
        }catch (Exception e){
            throw new PostFailureException("Unos bolestHasFaza nije uspio!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestFazaDto> getBolestFazePaged(Pageable pageable) {
        return fazaBolestRepository.findAll(pageable).map(bolestFazaMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BolestFazaDto> getSredstvoBolestPageFiltered(Pageable pageable, Long bolestId, Long fazaId) {

        if(bolestId != null && fazaId != null){
            Bolest bolest = dohvatiBolestPremaId(bolestId);
            FenofazaRazvoja faza = dohvatiFazuPremaId(fazaId);
            return fazaBolestRepository.findByBolestAndFenofazaRazvoja(bolest,faza,pageable).map(bolestFazaMapper::toDto);
        }else if(bolestId != null ){
            Bolest bolest = dohvatiBolestPremaId(bolestId);
            return fazaBolestRepository.findByBolest(bolest,pageable).map(bolestFazaMapper::toDto);
        }else if( fazaId != null){
            FenofazaRazvoja faza = dohvatiFazuPremaId(fazaId);
            return fazaBolestRepository.findByFenofazaRazvoja(faza,pageable).map(bolestFazaMapper::toDto);
        }else{
            throw new IllegalArgumentException("Nije moguce pronaci zapise s danim filterom!");
        }
    }

    @Transactional(readOnly = true)
    private FenofazaRazvoja dohvatiFazuPremaId(Long fazaId){
        return  fenofazaRepository.findById(fazaId)
                .orElseThrow(()-> new IllegalArgumentException("Nije moguce pronaci fazu s id: "+fazaId));
    }

    @Transactional(readOnly = true)
    private Bolest dohvatiBolestPremaId(Long bolestId){
        return bolestRepository.findById(bolestId)
                .orElseThrow(()-> new IllegalArgumentException("Nije moguce pronaci bolest s id: "+bolestId));
    }
}
