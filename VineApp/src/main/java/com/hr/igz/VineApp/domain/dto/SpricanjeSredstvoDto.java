package com.hr.igz.VineApp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Base64;

@Data
@NoArgsConstructor
public class SpricanjeSredstvoDto {

    private Long sredstvo;

    private Long utrosak;

    private String napomena;

    private String naziv;

    private String tip;

    private String base64;

    private String preporuceno;

    private Integer karenca;

    private Long spricanjeId;

    private Long id;

    public void setBase64(byte[] content){
        if(content != null){
            this.base64 = Base64.getEncoder().encodeToString(content);
        }
    }


}
