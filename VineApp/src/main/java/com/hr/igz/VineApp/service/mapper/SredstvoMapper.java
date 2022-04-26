package com.hr.igz.VineApp.service.mapper;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.service.TipSredstvaService;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Base64;


@Mapper(componentModel="spring",nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface SredstvoMapper {

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

	@Mapping(source = "tipZastitnogSredstva.name",target = "nameOfTipSredstva")
	@Mapping(source = "tipZastitnogSredstva.id",target = "tipSredstvaId")
	@Mapping(source = "picture",target = "base64")
	SredstvoDto toDto(ZastitnoSredstvo sredstvo);

	@Mapping(source = "base64",target = "picture")
	ZastitnoSredstvo toEntity (SredstvoDto sredstvoDto);

	@Mapping(target="sredstvo.id",ignore = true)
	@Mapping(target="sredstvo.date",ignore = true)
	@Mapping(source = "sredstvoDto.base64",target = "picture")
	ZastitnoSredstvo UpdateSredstvoFromDto(@MappingTarget ZastitnoSredstvo sredstvo, SredstvoDto sredstvoDto);

	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	AntDCascaderDto SredstvoToCascaderDto(ZastitnoSredstvo sredstvo);

}
