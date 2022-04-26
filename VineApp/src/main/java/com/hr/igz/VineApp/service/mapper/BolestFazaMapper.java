package com.hr.igz.VineApp.service.mapper;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.BolestFaza;
import com.hr.igz.VineApp.domain.dto.BolestFazaDto;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface BolestFazaMapper{

    @Mapping(source ="bolest.name" ,target = "bolestName")
    @Mapping(source ="fenofazaRazvoja.name",target = "fazaName")
    @Mapping(source ="fenofazaRazvoja.updatedDate",target = "lastUpdate")
    BolestFazaDto toDto(BolestFaza bhf);

    //@BeanMapping(ignoreByDefault = true)
    Bolest toEntity(BolestFazaDto fazaDto);

}
