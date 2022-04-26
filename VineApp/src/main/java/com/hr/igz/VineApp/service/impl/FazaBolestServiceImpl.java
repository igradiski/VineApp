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

import javax.swing.text.html.Option;
import java.util.Optional;

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

        log.debug("Bolest id: "+bolestId+", faza id: "+fazaId);
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


        log.debug("bolest id: "+bolestId+" faza id:"+fazaId);
        Optional<Bolest> bolest = bolestRepository.findById(bolestId);
        Optional<FenofazaRazvoja> faza = fenofazaRepository.findById(fazaId);
        if( !bolest.isEmpty() && faza.isEmpty())
            return fazaBolestRepository.findByBolest(bolest.get(),pageable).map(bolestFazaMapper::toDto);
        else if(bolest.isEmpty() && !faza.isEmpty())
            return fazaBolestRepository.findByFenofazaRazvoja(faza.get(),pageable).map(bolestFazaMapper::toDto);
        else if(!bolest.isEmpty() && !faza.isEmpty())
            return fazaBolestRepository.findByBolestAndFenofazaRazvoja(bolest.get(),faza.get(),pageable).map(bolestFazaMapper::toDto);
        else
            throw new IllegalArgumentException("Nije moguce pronaci zapise s danim filterom!");
    }

    @Transactional(readOnly = true)
    private FenofazaRazvoja dohvatiFazuPremaId(Long fazaId){

        log.debug("id: "+fazaId);
        log.info("Getting faza with id: "+fazaId);
        return  fenofazaRepository.findById(fazaId)
                .orElseThrow(()-> new IllegalArgumentException("Nije moguce pronaci fazu s id: "+fazaId));
    }

    @Transactional(readOnly = true)
    private Bolest dohvatiBolestPremaId(Long bolestId){

        log.debug("id: "+bolestId);
        log.info("Getting bolest with id: "+bolestId);
        return bolestRepository.findById(bolestId)
                .orElseThrow(()-> new IllegalArgumentException("Nije moguce pronaci bolest s id: "+bolestId));
    }
}
