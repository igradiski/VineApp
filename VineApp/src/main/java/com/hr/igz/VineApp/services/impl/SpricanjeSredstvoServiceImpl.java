package com.hr.igz.VineApp.services.impl;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.SpricanjeHasZastitnoSredstvo;
import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SpricanjeOmjerDto;
import com.hr.igz.VineApp.domain.dto.SpricanjeSredstvoDto;
import com.hr.igz.VineApp.exception.DeleteFailureException;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.SpricanjeSredstvoMapper;
import com.hr.igz.VineApp.repository.SpricanjaRepository;
import com.hr.igz.VineApp.repository.SpricanjeSredstvoRepository;
import com.hr.igz.VineApp.repository.SredstvoRepository;
import com.hr.igz.VineApp.services.SpricanjeSredstvoService;
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
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class SpricanjeSredstvoServiceImpl implements SpricanjeSredstvoService {

    private final SpricanjeSredstvoRepository spricanjeSredstvoRepository;

    private final SpricanjaRepository spricanjaRepository;

    private final SredstvoRepository sredstvoRepository;

    private final SortingHelperUtil sortHelper;

    private final SpricanjeSredstvoMapper mapper;

    @Override
    @Transactional
    public ResponseEntity<Object> addSredstvoHasSpricanje(SpricanjeSredstvoDto spricanjeSredstvoDto) {

        Spricanje spricanje = getSpricanje(spricanjeSredstvoDto.getSpricanjeId());
        ZastitnoSredstvo sredstvo =getSredstvo(spricanjeSredstvoDto.getSredstvo());
        if(spricanjeSredstvoRepository.findAllBySpricanjeAndZastitnoSredstvo(spricanje,sredstvo) != null){
            log.info("Vec postoji!");
            throw new PostFailureException("Greška kod unosa zapisa");
        }
        SpricanjeHasZastitnoSredstvo shzs = new SpricanjeHasZastitnoSredstvo();
        shzs.setSpricanje(spricanje);
        shzs.setZastitnoSredstvo(sredstvo);
        shzs.setDosage(spricanjeSredstvoDto.getUtrosak().doubleValue());
        shzs.setRemark(spricanjeSredstvoDto.getNapomena());
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
    public Page<SpricanjeSredstvoDto> getSredstvaPaged(int pageSize, int pageNo, String[] sort,Long id) {

        Spricanje spricanje = getSpricanje(id);
        List<Sort.Order> orders = sortHelper.getOrdersFromArray(sort);
        Pageable paging = PageRequest.of(pageNo,pageSize, Sort.by(orders));
        Page <SpricanjeHasZastitnoSredstvo> list = spricanjeSredstvoRepository.findBySpricanje(spricanje,paging);
        return spricanjeSredstvoRepository.findBySpricanje(spricanje,paging).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SpricanjeOmjerDto> getOmjeri(Long sredstvoId, Long spricanjeId) {

        SpricanjeOmjerDto dto = new SpricanjeOmjerDto();
        Spricanje spricanje = getSpricanje(spricanjeId);
        ZastitnoSredstvo sredstvo =getSredstvo(sredstvoId);
        SpricanjeHasZastitnoSredstvo shz = spricanjeSredstvoRepository.findAllBySpricanjeAndZastitnoSredstvo(spricanje,sredstvo);
        dto.setMyDose(shz.getDosage().doubleValue());
        dto.setMyDoseOn100((shz.getDosage().doubleValue() / Double.valueOf(spricanje.getWater()))*100);
        dto.setDoseOn100(sredstvo.getDosageOn100().doubleValue());
        dto.setDose((sredstvo.getDosageOn100().doubleValue() / 100)* Double.valueOf(spricanje.getWater()));
        dto.setPercentage(getPercentage(dto.getMyDose(),dto.getDose()));
        return Optional.of(dto);

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
    public ResponseEntity<Object> updateSredstvoById(SpricanjeSredstvoDto spricanjeSredstvoDto, Long id) {

        SpricanjeHasZastitnoSredstvo shzs = spricanjeSredstvoRepository.findById(id)
                .orElseThrow(() ->{
                    log.error("Ne postoji zapis s id: {}", id);
                    throw new PostFailureException("Nije moguce azurirati!");
                });
        shzs.setRemark(spricanjeSredstvoDto.getNapomena());
        shzs.setDosage(spricanjeSredstvoDto.getUtrosak().doubleValue());
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

    private Spricanje getSpricanje(Long id){
        return spricanjaRepository.findById(id)
                .orElseThrow(() ->{
                    log.error("Ne postoji spricanje s id: {}", id);
                    throw new PostFailureException("Nije moguce dohvatiti!");
                });
    }

    private ZastitnoSredstvo getSredstvo(Long id){
        return sredstvoRepository.findById(id)
                .orElseThrow(()->{
                    log.error("Ne postoji sredstvo s ID: {}",id);
                    throw new PostFailureException("Ne postoji sredstvo za unos!");
                });
    }

}
