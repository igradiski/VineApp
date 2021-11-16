package com.hr.igz.VineApp.services.impl;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.dto.SpricanjeDto;
import com.hr.igz.VineApp.exception.DeleteFailureException;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.SpricanjeMapper;
import com.hr.igz.VineApp.repository.SpricanjaRepository;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.services.SpricanjaService;
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
public class SpricanjaServiceImpl implements SpricanjaService {

    private final SpricanjaRepository spricanjaRepository;

    private final UserRepository userRepository;

    private final SpricanjeMapper mapper;

    private final SortingHelperUtil sortHelper;

    @Override
    @Transactional
    public ResponseEntity<Object> insertSpricanje(SpricanjeDto spricanjeDto) {

        Spricanje spricanje = mapper.toDomain(spricanjeDto);
        spricanje.setUser(createFejkUser());
        try{
            spricanjaRepository.save(spricanje);
            return ResponseEntity.status(HttpStatus.CREATED).body("Špricanje uspješno uneseno!");
        }catch (Exception e){
            log.error("Greška kod unosa spricanja: {}", spricanje);
            throw new PostFailureException("Nije moguce unijeti špricanje!");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<SpricanjeDto> getSpricanjePaged(int pageSize, int pageNo, String[] sort) {

        List<Sort.Order> orders = sortHelper.getOrdersFromArray(sort);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        return spricanjaRepository.findAllByUser(createFejkUser(),paging)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteSpricanjeById(Long id) {
        Spricanje spricanje = getSpricanje(id);
        try{
            spricanjaRepository.delete(spricanje);
            return ResponseEntity.status(HttpStatus.OK).body("Spricnjae uspjesno obrisana");
        }catch (Exception e){
            log.error("Nije moguce obrisati spricanje: {}",spricanje.toString());
            throw new DeleteFailureException(e.getMessage());
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> updateSpricanje(SpricanjeDto spricanjeDto, Long id) {

        Spricanje oldSpricanje = getSpricanje(id);
        oldSpricanje = mapper.updateFromDto(oldSpricanje,spricanjeDto);
        try{
            spricanjaRepository.save(oldSpricanje);
            return ResponseEntity.status(HttpStatus.OK).body("Bolest je uspješno ažurirana");
        }
        catch (Exception e){
            log.error("Nije moguce ažurirati spricanje: {}",oldSpricanje.toString());
            //TODO PATCH exception
            throw new PostFailureException("Nije moguce ažurirati zeljenu bolest!");
        }
    }

    private Spricanje getSpricanje(Long id){
        return spricanjaRepository.findById(id)
                .orElseThrow(()->{
                    log.error("Ne postoji spricanje za azuriranje s ID: {}",id);
                    throw new PostFailureException("Ne postoji spricanje za azuriranje!");
                });
    }

    private User createFejkUser(){
        return userRepository.getById(2L);
    }
}
