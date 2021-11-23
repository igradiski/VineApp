package com.hr.igz.VineApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.igz.VineApp.domain.dto.UserDto;
import com.hr.igz.VineApp.security.jwt.payload.request.TokenRefreshRequest;
import com.hr.igz.VineApp.services.UserService;

import lombok.extern.slf4j.Slf4j;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
@Slf4j
public class AuthController {
	
	@Autowired
	UserService userService;

	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@RequestBody UserDto loginRequest) {

		log.info("User login request: {}",loginRequest.toString());
		return userService.userLogin(loginRequest);
	}
	
	@PostMapping("/refreshToken")
	  public ResponseEntity<?> refreshtoken( @RequestBody TokenRefreshRequest request) {
		
		log.info("Token refresh request{}",request.toString());
		return userService.refreshToken(request);
		
	  }
	
	
	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@RequestBody UserDto signUpRequest) {
		
		log.info("User register request: {}",signUpRequest.toString());
		return userService.registerUser(signUpRequest);
	}

}
