package com.hr.igz.VineApp.domain.dto;

public record AntDCascaderDto(String value, String key, String label) {

	public AntDCascaderDto(String value, String key, String label) {
		this.value = value;
		this.key = key;
		this.label = label;
	}
}
