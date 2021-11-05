package com.hr.igz.VineApp.domain.dto;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
	
	private String name;
	
	private String surname;
	
	private String username;
	
	private String email;
	
	private String password;
	
	private int aktivan;
	
	private Date lastLogin;

}
