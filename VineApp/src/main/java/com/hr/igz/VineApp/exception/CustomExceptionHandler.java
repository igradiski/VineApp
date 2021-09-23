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
	
	@ExceptionHandler(UserAlreadyExistException.class)
    public final ResponseEntity<JsonResponseError> handleNevalidnaRola(UserAlreadyExistException ex, WebRequest request) {
		
		var error = new JsonResponseError(HttpStatus.CONFLICT,ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(TokenRefreshException.class)
    public final ResponseEntity<JsonResponseError> handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
		
		var error = new JsonResponseError(HttpStatus.FORBIDDEN,ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }

}
