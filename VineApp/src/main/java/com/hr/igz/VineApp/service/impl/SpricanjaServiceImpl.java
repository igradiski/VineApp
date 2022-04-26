package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.dto.SpricanjeDto;
import com.hr.igz.VineApp.repository.SpricanjaRepository;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.service.SpricanjaService;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.SpricanjeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SpricanjaServiceImpl implements SpricanjaService {

    private final SpricanjaRepository spricanjaRepository;
    private final UserRepository userRepository;
    private final SpricanjeMapper mapper;

    private Logger log = LoggerFactory.getLogger(SpricanjaServiceImpl.class);

    public SpricanjaServiceImpl(SpricanjaRepository spricanjaRepository, UserRepository userRepository, SpricanjeMapper mapper) {
        this.spricanjaRepository = spricanjaRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public SpricanjeDto insertSpricanje(SpricanjeDto spricanjeDto) {

        log.debug(spricanjeDto.toString());
        log.info("Started inserting spricanje: "+spricanjeDto);
        Spricanje spricanje = mapper.toEntity(spricanjeDto);
        spricanje.setUser(createFejkUser());
        try{
            return mapper.toDto(spricanjaRepository.save(spricanje));
        }catch (Exception e){
            log.error("Greška kod unosa spricanja: {}", spricanje);
            throw new PostFailureException("Nije moguce unijeti špricanje!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SpricanjeDto> getSpricanjePaged(Pageable pageable) {

        return spricanjaRepository.findAllByUser(createFejkUser(),pageable)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public SpricanjeDto updateSpricanje(SpricanjeDto spricanjeDto) {

        Spricanje oldSpricanje = getSpricanje(spricanjeDto.id());
        oldSpricanje = mapper.updateFromDto(oldSpricanje,spricanjeDto);
        try{
            return mapper.toDto(spricanjaRepository.save(oldSpricanje));
        }
        catch (Exception e){
            log.error("Nije moguce ažurirati spricanje: {}",oldSpricanje.toString());
            throw new PostFailureException("Nije moguce ažurirati zeljenu bolest!");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteSpricanjeById(Long id) {
        Spricanje spricanje = getSpricanje(id);
        try{
            spricanjaRepository.delete(spricanje);
            return ResponseEntity.status(HttpStatus.OK).body("Spricnje uspjesno obrisano");
        }catch (Exception e){
            log.error("Nije moguce obrisati spricanje: {}",spricanje.toString());
            throw new DeleteFailureException(e.getMessage());
        }
    }

    @Transactional(readOnly = true)
    private Spricanje getSpricanje(Long id){
        return spricanjaRepository.findById(id)
                .orElseThrow(()->{
                    log.error("Ne postoji spricanje za azuriranje s ID: {}",id);
                    throw new PostFailureException("Ne postoji spricanje za azuriranje!");
                });
    }

    @Transactional(readOnly = true)
    private User createFejkUser(){
        return userRepository.getById(2L);
    }
}
