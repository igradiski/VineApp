package com.hr.igz.VineApp.service.mapper;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.Base64;

@Mapper(componentModel="spring")
public interface BolestMapper{

	default byte[] toByte(String base64){
		return Base64.getDecoder().decode(base64);
	}
	default String toBase64(byte [] data){
		return Base64.getEncoder().encodeToString(data);
	}

	BolestDto toDto(Bolest bolest);
	Bolest toEntity(BolestDto bolestDto);

	@Mapping(target="bolest.id",ignore = true)
	@Mapping(target="bolest.date",ignore = true)
	@Mapping(source="bolestDto.name",target = "bolest.name")
	@Mapping(source="bolestDto.description",target = "bolest.description")
	Bolest UpdateBolestFromDto (@MappingTarget Bolest bolest, BolestDto bolestDto);

	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	AntDCascaderDto BolestToCascaderDto(Bolest bolest);
}
