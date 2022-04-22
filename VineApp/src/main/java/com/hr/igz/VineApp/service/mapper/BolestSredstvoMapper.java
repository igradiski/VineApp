package com.hr.igz.VineApp.service.mapper;


import com.hr.igz.VineApp.domain.SredstvoBolest;
import com.hr.igz.VineApp.domain.dto.BolestSredstvoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface BolestSredstvoMapper {


    @Mapping(source="bolest.name",target="bolestName")
    @Mapping(source="zastitnoSredstvo.name",target="sredstvoName")
    BolestSredstvoDto toDto(SredstvoBolest shb);

    SredstvoBolest toEntity(BolestSredstvoDto bolestSredstvoDto);
}
