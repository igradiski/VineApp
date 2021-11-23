package com.hr.igz.VineApp.mapper;


import com.hr.igz.VineApp.domain.SredstvoHasBolest;
import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring",uses = {BolestMapper.class,SredstvoMapper.class})
public abstract class BolestSredstvoMapper implements EntityMapper<SredstvoHasBolest,BolestSredstvoDto> {


    @Mapping(source="bolest.name",target="bolestName")
    @Mapping(source="zastitnoSredstvo.name",target="sredstvoName")
    public abstract BolestSredstvoDto toDto(SredstvoHasBolest shb);
}
