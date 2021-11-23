package com.hr.igz.VineApp.services.impl;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.VinogradHasVinovaloza;
import com.hr.igz.VineApp.domain.dto.VinogradDto;
import com.hr.igz.VineApp.exception.ObjectAlreadyExists;
import com.hr.igz.VineApp.exception.PostFailureException;
import com.hr.igz.VineApp.mapper.VinogradMapper;
import com.hr.igz.VineApp.repository.UserRepository;
import com.hr.igz.VineApp.repository.VinogradHasLozaRepository;
import com.hr.igz.VineApp.repository.VinogradRepository;
import com.hr.igz.VineApp.services.VinogradService;
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
@Slf4j
@RequiredArgsConstructor
public class VinogradServiceImpl  implements VinogradService {

    private final VinogradRepository vinogradRepository;

    private final VinogradHasLozaRepository vinogradHasLozaRepository;

    private final UserRepository userRepository;

    private final VinogradMapper mapper;

    private final SortingHelperUtil sortHelper;

    @Override
    @Transactional
    public ResponseEntity<Object> insertVinograd(VinogradDto vinogradDto) {

        if(vinogradRepository.existsByName(vinogradDto.getName())){
            log.error("Postoji vinograd s danim imenom!");
            throw new ObjectAlreadyExists("Vinograd postoji s imenom : "+vinogradDto.getName());
        }
        Vinograd vinograd = mapper.toEntity(vinogradDto);
        vinograd.setUser(createFejkUser());
        vinogradRepository.save(vinograd);
        return ResponseEntity.status(HttpStatus.CREATED).body("Vinograd  je uspješno kreiran");
    }

    @Override
    @Transactional(readOnly = true)
    public Page<VinogradDto> getVinogradi(int pageSize, int pageNo, String[] sort) {

        List<Sort.Order> orders = sortHelper.getOrdersFromArray(sort);
        Pageable paging = PageRequest.of(pageNo, pageSize, Sort.by(orders));
        return vinogradRepository.findAllByUser(createFejkUser(),paging)
                .map(vinograd -> mapper.toDto(vinograd,cokotiCount(vinograd.getId())));
    }

    @Override
    public ResponseEntity<Object> deleteVinogradById(Long id) {
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
                .mapToInt(VinogradHasVinovaloza::getQuantity)
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
