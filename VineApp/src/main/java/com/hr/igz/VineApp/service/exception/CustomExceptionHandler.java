package com.hr.igz.VineApp.service.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.hr.igz.VineApp.service.util.JsonResponseError;


@RestControllerAdvice
public class CustomExceptionHandler extends  ResponseEntityExceptionHandler {
	
	@ExceptionHandler(ObjectAlreadyExists.class)
    public final ResponseEntity<JsonResponseError> handleNevalidnaRola(ObjectAlreadyExists ex, WebRequest request) {
		
		var error = new JsonResponseError(HttpStatus.CONFLICT,ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }
	
	@ExceptionHandler(TokenRefreshException.class)
    public final ResponseEntity<JsonResponseError> handleTokenRefreshException(TokenRefreshException ex, WebRequest request) {
		
		var error = new JsonResponseError(HttpStatus.FORBIDDEN,ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.FORBIDDEN);
    }
	
	@ExceptionHandler(PostFailureException.class)
    public final ResponseEntity<JsonResponseError> handlePostFailureException(PostFailureException ex, WebRequest request) {
		
		var error = new JsonResponseError(HttpStatus.CONFLICT,ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public final ResponseEntity<JsonResponseError> handleDeleteFailureException(NoSuchElementException ex, WebRequest request) {
        var error = new JsonResponseError(HttpStatus.NOT_FOUND,ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

	@ExceptionHandler(DeleteFailureException.class)
    public final ResponseEntity<JsonResponseError> handleDeleteFailureException(DeleteFailureException ex, WebRequest request) {
		
		var error = new JsonResponseError(HttpStatus.CONFLICT,ex.getLocalizedMessage());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
