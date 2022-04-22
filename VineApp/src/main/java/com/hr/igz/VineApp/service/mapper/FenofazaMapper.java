package com.hr.igz.VineApp.service.mapper;

import com.hr.igz.VineApp.domain.FenofazaRazvoja;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.hr.igz.VineApp.domain.dto.FenofazaDto;

@Mapper(componentModel="spring")
public interface FenofazaMapper {

	FenofazaDto toDto(FenofazaRazvoja fenofazaRazvoja);

	FenofazaRazvoja toEntity (FenofazaDto fenofazaDto);

	@Mapping(target="fenofaza.id",ignore = true)
	@Mapping(target="fenofaza.date",ignore = true)
	@Mapping(source = "fenofazaDto.name",target = "fenofaza.name")
	@Mapping(source = "fenofazaDto.timeOfUsage",target = "fenofaza.timeOfUsage")
	FenofazaRazvoja UpdateFenofazaFromDto(@MappingTarget FenofazaRazvoja fenofaza, FenofazaDto fenofazaDto);

	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	AntDCascaderDto FenofazaToCascaderDto(FenofazaRazvoja fenofaza);
}
