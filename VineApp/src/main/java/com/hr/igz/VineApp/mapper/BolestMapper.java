package com.hr.igz.VineApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.dto.BolestDto;

@Mapper(componentModel="spring")
public abstract class BolestMapper {
	
	@Mapping(source = "name",target = "name")
	@Mapping(source = "description",target = "description")
	public abstract Bolest BolestDtoToBolest(BolestDto bolestDto);
	
	@Mapping(source = "name",target = "name")
	@Mapping(source = "description",target = "description")
	@Mapping(source = "date",target = "date")
	public abstract BolestDto BolestToBolestDto(Bolest bolest);

}
