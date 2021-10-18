package com.hr.igz.VineApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import com.hr.igz.VineApp.domain.dto.FenofazaDto;

@Mapper(componentModel="spring")
public abstract class FenofazaMapper {
	
	@Mapping(source = "name",target = "name")
	@Mapping(source = "timeOfUsage",target = "timeOfUsage")
	public abstract FenozafaRazvoja FenofazaDtoToFenofaza(FenofazaDto fenofazaDto);
	
	@Mapping(source = "name",target = "name")
	@Mapping(source = "timeOfUsage",target = "timeOfUsage")
	public abstract FenofazaDto FenofazaToFenofazaDto(FenozafaRazvoja fenofaza);

}
