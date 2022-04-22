package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;


public record BolestFazaDto(String bolestName,String fazaName,Long id,Instant lastUpdate) {

    public BolestFazaDto(String bolestName, String fazaName, Long id, Instant lastUpdate) {
        this.bolestName = bolestName;
        this.fazaName = fazaName;
        this.id = id;
        this.lastUpdate = lastUpdate;
    }
}
