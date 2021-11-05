package com.hr.igz.VineApp.domain.dto;

import java.time.Instant;

import javax.validation.constraints.NotEmpty;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TipSredstvaDto {

	@NotEmpty
	private String name;
	
	private Instant date;

	private Long id;
}
