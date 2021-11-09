package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.Vinovaloza;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.VinovaLozaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public abstract class VinovaLozaMapper {

    public abstract Vinovaloza ToDomain(VinovaLozaDto vinovaLozaDto);

    public abstract VinovaLozaDto toDto(Vinovaloza vinovaloza);

    @Mapping(target = "vinovaloza.id",ignore = true)
    @Mapping(target = "vinovaloza.date",ignore = true)
    @Mapping(target = "vinovaloza.approved",ignore = true)
    @Mapping(source="vinovaLozaDto.name",target = "vinovaloza.name")
    @Mapping(source="vinovaLozaDto.description",target = "vinovaloza.description")
    public abstract Vinovaloza updateFromDto(@MappingTarget Vinovaloza vinovaloza, VinovaLozaDto vinovaLozaDto);

    @Mapping(source = "loza.id",target = "value")
    @Mapping(source = "loza.id",target = "key")
    @Mapping(source = "loza.name",target = "label")
    public abstract AntDCascaderDto toCascaderDto(Vinovaloza loza);


}
