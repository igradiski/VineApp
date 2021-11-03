package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.BolestHasFaza;
import com.hr.igz.VineApp.domain.dto.BolestFazaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public abstract class BolestFazaMapper {

    @Mapping(source = "bhf.id",target = "id")
    @Mapping(source = "bhf.updatedDate",target = "lastUpdate")
    @Mapping(target="bolest",expression = "java(bhf.getBolest().getName())")
    @Mapping(target="faza",expression = "java(bhf.getFenozafaRazvoja().getName())")
    public abstract BolestFazaDto toDto(BolestHasFaza bhf);

}
