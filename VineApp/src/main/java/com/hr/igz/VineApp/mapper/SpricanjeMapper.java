package com.hr.igz.VineApp.mapper;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.dto.SpricanjeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public abstract class SpricanjeMapper {

    public abstract Spricanje toDomain(SpricanjeDto spricanjeDto);

    public abstract SpricanjeDto toDto(Spricanje spricanje);

    @Mapping(target="spricanje.id",ignore = true)
    public abstract Spricanje updateFromDto(@MappingTarget Spricanje spricanje, SpricanjeDto spricanjeDto);
}
