package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class TipSredstvaDto {

	@NotEmpty
	private String name;
	
	private Instant date;
}
