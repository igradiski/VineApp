package com.hr.igz.VineApp.service.impl;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.VinogradVinovaloza;
import com.hr.igz.VineApp.domain.dto.VinogradDto;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.repository.VinogradHasLozaRepository;
import com.hr.igz.VineApp.repository.VinogradRepository;
import com.hr.igz.VineApp.service.VinogradService;
import com.hr.igz.VineApp.service.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.service.exception.PostFailureException;
import com.hr.igz.VineApp.service.mapper.VinogradMapper;
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
public class VinogradServiceImpl  implements VinogradService {

    private final VinogradRepository vinogradRepository;
    private final VinogradHasLozaRepository vinogradHasLozaRepository;
    private final UserRepository userRepository;
    private final VinogradMapper mapper;

    private Logger log = LoggerFactory.getLogger(VinogradServiceImpl.class);

    public VinogradServiceImpl(VinogradRepository vinogradRepository, VinogradHasLozaRepository vinogradHasLozaRepository, UserRepository userRepository, VinogradMapper mapper) {
        this.vinogradRepository = vinogradRepository;
        this.vinogradHasLozaRepository = vinogradHasLozaRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    @Transactional
    public VinogradDto insertVinograd(VinogradDto vinogradDto) {

        log.debug(vinogradDto.toString());
        if(vinogradRepository.existsByName(vinogradDto.name())){
            log.error("Postoji vinograd s danim imenom!");
            throw new ObjectAlreadyExists("Vinograd postoji s imenom : "+vinogradDto.name());
        }
        Vinograd vinograd = mapper.toEntity(vinogradDto);
        vinograd.setUser(createFejkUser());
        return mapper.toDto(vinogradRepository.save(vinograd));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VinogradDto> getVinogradi(Pageable pageable) {
        return vinogradRepository.findAllByUser(createFejkUser(),pageable)
                .map(vinograd -> mapper.toDtoWithCount(vinograd,cokotiCount(vinograd.getId())));
    }

    @Override
    public ResponseEntity<Object> deleteVinogradById(Long id) {

        log.debug("ID:"+id);
        Objects.requireNonNull(id,"Id cant be null");
        Vinograd vinograd = vinogradRepository.findById(id).get();
        if(vinograd != null){
            vinogradRepository.delete(vinograd);
            return ResponseEntity.status(HttpStatus.OK).body("Vinograd  je uspješno OBRISAN");
        }else{
            throw new PostFailureException("TODO DELETE exception");
        }
    }

    @Transactional(readOnly = true)
    public Integer cokotiCount(Long id) {
        Vinograd vinograd = vinogradRepository.findById(id).get();
        return vinogradHasLozaRepository.findByVinograd(vinograd)
                .stream()
                .mapToInt(VinogradVinovaloza::getQuantity)
                .sum();
    }
    //TODO  DOHVATITI S PRIJAVE
    private User createFejkUser(){
        return userRepository.getById(2L);
    }
    private User createFejkUser1(){
        return userRepository.getById(1L);
    }
}
