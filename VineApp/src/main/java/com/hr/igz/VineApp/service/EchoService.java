package com.hr.igz.VineApp.service;

import org.springframework.http.ResponseEntity;

public interface EchoService {

	ResponseEntity<Object> sendEcho();

}
