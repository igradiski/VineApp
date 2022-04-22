package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;

public record VinogradHasLozaDto(Long id,Integer brojCokota,Long idLoza,Long  idVinograd,String nazivLoze,String slikaLoze,Instant date) {

    public VinogradHasLozaDto(Long id, Integer brojCokota, Long idLoza, Long idVinograd, String nazivLoze, String slikaLoze, Instant date) {
        this.id = id;
        this.brojCokota = brojCokota;
        this.idLoza = idLoza;
        this.idVinograd = idVinograd;
        this.nazivLoze = nazivLoze;
        this.slikaLoze = slikaLoze;
        this.date = date;
    }
}
