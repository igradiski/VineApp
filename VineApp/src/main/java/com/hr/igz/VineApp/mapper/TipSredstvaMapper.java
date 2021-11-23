package com.hr.igz.VineApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public abstract class TipSredstvaMapper implements EntityMapper<TipZastitnogSredstva,TipSredstvaDto> {


	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	public abstract AntDCascaderDto AntTipSredstvaToAntDCascaderDto(TipZastitnogSredstva sredstvo);

	@Mapping(target="oldTip.id",ignore = true)
	@Mapping(target="oldTip.date",ignore = true)
	@Mapping(source = "tipSredstva.name",target = "oldTip.name")
	public abstract TipZastitnogSredstva UpdateTipZastitnogSredstvaFromDto(@MappingTarget TipZastitnogSredstva oldTip,
			TipSredstvaDto tipSredstva);
	

}
