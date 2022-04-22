package com.hr.igz.VineApp.security.jwt.payload.request;

import javax.validation.constraints.NotBlank;


public class TokenRefreshRequest {

	public TokenRefreshRequest() {
	}

	@NotBlank
	private String refreshToken;

	public String getRefreshToken() {
		return refreshToken;
	}

	public void setRefreshToken(String refreshToken) {
		this.refreshToken = refreshToken;
	}
}
