package com.hr.igz.VineApp.domain.dto;

import java.util.Date;

public record UserDto(String name,String surname,String username,String email,String password,int aktivan,Date lastLogin ) {

	public UserDto(String name, String surname, String username, String email, String password, int aktivan, Date lastLogin) {
		this.name = name;
		this.surname = surname;
		this.username = username;
		this.email = email;
		this.password = password;
		this.aktivan = aktivan;
		this.lastLogin = lastLogin;
	}
}
