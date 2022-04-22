package com.hr.igz.VineApp.domain.dto;

public record SpricanjeSredstvoDto (Long sredstvo, Long utrosak,String napomena,String naziv,String tip,String base64,String preporuceno,Integer karenca,Long spricanjeId, Long id) {

    public SpricanjeSredstvoDto(Long sredstvo, Long utrosak, String napomena, String naziv, String tip, String base64, String preporuceno, Integer karenca, Long spricanjeId, Long id) {
        this.sredstvo = sredstvo;
        this.utrosak = utrosak;
        this.napomena = napomena;
        this.naziv = naziv;
        this.tip = tip;
        this.base64 = base64;
        this.preporuceno = preporuceno;
        this.karenca = karenca;
        this.spricanjeId = spricanjeId;
        this.id = id;
    }
}
