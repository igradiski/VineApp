package com.hr.igz.VineApp.mapper;

import org.mapstruct.Mapper;

import com.hr.igz.VineApp.domain.TipZastitnogSredstva;
import com.hr.igz.VineApp.domain.dto.TipSredstvaDto;
import com.hr.igz.VineApp.services.TipSredstvaService;

@Mapper(componentModel="spring")
public abstract class TipSredstvaMapper {
	
	public abstract TipSredstvaDto TipSredstvaToTipSredstvaDto(TipZastitnogSredstva sredstvo);

}
