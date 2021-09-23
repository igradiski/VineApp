package com.hr.igz.VineApp.exception;

public class InvalidLoginException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public InvalidLoginException(String exception) {
        super(exception);
    }

}
