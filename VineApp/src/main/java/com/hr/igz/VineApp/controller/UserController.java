package com.hr.igz.VineApp.controller;

import com.hr.igz.VineApp.domain.dto.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/user")
public class UserController {

	@GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	@Operation(summary= "Operacija za fake login usera s front-a")
	public ResponseEntity<Object> loginUser(UserDto userDto) {
		return new ResponseEntity<>("User logged", HttpStatus.CREATED);
	}
}
