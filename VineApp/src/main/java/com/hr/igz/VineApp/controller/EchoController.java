package com.hr.igz.VineApp.controller;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EchoController {

	@GetMapping(value = "/echo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Object>> echo() {
		return new ResponseEntity<>(Arrays.asList(LocalDateTime.now(), "Servis prijavnica dostupan!"), HttpStatus.OK);
	}
}
