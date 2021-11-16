package com.hr.igz.VineApp.domain.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class SpricanjeDto {

    private Long id;

    private Long userId;

    private Instant date;

    private Integer water;

    private String description;
}
