package com.pavelkisliuk.fth.exception;

public class FthMailException extends FthServletException {
	public FthMailException(String message) {
		super(message);
	}

	public FthMailException(String message, Throwable cause) {
		super(message, cause);
	}
}