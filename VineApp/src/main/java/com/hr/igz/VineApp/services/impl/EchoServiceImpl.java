package com.hr.igz.VineApp.services.impl;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hr.igz.VineApp.repository.RoleRepository;
import com.hr.igz.VineApp.services.EchoService;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class EchoServiceImpl implements EchoService{

	@Autowired
	RoleRepository roleRepos;
	
	@Override
	public ResponseEntity<Object> sendEcho() {
		log.info("Saljem da servis radi!");
		log.info(roleRepos.findAll().get(0).getRoleName());
		return new ResponseEntity<>("Web service is up and running!", HttpStatus.OK);
	}

}
