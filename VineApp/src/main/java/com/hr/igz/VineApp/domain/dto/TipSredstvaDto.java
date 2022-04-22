package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;

public record TipSredstvaDto(String name, Instant date, Long id) {

	public TipSredstvaDto(String name, Instant date, Long id) {
		this.name = name;
		this.date = date;
		this.id = id;
	}
}
