package com.hr.igz.VineApp.services.impl;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.VinogradHasVinovaloza;
import com.hr.igz.VineApp.domain.Vinovaloza;
import com.hr.igz.VineApp.domain.dto.VinogradHasLozaDto;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.VinogradHasLozaMapper;
import com.hr.igz.VineApp.mapper.VinovaLozaMapper;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.repository.VinogradHasLozaRepository;
import com.hr.igz.VineApp.repository.VinogradRepository;
import com.hr.igz.VineApp.repository.VinovaLozaRepository;
import com.hr.igz.VineApp.services.VinogradHasLozaService;
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

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class VinogradHasLozaServiceImpl implements VinogradHasLozaService {

    private final VinogradHasLozaRepository vinogradHasLozaRepository;

    private final VinogradRepository vinogradRepository;

    private final VinovaLozaRepository vinovaLozaRepository;

    private final UserRepository userRepository;

    private final SortingHelperUtil sortHelper;

    private final VinogradHasLozaMapper mapper;


    @Override
    public ResponseEntity<Object> dodajVinogradHasLoza(VinogradHasLozaDto vinogradHasLozaDto) {

        Vinograd vinograd = vinogradRepository.findById(vinogradHasLozaDto.getIdVinograd())
                .orElseThrow(() ->{
                    log.error("Ne postoji vinograd s id: {}", vinogradHasLozaDto.getIdVinograd());
                    throw new PostFailureException("Nije moguce unijeti zeljenu vezu!");
                });
        Vinovaloza loza = vinovaLozaRepository.findById(vinogradHasLozaDto.getIdLoza())
                .orElseThrow(() ->{
                    log.error("Ne postoji loza s id: {}", vinogradHasLozaDto.getIdVinograd());
                    throw new PostFailureException("Nije moguce unijeti zeljenu vezu!");
                });
        if( vinogradHasLozaRepository.findByUserAndVinogradAndVinovaloza(getUser(),vinograd,loza) != null){
            throw new PostFailureException("Zapis već postoji!!");
        }
        VinogradHasVinovaloza vinogradHasVinovaloza = new VinogradHasVinovaloza();
        vinogradHasVinovaloza.setVinograd(vinograd);
        vinogradHasVinovaloza.setVinovaloza(loza);
        vinogradHasVinovaloza.setQuantity(vinogradHasLozaDto.getBrojCokota());
        vinogradHasVinovaloza.setUser(getUser());
        try{
            vinogradHasLozaRepository.save(vinogradHasVinovaloza);
        }catch (Exception e){
            throw new PostFailureException("Greška kod unosa zapisa");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Vinograd i loza uspojesno uneseni!");

    }

    @Override
    public Page<VinogradHasLozaDto> dohvatiVinogradHasLoza(int pageSize, int pageNo, String[] sort, Long vinogradId) {

        Vinograd vinograd = vinogradRepository.findById(vinogradId)
                .orElseThrow(() ->{
                    log.error("Ne postoji vinograd s id: {}", vinogradId);
                    throw new PostFailureException("Nije moguce unijeti zeljenu vezu!");
                });
        List<Sort.Order> orders = sortHelper.getOrdersFromArray(sort);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        return vinogradHasLozaRepository.findByVinograd(vinograd,paging)
                .map(mapper::ToDto);
    }

    private User getUser(){
        return  userRepository.getById(1L);

    }
}
