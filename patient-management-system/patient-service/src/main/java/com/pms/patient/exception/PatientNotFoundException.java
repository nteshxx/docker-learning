package com.pms.patient.exception;

public class PatientNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 5132263670046691122L;

	public PatientNotFoundException(String message) {
		super(message);
	}
}
