package com.pavelkisliuk.fth.exception;

public class FthServletException extends Exception {
	public FthServletException(String message) {
		super(message);
	}

	public FthServletException(String message, Throwable cause) {
		super(message, cause);
	}
}