package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;


public record VinogradDto (Long id, String name,String adress,String description,Integer brojCokota,Instant date) {

    public VinogradDto(Long id, String name, String adress, String description, Integer brojCokota, Instant date) {
        this.id = id;
        this.name = name;
        this.adress = adress;
        this.description = description;
        this.brojCokota = brojCokota;
        this.date = date;
    }
}
