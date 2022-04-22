package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;

public record SpricanjeDto(Long id, Long userId, Instant date, Integer water, String description) {

    public SpricanjeDto(Long id, Long userId, Instant date, Integer water, String description) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.water = water;
        this.description = description;
    }
}
