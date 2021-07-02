package com.frsummit.HRM.api.rmi;

public class BeanNotFoundException extends RuntimeException {

	BeanNotFoundException() {
		super();
	}

	BeanNotFoundException(String message) {
		super(message);
	}
}
