package com.hr.igz.VineApp.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hr.igz.VineApp.utils.JsonResponseError;


@RestControllerAdvice
public class CustomExceptionHandler extends  ResponseEntityExceptionHandler {
	
	@ExceptionHandler(InvalidLoginException.class)
    public final ResponseEntity<JsonResponseError> handleNevalidnaRola(InvalidLoginException ex, WebRequest request) {
		
		var error = new JsonResponseError(HttpStatus.CONFLICT,ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
