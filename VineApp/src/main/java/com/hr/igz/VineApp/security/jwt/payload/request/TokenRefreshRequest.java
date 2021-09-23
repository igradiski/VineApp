package com.hr.igz.VineApp.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class TokenRefreshRequest {

	@NotBlank
	private String refreshToken;
}
