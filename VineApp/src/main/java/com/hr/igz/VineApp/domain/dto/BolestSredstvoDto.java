package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;


public record BolestSredstvoDto (String bolestName,String sredstvoName,Long id,Instant lastUpdate){

    public BolestSredstvoDto(String bolestName, String sredstvoName, Long id, Instant lastUpdate) {
        this.bolestName = bolestName;
        this.sredstvoName = sredstvoName;
        this.id = id;
        this.lastUpdate = lastUpdate;
    }
}
