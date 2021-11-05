package com.hr.igz.VineApp.domain.dto;

import com.sun.istack.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.Instant;

@Data
@NoArgsConstructor
public class SredstvoDto {

	private Long id;

	@NotBlank(message = "Name is mandatory!")
	private String name;

	@NotBlank(message = "Name is mandatory!")
	private String description;

	@NotBlank(message = "Name is mandatory!")
	private String composition;

	@NotBlank(message = "Name is mandatory!")
	private String group;

	@NotBlank(message = "Name is mandatory!")
	private String formulation;

	@NotBlank(message = "Name is mandatory!")
	private String typeOfAction;

	@NotBlank(message = "Name is mandatory!")
	private String usage;

	@NotNull
	private Float concentration;

	@NotNull
	private Float dosageOn100;

	@NotNull
	private Integer waiting;
	
	@NotNull
	private Long typeOfMedium;
	
	private Instant date;
	
	private String nameOfTipSredstva;

	private Long tipSredstvaId;

	private String base64;

	private String picture_name;



}
