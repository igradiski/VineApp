package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.dto.BolestDto;

@Mapper(componentModel="spring")
public abstract class BolestMapper implements EntityMapper<Bolest,BolestDto> {

	@Mapping(target="bolest.id",ignore = true)
	@Mapping(target="bolest.date",ignore = true)
	@Mapping(source="bolestDto.name",target = "bolest.name")
	@Mapping(source="bolestDto.description",target = "bolest.description")
	public abstract Bolest UpdateBolestFromDto (@MappingTarget Bolest bolest, BolestDto bolestDto);

	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	public abstract AntDCascaderDto BolestToCascaderDto(Bolest bolest);
}
