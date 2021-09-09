package com.hr.igz.VineApp.services;

import org.springframework.http.ResponseEntity;

public interface EchoService {

	ResponseEntity<Object> sendEcho();

}
