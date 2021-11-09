package com.hr.igz.VineApp.mapper;


import com.hr.igz.VineApp.domain.VinogradHasVinovaloza;
import com.hr.igz.VineApp.domain.dto.VinogradHasLozaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public abstract class VinogradHasLozaMapper {

    public abstract VinogradHasVinovaloza ToDomain(VinogradHasLozaDto vinogradHasLozaDto);


    @Mapping(source = "quantity",target = "brojCokota")
    @Mapping(target = "nazivLoze",expression = "java(vinovaloza.getVinovaloza().getName())")
    @Mapping(target ="slikaLoze", expression= "java(vinovaloza.getVinovaloza().getPicture())")
    public abstract VinogradHasLozaDto ToDto(VinogradHasVinovaloza vinovaloza);
}
