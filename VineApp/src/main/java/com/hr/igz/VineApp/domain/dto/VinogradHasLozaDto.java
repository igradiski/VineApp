package com.hr.igz.VineApp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Base64;

@Data
@NoArgsConstructor
public class VinogradHasLozaDto {

    private Long id;

    private Integer brojCokota;

    private Long idLoza;

    private Long  idVinograd;

    private String nazivLoze;

    private String slikaLoze;

    private Instant date;

    public void setSlikaLoze(byte[] content){
        if(content != null){
            this.slikaLoze = Base64.getEncoder().encodeToString(content);
        }
    }
}
