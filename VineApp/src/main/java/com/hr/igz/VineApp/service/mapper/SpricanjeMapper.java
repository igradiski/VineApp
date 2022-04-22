package com.hr.igz.VineApp.service.mapper;

import com.hr.igz.VineApp.domain.Spricanje;
import com.hr.igz.VineApp.domain.dto.SpricanjeDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel="spring")
public interface SpricanjeMapper {

    Spricanje toEntity(SpricanjeDto spricanjeDto);

    @Mapping(source ="user.id" ,target = "userId")
    SpricanjeDto toDto(Spricanje spricanje);

    @Mapping(target="spricanje.id",ignore = true)
    Spricanje updateFromDto(@MappingTarget Spricanje spricanje, SpricanjeDto spricanjeDto);
}
