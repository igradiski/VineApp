package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;


public record BolestDto(Long id, String name, String description, Instant date, String base64, String picture_name) {

	public BolestDto(Long id, String name, String description, Instant date, String base64, String picture_name) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.date = date;
		this.base64 = base64;
		this.picture_name = picture_name;
	}
}