package com.hr.igz.VineApp.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.dto.UserDto;

public interface UserService {


	User loadUserByUsername(String username) throws UsernameNotFoundException;
	
	ResponseEntity<Object> userLogin(UserDto loginRequest);

	ResponseEntity<Object> registerUser(UserDto signUpRequest);

}
