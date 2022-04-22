package com.hr.igz.VineApp.domain.dto;

import javax.validation.constraints.NotEmpty;
import java.time.Instant;


public record FenofazaDto(
		Long id,
		String name,
		String message,
		String timeOfUsage,
		Instant date) {

	public FenofazaDto(
			Long id,
			@NotEmpty(message = "Name is mandatory") String name,
			@NotEmpty(message = "Name is mandatory") String message,
			String timeOfUsage,
			Instant date) {
		this.id = id;
		this.name=name;
		this.message = message;
		this.timeOfUsage = timeOfUsage;
		this.date = date;
	}
}
