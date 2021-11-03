package com.hr.igz.VineApp.mapper;


import com.hr.igz.VineApp.domain.SredstvoHasBolest;
import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public abstract class BolestSredstvoMapper {

    @Mapping(source = "shb.id",target = "id")
    @Mapping(source = "shb.updatedDate",target = "lastUpdate")
    @Mapping(target="bolest",expression = "java(shb.getBolest().getName())")
    @Mapping(target="sredstvo",expression = "java(shb.getZastitnoSredstvo().getName())")
    public abstract BolestSredstvoDto toDto(SredstvoHasBolest shb);
}
