package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.dto.SpricanjeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public abstract class SpricanjeMapper implements EntityMapper<Spricanje,SpricanjeDto> {


    @Mapping(source ="user.id" ,target = "userId")
    public abstract SpricanjeDto toDto(Spricanje spricanje);

    @Mapping(target="spricanje.id",ignore = true)
    public abstract Spricanje updateFromDto(@MappingTarget Spricanje spricanje, SpricanjeDto spricanjeDto);
}
