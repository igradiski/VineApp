package com.hr.igz.VineApp.security.jwt.payload.response;

import lombok.Data;

@Data
public class JwtResponseToken {

	private String token;
	private String type = "Bearer";
	private String refreshToken;
	private String username;
	private String email;
	
	public JwtResponseToken(String jwt, String refTok, String userName, String email) {
		this.token= jwt;
		this.refreshToken = refTok;
		this.username = userName;
		this.email = email;
	}

}
