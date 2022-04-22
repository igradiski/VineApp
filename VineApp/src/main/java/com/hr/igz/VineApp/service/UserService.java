package com.hr.igz.VineApp.service;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hr.igz.VineApp.domain.User;
import com.hr.igz.VineApp.domain.dto.UserDto;
import com.hr.igz.VineApp.security.jwt.payload.request.TokenRefreshRequest;

public interface UserService {


	User loadUserByUsername(String username) throws UsernameNotFoundException;
	
	ResponseEntity<Object> userLogin(UserDto loginRequest);

	ResponseEntity<Object> registerUser(UserDto signUpRequest);

	ResponseEntity<?> refreshToken(TokenRefreshRequest request);

}
