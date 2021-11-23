package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.SpricanjeHasZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SpricanjeSredstvoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring",uses = {SpricanjeMapper.class,BolestMapper.class,TipSredstvaMapper.class})
public abstract class SpricanjeSredstvoMapper implements EntityMapper<SpricanjeHasZastitnoSredstvo,SpricanjeSredstvoDto> {

    @Mapping(source = "spricanje.id",target = "spricanjeId")
    @Mapping(source = "zastitnoSredstvo.waiting",target = "karenca")
    @Mapping(source="zastitnoSredstvo.dosageOn100",target = "preporuceno")
    @Mapping(source="zastitnoSredstvo.picture",target = "base64")
    @Mapping(source = "zastitnoSredstvo.tipZastitnogSredstva.name",target = "tip")
    @Mapping(source="zastitnoSredstvo.name",target = "naziv")
    @Mapping(source = "shs.remark",target = "napomena")
    @Mapping(source = "shs.dosage",target = "utrosak")
    @Mapping(source="zastitnoSredstvo.id",target = "sredstvo")
    public abstract SpricanjeSredstvoDto toDto(SpricanjeHasZastitnoSredstvo shs);
}
