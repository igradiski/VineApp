package com.hr.igz.VineApp.service.mapper;


import com.hr.igz.VineApp.domain.VinogradVinovaloza;
import com.hr.igz.VineApp.domain.dto.VinogradHasLozaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Base64;

@Mapper(componentModel="spring")
public interface VinogradHasLozaMapper {

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

    VinogradVinovaloza toEntity(VinogradHasLozaDto vinogradHasLozaDto);

    VinogradHasLozaDto toDto(VinogradVinovaloza vinogradVinovaloza);

    @Mapping(source = "quantity",target = "brojCokota")
    @Mapping(source="vinovaloza.name",target = "nazivLoze")
    @Mapping(source ="vinovaloza.picture",target ="slikaLoze")
    @Mapping(source ="updatedDate",target =  "date")
    VinogradHasLozaDto ToDto(VinogradVinovaloza vinovaloza);
}
