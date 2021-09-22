package com.hr.igz.VineApp.enums;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

public enum ERole {
	
	ROLE_BASIC_USER(1),
	ROLE_ADMIN(2);
	
	private Integer value;

	ERole(Integer value) {
		this.value = value;
	}
	
	public Integer getValue() {
		return value;
	}
	
	public static ERole fromValue(Integer value) {
		for (ERole enumeration : ERole.values()) {
			if (enumeration.getValue().equals(value)) {
				return enumeration;
			}
		}
		return null;
	}
	
	@Converter(autoApply = true)
	@SuppressWarnings("unused")
	public static class RoleKorisnikEnumConverter implements AttributeConverter<ERole, Integer> {

		@Override
		public Integer convertToDatabaseColumn(ERole enumeration) {
			return enumeration != null ? enumeration.getValue() : null;
		}

		@Override
		public ERole convertToEntityAttribute(Integer value) {
			return ERole.fromValue(value);
		}
	}
	

}
