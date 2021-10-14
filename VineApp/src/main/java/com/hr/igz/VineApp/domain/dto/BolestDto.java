package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class BolestDto {

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;
	
	private Instant date;
	
}