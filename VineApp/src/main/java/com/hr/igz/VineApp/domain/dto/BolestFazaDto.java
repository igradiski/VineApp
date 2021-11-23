package com.hr.igz.VineApp.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hr.igz.VineApp.domain.Bolest;
import com.hr.igz.VineApp.domain.FenozafaRazvoja;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class BolestFazaDto {

    private String bolestName;

    private String fazaName;

    private Long id;

    private Instant lastUpdate;
}
