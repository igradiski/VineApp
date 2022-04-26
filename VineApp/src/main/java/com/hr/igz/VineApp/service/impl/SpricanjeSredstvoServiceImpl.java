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
import com.hr.igz.VineApp.service.exception.NoSuchElementException;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.SpricanjeSredstvoMapper;
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
import java.util.Objects;
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
    public SpricanjeSredstvoDto addSredstvoHasSpricanje(SpricanjeSredstvoDto spricanjeSredstvoDto) {

        log.debug(spricanjeSredstvoDto.toString());
        Spricanje spricanje = getSpricanje(spricanjeSredstvoDto.spricanjeId());
        ZastitnoSredstvo sredstvo =getSredstvo(spricanjeSredstvoDto.sredstvo());
        if(spricanjeSredstvoRepository.findAllBySpricanjeAndZastitnoSredstvo(spricanje,sredstvo) != null){
            log.error("Zapis vec postoji");
            throw new ObjectAlreadyExists("Zapis vec postoji!");
        }
        SpricanjeZastitnoSredstvo shzs = new SpricanjeZastitnoSredstvo();
        shzs.setSpricanje(spricanje);
        shzs.setZastitnoSredstvo(sredstvo);
        shzs.setDosage(spricanjeSredstvoDto.utrosak().doubleValue());
        shzs.setRemark(spricanjeSredstvoDto.napomena());
        try{
            return mapper.toDto(spricanjeSredstvoRepository.save(shzs));
        }catch (Exception e){
            log.error("Greska kod unosa sredstva u spricanje!");
            throw new PostFailureException("Greška kod unosa zapisa");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SpricanjeSredstvoDto> getSredstvaPaged(Pageable pageable,Long id) {

        log.debug("Id: "+id);
        Spricanje spricanje = getSpricanje(id);
        Page <SpricanjeZastitnoSredstvo> list = spricanjeSredstvoRepository.findBySpricanje(spricanje,pageable);
        return spricanjeSredstvoRepository.findBySpricanje(spricanje,pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SpricanjeOmjerDto> getOmjeri(Long sredstvoId, Long spricanjeId) {

        log.debug("Sredstvo :"+sredstvoId+" spricanje: "+spricanjeId);

        Spricanje spricanje = getSpricanje(spricanjeId);
        ZastitnoSredstvo sredstvo =getSredstvo(sredstvoId);
        SpricanjeZastitnoSredstvo shz = spricanjeSredstvoRepository.findAllBySpricanjeAndZastitnoSredstvo(spricanje,sredstvo);

        Double myDose = shz.getDosage().doubleValue();
        Double myDoseOn100 = (shz.getDosage().doubleValue() / Double.valueOf(spricanje.getWater()))*100;
        Double doseOn100 = sredstvo.getDosageOn100().doubleValue();
        Double dose = (sredstvo.getDosageOn100().doubleValue() / 100)* Double.valueOf(spricanje.getWater());
        Double percentage =getPercentage(myDose,dose);
        SpricanjeOmjerDto spricanjeOmjerDto = new SpricanjeOmjerDto(myDose,myDoseOn100,dose,doseOn100,percentage);
        return Optional.of(spricanjeOmjerDto);
    }

    @Override
    @Transactional
    public SpricanjeSredstvoDto updateSredstvoById(SpricanjeSredstvoDto spricanjeSredstvoDto) {

        log.debug(spricanjeSredstvoDto.toString());
        Objects.requireNonNull(spricanjeSredstvoDto.id(),"Id cant be null!");
        SpricanjeZastitnoSredstvo shzs = spricanjeSredstvoRepository.findById(spricanjeSredstvoDto.id())
                .orElseThrow(() ->{
                    log.error("Ne postoji zapis s id: {}", spricanjeSredstvoDto.id());
                    throw new PostFailureException("Nije moguce azurirati!");
                });
        shzs.setRemark(spricanjeSredstvoDto.napomena());
        shzs.setDosage(spricanjeSredstvoDto.utrosak().doubleValue());
        try{
            return mapper.toDto(spricanjeSredstvoRepository.save(shzs));
        }catch (Exception e){
            log.error("Greska kod azuriranja sredstva u spricanje!");
            throw new PostFailureException("Greška kod azuriranja zapisa");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteById(Long id) {

        log.debug("ID: "+id);
        try{
            spricanjeSredstvoRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Brisanje sredstva u spricanju uspjesno obavljeno!");
        }catch (Exception e){
            throw new DeleteFailureException(e.getMessage());
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
                    throw new NoSuchElementException("Nije moguce dohvatiti spricanje!");
                });
    }

    @Transactional(readOnly = true)
    private ZastitnoSredstvo getSredstvo(Long id){
        return sredstvoRepository.findById(id)
                .orElseThrow(()->{
                    log.error("Ne postoji sredstvo s ID: {}",id);
                    throw new NoSuchElementException("Ne postoji sredstvo!!");
                });
    }

}
