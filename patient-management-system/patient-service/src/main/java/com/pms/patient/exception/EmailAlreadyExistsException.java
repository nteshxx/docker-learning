package com.pms.patient.exception;

public class EmailAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = -4206949275779267290L;

	public EmailAlreadyExistsException(String message) {
		super(message);
	}
}
