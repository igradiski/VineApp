package com.hr.igz.VineApp.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface TipSredstvaMapper {

	TipZastitnogSredstva toEntity(TipSredstvaDto tipSredstvaDto);

	TipSredstvaDto toDto(TipZastitnogSredstva tipZastitnogSredstva);

	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	AntDCascaderDto ToAntDCascaderDto(TipZastitnogSredstva sredstvo);

	@Mapping(target="oldTip.id",ignore = true)
	@Mapping(target="oldTip.date",ignore = true)
	@Mapping(source = "tipSredstva.name",target = "oldTip.name")
	TipZastitnogSredstva UpdateTipZastitnogSredstvaFromDto(@MappingTarget TipZastitnogSredstva oldTip,
			TipSredstvaDto tipSredstva);
	

}
