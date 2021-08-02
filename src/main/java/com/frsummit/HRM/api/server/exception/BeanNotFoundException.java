package com.frsummit.HRM.api.server.exception;

public class BeanNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BeanNotFoundException() {
		super();
	}

	public BeanNotFoundException(String message) {
		super(message);
	}
}
