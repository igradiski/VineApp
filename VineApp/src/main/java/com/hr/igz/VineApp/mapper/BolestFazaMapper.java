package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.BolestHasFaza;
import com.hr.igz.VineApp.domain.dto.BolestFazaDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring",uses = {BolestMapper.class,FenofazaMapper.class})
public abstract class BolestFazaMapper implements EntityMapper<BolestHasFaza,BolestFazaDto> {

    @Mapping(source ="bolest.name" ,target = "bolestName")
    @Mapping(source ="fenozafaRazvoja.name",target = "fazaName")
    public abstract BolestFazaDto toDto(BolestHasFaza bhf);

    @BeanMapping(ignoreByDefault = true)
    public abstract BolestHasFaza  toEntity(BolestFazaDto fazaDto);

}
