package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.SpricanjeZastitnoSredstvo;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SpricanjeOmjerDto;
import com.hr.igz.VineApp.domain.dto.SpricanjeSredstvoDto;
import com.hr.igz.VineApp.repository.SpricanjaRepository;
import com.hr.igz.VineApp.repository.SpricanjeSredstvoRepository;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.service.SpricanjeSredstvoService;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.SpricanjeSredstvoMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class SpricanjeSredstvoServiceImpl implements SpricanjeSredstvoService {

    private final SpricanjeSredstvoRepository spricanjeSredstvoRepository;
    private final SpricanjaRepository spricanjaRepository;
    private final SredstvoRepository sredstvoRepository;
    private final SpricanjeSredstvoMapper mapper;

    private Logger log = LoggerFactory.getLogger(SpricanjeSredstvoServiceImpl.class);

    public SpricanjeSredstvoServiceImpl(SpricanjeSredstvoRepository spricanjeSredstvoRepository, SpricanjaRepository spricanjaRepository, SredstvoRepository sredstvoRepository, SpricanjeSredstvoMapper mapper) {
        this.spricanjeSredstvoRepository = spricanjeSredstvoRepository;
        this.spricanjaRepository = spricanjaRepository;
        this.sredstvoRepository = sredstvoRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> addSredstvoHasSpricanje(SpricanjeSredstvoDto spricanjeSredstvoDto) {

        Spricanje spricanje = getSpricanje(spricanjeSredstvoDto.spricanjeId());
        ZastitnoSredstvo sredstvo =getSredstvo(spricanjeSredstvoDto.sredstvo());
        if(spricanjeSredstvoRepository.findAllBySpricanjeAndZastitnoSredstvo(spricanje,sredstvo) != null){
            log.info("Vec postoji!");
            throw new PostFailureException("Greška kod unosa zapisa");
        }
        SpricanjeZastitnoSredstvo shzs = new SpricanjeZastitnoSredstvo();
        shzs.setSpricanje(spricanje);
        shzs.setZastitnoSredstvo(sredstvo);
        shzs.setDosage(spricanjeSredstvoDto.utrosak().doubleValue());
        shzs.setRemark(spricanjeSredstvoDto.napomena());
        try{
            spricanjeSredstvoRepository.save(shzs);
            return ResponseEntity.status(HttpStatus.CREATED).body("Spricanje i sredstvo uspojesno uneseni!");
        }catch (Exception e){
            log.error("Greska kod unosa sredstva u spricanje!");
            throw new PostFailureException("Greška kod unosa zapisa");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SpricanjeSredstvoDto> getSredstvaPaged(Pageable pageable,Long id) {

        Spricanje spricanje = getSpricanje(id);
        Page <SpricanjeZastitnoSredstvo> list = spricanjeSredstvoRepository.findBySpricanje(spricanje,pageable);
        return spricanjeSredstvoRepository.findBySpricanje(spricanje,pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SpricanjeOmjerDto> getOmjeri(Long sredstvoId, Long spricanjeId) {

        //SpricanjeOmjerDto dto = new SpricanjeOmjerDto();
        Spricanje spricanje = getSpricanje(spricanjeId);
        ZastitnoSredstvo sredstvo =getSredstvo(sredstvoId);
        SpricanjeZastitnoSredstvo shz = spricanjeSredstvoRepository.findAllBySpricanjeAndZastitnoSredstvo(spricanje,sredstvo);
        //dto.setMyDose(shz.getDosage().doubleValue());
        //dto.setMyDoseOn100((shz.getDosage().doubleValue() / Double.valueOf(spricanje.getWater()))*100);
        //dto.setDoseOn100(sredstvo.getDosageOn100().doubleValue());
        //dto.setDose((sredstvo.getDosageOn100().doubleValue() / 100)* Double.valueOf(spricanje.getWater()));
        //dto.setPercentage(getPercentage(dto.getMyDose(),dto.getDose()));
        //SpricanjeOmjerDto dto = new SpricanjeOmjerDto();
        //return Optional.of(dto);
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteById(Long id) {

        try{
            spricanjeSredstvoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Brisanje sredstva u spricanju uspjesno obavljeno!");
        }catch (Exception e){
            throw new DeleteFailureException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> updateSredstvoById(SpricanjeSredstvoDto spricanjeSredstvoDto) {

        SpricanjeZastitnoSredstvo shzs = spricanjeSredstvoRepository.findById(spricanjeSredstvoDto.id())
                .orElseThrow(() ->{
                    log.error("Ne postoji zapis s id: {}", spricanjeSredstvoDto.id());
                    throw new PostFailureException("Nije moguce azurirati!");
                });
        shzs.setRemark(spricanjeSredstvoDto.napomena());
        shzs.setDosage(spricanjeSredstvoDto.utrosak().doubleValue());
        try{
            spricanjeSredstvoRepository.save(shzs);
            return ResponseEntity.status(HttpStatus.CREATED).body("Spricanje i sredstvo uspojesno azurirani!");
        }catch (Exception e){
            log.error("Greska kod azuriranja sredstva u spricanje!");
            throw new PostFailureException("Greška kod azuriranja zapisa");
        }
    }

    private Double getPercentage (Double a , double b){

        Double abs = Math.abs(a-b);
        return (abs/((a+b)/2))*100;
    }

    @Transactional(readOnly = true)
    private Spricanje getSpricanje(Long id){
        return spricanjaRepository.findById(id)
                .orElseThrow(() ->{
                    log.error("Ne postoji spricanje s id: {}", id);
                    throw new PostFailureException("Nije moguce dohvatiti!");
                });
    }

    @Transactional(readOnly = true)
    private ZastitnoSredstvo getSredstvo(Long id){
        return sredstvoRepository.findById(id)
                .orElseThrow(()->{
                    log.error("Ne postoji sredstvo s ID: {}",id);
                    throw new PostFailureException("Ne postoji sredstvo za unos!");
                });
    }

}
