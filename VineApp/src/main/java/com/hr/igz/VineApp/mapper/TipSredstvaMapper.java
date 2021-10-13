package com.hr.igz.VineApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;

@Mapper(componentModel="spring")
public abstract class TipSredstvaMapper {

	@Mapping(source="name",target="name")
	@Mapping(source="date",target="date")
	public abstract TipSredstvaDto TipSredstvaToTipSredstvaDto(TipZastitnogSredstva sredstvo);
	
	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	public abstract AntDCascaderDto AntTipSredstvaToAntDCascaderDto(TipZastitnogSredstva sredstvo);

}