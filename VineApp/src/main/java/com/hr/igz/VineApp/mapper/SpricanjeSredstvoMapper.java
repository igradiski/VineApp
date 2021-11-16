package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.SpricanjeHasZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SpricanjeSredstvoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public abstract class SpricanjeSredstvoMapper {

    public abstract SpricanjeHasZastitnoSredstvo toDomain(SpricanjeSredstvoDto sredstvoDto);

    @Mapping(source = "shs.id",target = "id")
    @Mapping(target = "spricanjeId",expression = "java(shs.getSpricanje().getId())")
    @Mapping(target = "karenca",expression = "java(shs.getZastitnoSredstvo().getWaiting())")
    @Mapping(target = "preporuceno",expression = "java(shs.getZastitnoSredstvo().getDosageOn100().toString())")
    @Mapping(target = "base64",expression = "java(shs.getZastitnoSredstvo().getPicture())")
    @Mapping(target = "tip",expression = "java(shs.getZastitnoSredstvo().getTipZastitnogSredstva().getName())")
    @Mapping(target = "naziv",expression = "java(shs.getZastitnoSredstvo().getName())")
    @Mapping(source = "shs.remark",target = "napomena")
    @Mapping(source = "shs.dosage",target = "utrosak")
    @Mapping(target = "sredstvo",expression = "java(shs.getZastitnoSredstvo().getId())")
    public abstract SpricanjeSredstvoDto toDto(SpricanjeHasZastitnoSredstvo shs);
}
