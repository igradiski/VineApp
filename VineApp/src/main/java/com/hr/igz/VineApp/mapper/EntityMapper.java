package com.hr.igz.VineApp.mapper;

import org.mapstruct.MappingTarget;

import java.util.Collection;

public interface EntityMapper<E,D>{

    E toEntity(D dto);

    D toDto(E entity);
    
    Collection<E> toEntityCollection(Collection<D> dtoCollection);

    Collection<E> toDtoCollection(Collection<D> entityCollection);

    void merge(D dto, @MappingTarget E entity);

}
