package com.hr.igz.VineApp.service.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Arrays;
import java.util.List;

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

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public HttpStatus getStatus() {
		return status;
	}

	public void setStatus(HttpStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}