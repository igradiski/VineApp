package com.hr.igz.VineApp.exception;

public class ObjectAlreadyExists extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ObjectAlreadyExists(String exception) {
        super(exception);
    }

}
