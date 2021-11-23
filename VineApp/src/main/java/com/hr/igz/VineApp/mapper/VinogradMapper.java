package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.dto.VinogradDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring")
public abstract class VinogradMapper implements EntityMapper<Vinograd,VinogradDto> {

    @Mapping(target = "brojCokota",source = "brojCokota")
    public abstract VinogradDto toDto(Vinograd vinograd,int brojCokota);
}
