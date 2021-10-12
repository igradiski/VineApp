package com.hr.igz.VineApp.domain.dto;

import javax.validation.constraints.NotEmpty;

import com.sun.istack.NotNull;

import lombok.Data;

@Data
public class SredstvoDto {

	@NotEmpty
	private String name;

	@NotEmpty
	private String description;

	@NotEmpty
	private String composition;

	@NotEmpty
	private String group;

	@NotEmpty
	private String formulation;

	@NotEmpty
	private String typeOfAction;

	@NotEmpty
	private String usage;

	@NotNull
	private Float concentration;

	@NotNull
	private Float dosageOn100;

	@NotNull
	private Integer waiting;
	
	@NotNull
	private Long typeOfMedium;

}
