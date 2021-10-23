package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class BolestDto {
	
	private Long id;

	@NotBlank(message = "Name is mandatory!")
	private String name;

	@NotBlank(message = "Description is mandatory!")
	private String description;
	
	private Instant date;
	
}