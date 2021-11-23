package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.services.TipSredstvaService;
import org.mapstruct.MappingTarget;


@Mapper(componentModel="spring", uses= {TipSredstvaService.class,TipSredstvaMapper.class})
public abstract class SredstvoMapper implements EntityMapper<ZastitnoSredstvo,SredstvoDto> {

	@Mapping(source = "tipZastitnogSredstva.name",target = "nameOfTipSredstva")
	public abstract SredstvoDto toDto(ZastitnoSredstvo sredstvo);

	@Mapping(target="sredstvo.id",ignore = true)
	@Mapping(target="sredstvo.date",ignore = true)
	@Mapping(source = "sredstvoDto.name",target = "sredstvo.name")
	@Mapping(source = "sredstvoDto.description",target = "sredstvo.description")
	@Mapping(source = "sredstvoDto.composition",target = "sredstvo.composition")
	@Mapping(source = "sredstvoDto.group",target = "sredstvo.group")
	@Mapping(source = "sredstvoDto.formulation",target = "sredstvo.formulation")
	@Mapping(source = "sredstvoDto.typeOfAction",target = "sredstvo.typeOfAction")
	@Mapping(source = "sredstvoDto.usage",target = "sredstvo.usage")
	@Mapping(source = "sredstvoDto.concentration",target = "sredstvo.concentration")
	@Mapping(source = "sredstvoDto.dosageOn100",target = "sredstvo.dosageOn100")
	@Mapping(source = "sredstvoDto.waiting",target = "sredstvo.waiting")
	@Mapping(target = "sredstvo.tipZastitnogSredstva",expression = "java(tipSredstvaRepositoryRepos.findById(sredstvoDto.getTypeOfMedium()).get())")
	public abstract ZastitnoSredstvo UpdateSredstvoFromDto(@MappingTarget ZastitnoSredstvo sredstvo, SredstvoDto sredstvoDto,TipSredstvaRepository tipSredstvaRepositoryRepos);

	@Mapping(source="id",target="key")
	@Mapping(source="id",target="value")
	@Mapping(source="name",target="label")
	public abstract AntDCascaderDto SredstvoToCascaderDto(ZastitnoSredstvo sredstvo);

}
