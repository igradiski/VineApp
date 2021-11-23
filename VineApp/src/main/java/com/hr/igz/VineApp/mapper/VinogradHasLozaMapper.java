package com.hr.igz.VineApp.mapper;


import com.hr.igz.VineApp.domain.VinogradHasVinovaloza;
import com.hr.igz.VineApp.domain.dto.VinogradHasLozaDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel="spring",uses={VinogradMapper.class,VinovaLozaMapper.class})
public abstract class VinogradHasLozaMapper implements EntityMapper<VinogradHasVinovaloza,VinogradHasLozaDto> {


    @Mapping(source = "quantity",target = "brojCokota")
    @Mapping(source="vinovaloza.name",target = "nazivLoze")
    @Mapping(source ="vinovaloza.picture",target ="slikaLoze")
    @Mapping(source ="updatedDate",target =  "date")
    public abstract VinogradHasLozaDto ToDto(VinogradHasVinovaloza vinovaloza);
}
