package com.wade.spring.examples.jpa.mvc.exception;

public class RecordNotFoundException extends Exception {
	private static final long serialVersionUID = -7056669305362993714L;

	public RecordNotFoundException(String message) {
		super(message);
	}
	
	public RecordNotFoundException(String message, Throwable t) {
		super(message, t);
	}
}
