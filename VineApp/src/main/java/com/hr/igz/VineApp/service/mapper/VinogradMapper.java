package com.hr.igz.VineApp.service.mapper;

import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.dto.VinogradDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public interface VinogradMapper{

    VinogradDto toDto(Vinograd vinograd);

    @Mapping(target = "brojCokota",source = "brojCokota")
    VinogradDto toDtoWithCount(Vinograd vinograd,int brojCokota);

    Vinograd toEntity (VinogradDto vinogradDto);
}
