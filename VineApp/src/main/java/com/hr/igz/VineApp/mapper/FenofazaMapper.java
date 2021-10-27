package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;

@Mapper(componentModel="spring")
public abstract class FenofazaMapper {
	
	@Mapping(source = "id",target = "id")
	@Mapping(source = "name",target = "name")
	@Mapping(source = "timeOfUsage",target = "timeOfUsage")
	public abstract FenozafaRazvoja FenofazaDtoToFenofaza(FenofazaDto fenofazaDto);
	
	@Mapping(source = "id",target = "id")
	@Mapping(source = "name",target = "name")
	@Mapping(source = "timeOfUsage",target = "timeOfUsage")
	@Mapping(source = "date",target = "date")
	public abstract FenofazaDto FenofazaToFenofazaDto(FenozafaRazvoja fenofaza);
	
	@Mapping(target="fenofaza.id",ignore = true)
	@Mapping(source = "fenofazaDto.name",target = "fenofaza.name")
	@Mapping(source = "fenofazaDto.timeOfUsage",target = "fenofaza.timeOfUsage")
	public abstract FenozafaRazvoja UpdateFenofazaFromDto(@MappingTarget FenozafaRazvoja fenofaza,FenofazaDto fenofazaDto);

	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	public abstract AntDCascaderDto FenofazaToCascaderDto(FenozafaRazvoja fenofaza);
}
