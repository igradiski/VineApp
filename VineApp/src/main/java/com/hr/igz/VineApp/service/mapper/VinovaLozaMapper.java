package com.hr.igz.VineApp.service.mapper;

import com.hr.igz.VineApp.domain.Vinovaloza;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.VinovaLozaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Base64;

@Mapper(componentModel="spring")
public interface VinovaLozaMapper{

    default byte[] toByte(String base64){
        if(base64 != null)
            return Base64.getDecoder().decode(base64);
        else {
            return new byte[1];
        }
    }
    default String toBase64(byte [] data){
        if(data != null)
            return Base64.getEncoder().encodeToString(data);
        else
            return "";
    }

    @Mapping(source = "base64",target = "picture")
    Vinovaloza toEntity(VinovaLozaDto vinovaLozaDto);

    @Mapping(source = "picture",target = "base64")
    VinovaLozaDto toDto(Vinovaloza vinovaloza);

    @Mapping(target = "vinovaloza.id",ignore = true)
    @Mapping(target = "vinovaloza.date",ignore = true)
    @Mapping(target = "vinovaloza.approved",ignore = true)
    @Mapping(source="vinovaLozaDto.name",target = "vinovaloza.name")
    @Mapping(source="vinovaLozaDto.description",target = "vinovaloza.description")
    @Mapping(source = "vinovaLozaDto.base64",target = "vinovaloza.picture")
    Vinovaloza updateFromDto(@MappingTarget Vinovaloza vinovaloza, VinovaLozaDto vinovaLozaDto);

    @Mapping(source = "loza.id",target = "value")
    @Mapping(source = "loza.id",target = "key")
    @Mapping(source = "loza.name",target = "label")
    AntDCascaderDto toCascaderDto(Vinovaloza loza);

}
