package com.hr.igz.VineApp.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import lombok.Data;

import java.time.Instant;

@Data
public class BolestFazaDto {

    private String bolest;

    private String faza;

    private Long id;

    private Instant lastUpdate;
}
