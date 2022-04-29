package com.hr.igz.VineApp.service.mapper;

import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.BolestDto;
import org.mapstruct.*;

import java.util.Base64;

@Mapper(componentModel="spring")
public interface BolestMapper{

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

	@Mapping(source = "picture",target = "base64")
	BolestDto toDto(Bolest bolest);

	@Mapping(source = "base64",target = "picture")
	Bolest toEntity(BolestDto bolestDto);

	@Mapping(target="bolest.id",ignore = true)
	@Mapping(target="bolest.date",ignore = true)
	@Mapping(source = "bolestDto.base64",target = "picture")
	Bolest UpdateBolestFromDto (@MappingTarget Bolest bolest, BolestDto bolestDto);

	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	AntDCascaderDto BolestToCascaderDto(Bolest bolest);
}
