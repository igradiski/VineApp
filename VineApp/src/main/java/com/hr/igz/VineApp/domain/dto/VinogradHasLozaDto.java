package com.hr.igz.VineApp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@NoArgsConstructor
public class VinogradHasLozaDto {

    private Integer brojCokota;

    private Long idLoza;

    private Long  idVinograd;

    private String nazivLoze;

    private String slikaLoze;


    public void setSlikaLoze(byte[] content){
        if(content != null){
            this.slikaLoze = Base64.getEncoder().encodeToString(content);
        }
    }
}
