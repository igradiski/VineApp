package com.hr.igz.VineApp.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import lombok.Data;

import java.time.Instant;

@Data
public class BolestSredstvoDto {

    private String bolest;

    private String sredstvo;

    private Long id;

    private Instant lastUpdate;
}
