package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.dto.AntDCascaderDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.services.TipSredstvaService;
import org.mapstruct.MappingTarget;


@Mapper(componentModel="spring", uses=TipSredstvaService.class)
public abstract class SredstvoMapper {
	
	@Mapping(source="sredstvo.id",target = "id")
	@Mapping(source = "sredstvo.name",target = "name")
	@Mapping(source = "sredstvo.description",target = "description")
	@Mapping(source = "sredstvo.composition",target = "composition")
	@Mapping(source = "sredstvo.group",target = "group")
	@Mapping(source = "sredstvo.formulation",target = "formulation")
	@Mapping(source = "sredstvo.typeOfAction",target = "typeOfAction")
	@Mapping(source = "sredstvo.usage",target = "usage")
	@Mapping(source = "sredstvo.concentration",target = "concentration")
	@Mapping(source = "sredstvo.dosageOn100",target = "dosageOn100")
	@Mapping(source = "sredstvo.waiting",target = "waiting")
	@Mapping(target = "tipZastitnogSredstva",expression = "java(tipSredstvaRepositoryRepos.findById(sredstvo.getTypeOfMedium()).get())")
	public abstract ZastitnoSredstvo sredstvoDtoToZastitnoSredstvo(SredstvoDto sredstvo, TipSredstvaRepository tipSredstvaRepositoryRepos);

	@Mapping(source="id",target = "id")
	@Mapping(source = "name",target = "name")
	@Mapping(source = "description",target = "description")
	@Mapping(source = "composition",target = "composition")
	@Mapping(source = "group",target = "group")
	@Mapping(source = "formulation",target = "formulation")
	@Mapping(source = "typeOfAction",target = "typeOfAction")
	@Mapping(source = "usage",target = "usage")
	@Mapping(source = "concentration",target = "concentration")
	@Mapping(source = "dosageOn100",target = "dosageOn100")
	@Mapping(source = "waiting",target = "waiting")
	@Mapping(source = "date",target = "date")
	@Mapping(target = "nameOfTipSredstva",expression = "java(sredstvo.getTipZastitnogSredstva().getName())")
	@Mapping(target = "tipSredstvaId",expression="java(sredstvo.getTipZastitnogSredstva().getId())")
	public abstract SredstvoDto ZastitnoSredstvoToZastitnoSredstvoDto(ZastitnoSredstvo sredstvo);

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
