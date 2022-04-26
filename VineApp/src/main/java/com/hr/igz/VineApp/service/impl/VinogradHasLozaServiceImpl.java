package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.VinogradVinovaloza;
import com.hr.igz.VineApp.domain.Vinovaloza;
import com.hr.igz.VineApp.domain.dto.VinogradHasLozaDto;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.repository.VinogradHasLozaRepository;
import com.hr.igz.VineApp.repository.VinogradRepository;
import com.hr.igz.VineApp.repository.VinovaLozaRepository;
import com.hr.igz.VineApp.service.VinogradHasLozaService;
import com.hr.igz.VineApp.service.exception.DeleteFailureException;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.VinogradHasLozaMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class VinogradHasLozaServiceImpl implements VinogradHasLozaService {

    private final VinogradHasLozaRepository vinogradHasLozaRepository;
    private final VinogradRepository vinogradRepository;
    private final VinovaLozaRepository vinovaLozaRepository;
    private final UserRepository userRepository;
    private final VinogradHasLozaMapper mapper;

    private Logger log = LoggerFactory.getLogger(VinogradHasLozaServiceImpl.class);

    public VinogradHasLozaServiceImpl(VinogradHasLozaRepository vinogradHasLozaRepository, VinogradRepository vinogradRepository, VinovaLozaRepository vinovaLozaRepository, UserRepository userRepository, VinogradHasLozaMapper mapper) {
        this.vinogradHasLozaRepository = vinogradHasLozaRepository;
        this.vinogradRepository = vinogradRepository;
        this.vinovaLozaRepository = vinovaLozaRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public VinogradHasLozaDto dodajVinogradHasLoza(VinogradHasLozaDto vinogradHasLozaDto) {

        log.debug(vinogradHasLozaDto.toString());
        Vinograd vinograd = vinogradRepository.findById(vinogradHasLozaDto.idVinograd())
                .orElseThrow(() ->{
                    log.error("Ne postoji vinograd s id: {}", vinogradHasLozaDto.idVinograd());
                    throw new PostFailureException("Nije moguce unijeti zeljeni vinograd!");
                });
        Vinovaloza loza = vinovaLozaRepository.findById(vinogradHasLozaDto.idLoza())
                .orElseThrow(() ->{
                    log.error("Ne postoji loza s id: {}", vinogradHasLozaDto.idVinograd());
                    throw new PostFailureException("Nije moguce unijeti zeljenu lozu!");
                });
        if( vinogradHasLozaRepository.findByUserAndVinogradAndVinovaloza(getUser(),vinograd,loza) != null){
            log.error("Zapis vec postoji u bazi!");
            throw new ObjectAlreadyExists("Zapis već postoji!!");
        }
        VinogradVinovaloza vinogradVinovaloza = new VinogradVinovaloza();
        vinogradVinovaloza.setVinograd(vinograd);
        vinogradVinovaloza.setVinovaloza(loza);
        vinogradVinovaloza.setQuantity(vinogradHasLozaDto.brojCokota());
        vinogradVinovaloza.setUser(getUser());
        try{
            return mapper.toDto(vinogradHasLozaRepository.save(vinogradVinovaloza));
        }catch (Exception e){
            log.error("Greska kod unosa loze u vinograd!");
            throw new PostFailureException("Greška kod unosa zapisa");
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VinogradHasLozaDto> dohvatiVinogradHasLoza(Pageable pageable, Long vinogradId) {

        log.debug("Vinograd id:" +vinogradId);
        Objects.requireNonNull(vinogradId,"Id cant be null!");
        Vinograd vinograd = vinogradRepository.findById(vinogradId)
                .orElseThrow(() ->{
                    log.error("Ne postoji vinograd s id: {}", vinogradId);
                    throw new PostFailureException("Nije moguce pronaci vinograd!");
                });
        return vinogradHasLozaRepository.findByVinograd(vinograd,pageable)
                .map(mapper::toDto);
    }

    @Override
    @Transactional
    public VinogradHasLozaDto updateVinogradHasLoza(VinogradHasLozaDto vinogradHasLozaDto) {

        log.debug(vinogradHasLozaDto.toString());
        Objects.requireNonNull(vinogradHasLozaDto.id(),"Id cant be null!");
        VinogradVinovaloza vhv = vinogradHasLozaRepository.findById(vinogradHasLozaDto.id()).get();
        if(vhv != null){
            vhv.setQuantity(vinogradHasLozaDto.brojCokota());
            return mapper.toDto(vinogradHasLozaRepository.save(vhv));
        }else{
            throw new PostFailureException("Greska kod azuriranja!");
        }
    }

    @Override
    @Transactional
    public ResponseEntity<Object> deleteVinogradHasLozaById(Long id) {

        try{
            vinogradHasLozaRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Zapis uspješno obrisan!");
        }catch (Exception e){
            throw new DeleteFailureException("Brisanje nije uspjelo!");
        }
    }
    private User getUser(){
        return  userRepository.getById(1L);
    }
}
