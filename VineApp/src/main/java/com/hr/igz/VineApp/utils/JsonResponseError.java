package com.hr.igz.VineApp.utils;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class JsonResponseError {
	
	private Integer code;
	
	private HttpStatus status;
	
	private String message;

	private Timestamp timestamp;

	private List<String> errors;

	public JsonResponseError(HttpStatus pStatus, String pMessage, List<String> pErrors) {
		super();
		status = pStatus;
		message = pMessage;
		errors = pErrors;
		code = pStatus.value();
		this.timestamp = Timestamp.from(Instant.now());
	}

	public JsonResponseError(HttpStatus pStatus, String pMessage, String pErrors) {
		super();
		status = pStatus;
		message = pMessage;
		errors = Arrays.asList(pErrors);
		code = pStatus.value();
		this.timestamp = Timestamp.from(Instant.now());
	}
	
	public JsonResponseError(HttpStatus pStatus, String pMessage) {
		super();
		status = pStatus;
		message = pMessage;		
		code = pStatus.value();
		this.timestamp = Timestamp.from(Instant.now());
	}
}