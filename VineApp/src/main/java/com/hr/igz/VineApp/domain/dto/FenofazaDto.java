package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data	
public class FenofazaDto {
	
	private Long id;
	
	@NotBlank(message = "Name is mandatory!")
	private String name;

	@NotBlank(message = "Time of usage is mandatory!")
	private String timeOfUsage;
	
	
	private Instant date;

}
