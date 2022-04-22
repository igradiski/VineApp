package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.Vinovaloza;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.VinovaLozaDto;
import com.hr.igz.VineApp.repository.VinovaLozaRepository;
import com.hr.igz.VineApp.service.VinovaLozaService;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.VinovaLozaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Base64;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class VinovaLozaServiceImpl  implements VinovaLozaService {

    private final VinovaLozaRepository vinovaLozaRepository;
    private final VinovaLozaMapper mapper;

    private Logger log = LoggerFactory.getLogger(VinogradServiceImpl.class);

    public VinovaLozaServiceImpl(VinovaLozaRepository vinovaLozaRepository, VinovaLozaMapper mapper) {
        this.vinovaLozaRepository = vinovaLozaRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public ResponseEntity<Object> dodajLozu(VinovaLozaDto vinovaLozaDto) {

        if (vinovaLozaRepository.existsByName(vinovaLozaDto.name())) {
            log.error("Postoji loza s imenom: {}", vinovaLozaDto.name());
            throw new ObjectAlreadyExists("Vinova loza toga imena vec postoji!");
        }
        Vinovaloza loza = mapper.toEntity(vinovaLozaDto);
        loza.setApproved(0);
        try{
            vinovaLozaRepository.save(loza);
        }catch (Exception e){
            log.error("Greška kod unosa loze: {}", loza);
            throw new PostFailureException("Nije moguce unijeti zeljenu vinovu lozu!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Loza je uspjesno unesena!");
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VinovaLozaDto> getVinovaLozaPaged(Pageable pageable) {
        return vinovaLozaRepository.findAll(pageable).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VinovaLozaDto> getLozaForCard(Long id) {

        Vinovaloza loza = vinovaLozaRepository.findById(id).get();
        String base64 = Base64.getEncoder().encodeToString(loza.getPicture());
        VinovaLozaDto dto = mapper.toDto(loza);
        return Optional.of(dto);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteLozaById(Long id) {

       Vinovaloza loza = vinovaLozaRepository.findById(id).orElseThrow(() ->{
           log.error("Ne postoji loza s id: {}",id);
           throw new DeleteFailureException("Ne postoji objekt za brisanje!");
       });
       try{
            vinovaLozaRepository.delete(loza);
           return ResponseEntity.status(HttpStatus.OK).body("Loza uspjesno obrisana");
       }catch (Exception e){
           log.error("Nije moguce obrisati bolest: {}",loza.toString());
           throw new DeleteFailureException(e.getMessage());
       }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> updateLoza(VinovaLozaDto vinovaLozaDto) {

        Vinovaloza oldLoza = vinovaLozaRepository.findById(vinovaLozaDto.id()).orElseThrow(() ->{
            log.error("Ne postoji loza s id: {}",vinovaLozaDto.id());
            throw new DeleteFailureException("Ne postoji objekt za brisanje!");
        });
        oldLoza = mapper.updateFromDto(oldLoza,vinovaLozaDto);
        try{
            vinovaLozaRepository.save(oldLoza);
            return ResponseEntity.status(HttpStatus.OK).body("Loza je uspješno ažurirana");
        }
        catch (Exception e){
            log.error("Nije moguce ažurirati bolest: {}",vinovaLozaDto.toString());
            throw new PostFailureException("Nije moguce ažurirati zeljenu lozu!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Set<AntDCascaderDto> getVinovaLozaCascader() {
        return vinovaLozaRepository.findAll().stream().map(mapper::toCascaderDto).collect(Collectors.toSet());
    }
}
