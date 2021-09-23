package com.hr.igz.VineApp.exception;

public class UserAlreadyExistException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public UserAlreadyExistException(String exception) {
        super(exception);
    }

}
