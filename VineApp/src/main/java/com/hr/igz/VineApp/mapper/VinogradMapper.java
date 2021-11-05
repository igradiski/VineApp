package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.Vinograd;
import com.hr.igz.VineApp.domain.dto.VinogradDto;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public abstract class VinogradMapper {


    public abstract Vinograd toDomain(VinogradDto vinogradDto);

    public abstract VinogradDto toDto(Vinograd vinograd);
}
