package com.hr.igz.VineApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;

@Mapper(componentModel="spring")
public abstract class TipSredstvaMapper {

	@Mapping(source="id",target="id")
	@Mapping(source="name",target="name")
	@Mapping(source="date",target="date")
	public abstract TipSredstvaDto TipSredstvaToTipSredstvaDto(TipZastitnogSredstva sredstvo);
	
	@Mapping(source="name",target="name")
	@Mapping(source="date",target="date")
	public abstract TipZastitnogSredstva TipSredstvaDtoToTipSredstva(TipSredstvaDto sredstvoDto);
	
	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	public abstract AntDCascaderDto AntTipSredstvaToAntDCascaderDto(TipZastitnogSredstva sredstvo);

	@Mapping(target="oldTip.id",ignore = true)
	@Mapping(source = "tipSredstva.name",target = "oldTip.name")
	public abstract TipZastitnogSredstva UpdateTipZastitnogSredstvaFromDto(TipZastitnogSredstva oldTip,
			TipSredstvaDto tipSredstva);
	

}
