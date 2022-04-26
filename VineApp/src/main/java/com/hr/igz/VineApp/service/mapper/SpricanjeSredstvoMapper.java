package com.hr.igz.VineApp.service.mapper;

import com.hr.igz.VineApp.domain.SpricanjeZastitnoSredstvo;
import com.hr.igz.VineApp.domain.dto.SpricanjeOmjerDto;
import com.hr.igz.VineApp.domain.dto.SpricanjeSredstvoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface SpricanjeSredstvoMapper{

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

    SpricanjeZastitnoSredstvo toEntity(SpricanjeSredstvoDto spricanjeSredstvoDto);

    @Mapping(source = "spricanje.id",target = "spricanjeId")
    @Mapping(source = "zastitnoSredstvo.waiting",target = "karenca")
    @Mapping(source="zastitnoSredstvo.dosageOn100",target = "preporuceno")
    @Mapping(source="zastitnoSredstvo.picture",target = "base64")
    @Mapping(source = "zastitnoSredstvo.tipZastitnogSredstva.name",target = "tip")
    @Mapping(source="zastitnoSredstvo.name",target = "naziv")
    @Mapping(source = "shs.remark",target = "napomena")
    @Mapping(source = "shs.dosage",target = "utrosak")
    @Mapping(source="zastitnoSredstvo.id",target = "sredstvo")
    SpricanjeSredstvoDto toDto(SpricanjeZastitnoSredstvo shs);
}
