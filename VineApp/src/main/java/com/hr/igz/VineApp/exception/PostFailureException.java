package com.hr.igz.VineApp.exception;

public class PostFailureException  extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public PostFailureException(String exception) {
        super(exception);
    }

}
