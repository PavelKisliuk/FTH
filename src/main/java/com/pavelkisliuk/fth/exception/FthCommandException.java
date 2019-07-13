package com.pavelkisliuk.fth.exception;

public class FthCommandException extends Exception {
	public FthCommandException(String message) {
		super(message);
	}

	public FthCommandException(String message, Throwable cause) {
		super(message, cause);
	}
}