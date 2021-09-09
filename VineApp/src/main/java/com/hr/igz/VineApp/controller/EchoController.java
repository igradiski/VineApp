package com.hr.igz.VineApp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hr.igz.VineApp.services.EchoService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class EchoController {

	@Autowired
	EchoService echoService;
	
	@GetMapping(value = "/echo", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> Echo() {
		return echoService.sendEcho();
	}
}
