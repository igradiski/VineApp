package com.hr.igz.VineApp.services.impl;


import javax.transaction.Transactional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.igz.VineApp.services.EchoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EchoServiceImpl implements EchoService{

	@Override
	public ResponseEntity<Object> sendEcho() {
		log.info("Saljem da servis radi!");
		return new ResponseEntity<>("Web service is up and running!", HttpStatus.OK);
	}

}
