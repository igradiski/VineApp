package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BolestDto {
	
	private Long id;

	@NotBlank(message = "Name is mandatory!")
	private String name;

	@NotBlank(message = "Description is mandatory!")
	private String description;
	
	private Instant date;

	private String base64;

	private String picture_name;
	
}