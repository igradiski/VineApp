package com.hr.igz.VineApp.services.impl;

import com.hr.igz.VineApp.domain.Vinovaloza;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.VinovaLozaDto;
import com.hr.igz.VineApp.exception.DeleteFailureException;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.VinovaLozaMapper;
import com.hr.igz.VineApp.repository.VinovaLozaRepository;
import com.hr.igz.VineApp.services.VinovaLozaService;
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

import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class VinovaLozaServiceImpl  implements VinovaLozaService {

    private final VinovaLozaRepository vinovaLozaRepository;

    private final SortingHelperUtil sortHelper;

    private final VinovaLozaMapper mapper;

    @Override
    @Transactional
    public ResponseEntity<Object> dodajLozu(VinovaLozaDto vinovaLozaDto) {

        if (vinovaLozaRepository.existsByName(vinovaLozaDto.getName())) {
            log.error("Postoji loza s imenom: {}", vinovaLozaDto.getName());
            throw new ObjectAlreadyExists("Vinova loza toga imena vec postoji!");
        }
        Vinovaloza loza = mapper.ToDomain(vinovaLozaDto);
        loza.setApproved(0);
        try{
            byte[] decodedBytes = Base64.getDecoder().decode(vinovaLozaDto.getBase64());
            loza.setPicture(decodedBytes);
            vinovaLozaRepository.save(loza);
        }catch (Exception e){
            log.error("Greška kod unosa loze: {}", loza);
            throw new PostFailureException("Nije moguce unijeti zeljenu vinovu lozu!");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Loza je uspjesno unesena!");
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VinovaLozaDto> getVinovaLozaPaged(int pageSize, int pageNo, String[] sort) {

        List<Sort.Order> orders = sortHelper.getOrdersFromArray(sort);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        return vinovaLozaRepository.findAll(paging).map(mapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<VinovaLozaDto> getLozaForCard(Long id) {

        Vinovaloza loza = vinovaLozaRepository.findById(id).get();
        String base64 = Base64.getEncoder().encodeToString(loza.getPicture());
        VinovaLozaDto dto = mapper.toDto(loza);
        dto.setBase64(base64);
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
    public ResponseEntity<Object> updateLoza(VinovaLozaDto vinovaLozaDto, Long id) {

        Vinovaloza oldLoza = vinovaLozaRepository.findById(id).orElseThrow(() ->{
            log.error("Ne postoji loza s id: {}",id);
            throw new DeleteFailureException("Ne postoji objekt za brisanje!");
        });
        if(vinovaLozaDto.getBase64() == ""){
            byte[] decodedBytes = Base64.getDecoder().decode(vinovaLozaDto.getBase64());
            oldLoza.setPicture(decodedBytes);
            oldLoza.setPicture_name(vinovaLozaDto.getPicture_name());
        }
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
