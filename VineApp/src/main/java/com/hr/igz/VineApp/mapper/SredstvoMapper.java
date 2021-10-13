package com.hr.igz.VineApp.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.hr.igz.VineApp.domain.ZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SredstvoDto;
import com.hr.igz.VineApp.repository.TipSredstvaRepository;
import com.hr.igz.VineApp.services.TipSredstvaService;



@Mapper(componentModel="spring", uses=TipSredstvaService.class)
public abstract class SredstvoMapper {
	
	
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
	@Mapping(target = "tipZastitnogSredstva",expression = "java(sredstvoRepos.findById(sredstvo.getTypeOfMedium()).get())")
	public abstract ZastitnoSredstvo sredstvoDtoToZastitnoSredstvo(SredstvoDto sredstvo, TipSredstvaRepository sredstvoRepos);
	
	
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
	public abstract SredstvoDto ZastitnoSredstvoToZastitnoSredstvoDto(ZastitnoSredstvo sredstvo);
	

}
